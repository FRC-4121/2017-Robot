package org.usfirst.frc.team4121.robot;

import org.usfirst.frc.team4121.robot.commands.AutoStopCommand;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4121.robot.commands.AutoDriveStraightCommandGroup;
import org.usfirst.frc.team4121.robot.commands.AutoTurnLeftCommandGroup;
import org.usfirst.frc.team4121.robot.commands.AutoTurnRightCommandGroup;
import org.usfirst.frc.team4121.robot.commands.ExampleCommand;
import org.usfirst.frc.team4121.robot.commands.FindBoilerTargetCommand;
import org.usfirst.frc.team4121.robot.commands.FindGearTargetCommand;
import org.usfirst.frc.team4121.robot.commands.NewDriveStraightCommandGoup;
import org.usfirst.frc.team4121.robot.extraClasses.VisionProcessor;
import org.usfirst.frc.team4121.robot.extraClasses.VisionRead;
import org.usfirst.frc.team4121.robot.extraClasses.MyVisionThread;
import org.usfirst.frc.team4121.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team4121.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team4121.robot.subsystems.LimitSwitchSubsystem;
import org.usfirst.frc.team4121.robot.subsystems.ShifterSubsystem;
import org.usfirst.frc.team4121.robot.subsystems.ShooterSubsystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.first.wpilibj.vision.VisionRunner;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @author Ben Hayden
 */
public class Robot extends IterativeRobot {
		
	//Subsystems
	public static DriveTrainSubsystem driveTrain;
	public static ShifterSubsystem shifter;
	public static ShooterSubsystem shooting;
	public static ClimberSubsystem climber;
	
	//Commands
	public static FindGearTargetCommand findGear;
	public static OI oi;

	//SmartDashboard chooser
	private SendableChooser<Command> chooser;
	
	Command autonomousCommand;

	//encoder math values
	public static double distanceTraveled;
	public static double angleTraveled;
	public static double leftDistance;
	public static double rightDistance;
	
	//Vision variables
	public static VisionRead visionReader;
	public static double[] visionArray;
	public static Object imgLock;
	public static UsbCamera gearCam;
	public static CameraServer camServer;
	private VisionThread visionProcThread;
	public static double leftTargetCenter;
	public static double rightTargetCenter;
	public static double centerOfTargets;
	public static double targetError;
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		//Initialize subsystems
		driveTrain = new DriveTrainSubsystem();
		shifter = new ShifterSubsystem();
		shooting = new ShooterSubsystem();
		climber = new ClimberSubsystem();
		oi = new OI();
	
		
		//Initialize commands
		autonomousCommand = new ExampleCommand();
		findGear = new FindGearTargetCommand();
		
		
		//Initialize dashboard choosers
		chooser = new SendableChooser<>();
		chooser.addDefault("Do nothing", new AutoStopCommand());
		chooser.addObject("Straight Foward", new AutoDriveStraightCommandGroup());
		chooser.addObject("Turn Left", new AutoTurnLeftCommandGroup());
		chooser.addObject("Turn Right", new AutoTurnRightCommandGroup());
		SmartDashboard.putData("Auto mode", chooser);
		
		//Initialize vision processing, cameras and start autocapture for dashboard
		imgLock = new Object();
		camServer = CameraServer.getInstance();		
		Robot.gearCam = new UsbCamera("cam0", 0);		
		Robot.camServer.addCamera(Robot.gearCam);		
		Robot.gearCam.setResolution(320, 240);
		Robot.gearCam.setBrightness(10);		
		Robot.camServer.startAutomaticCapture(Robot.gearCam);
				
		visionReader = new VisionRead();
		visionProcThread = new VisionThread(gearCam,visionReader, new VisionRunner.Listener<VisionRead>() {

			@Override
			public void copyPipelineOutputs(VisionRead pipeline) {
				
				synchronized (imgLock) {
					//visionArray = vision.update(pipeline);
					
					if (!pipeline.filterContoursOutput().isEmpty()) {
						Rect leftRect = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
						Rect rightRect = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));
						
						leftTargetCenter = leftRect.x+(leftRect.width/2);
						rightTargetCenter = rightRect.x+(rightRect.width/2);
						centerOfTargets = (leftTargetCenter + rightTargetCenter)/2;
						targetError = centerOfTargets - (RobotMap.IMG_WIDTH/2);
					}
					else
					{
						leftTargetCenter = -9999.0;
						rightTargetCenter = -9999.0;
						centerOfTargets = -9999.0;
						targetError = 0.0;
					}
						
				}
			}
		});
		visionProcThread.start();
		
		
		//Calibrate the main gyro
		Robot.oi.MainGyro.calibrate();
		Robot.oi.MainGyro.reset();
		
		
		//setting Encoders up
		distanceTraveled = 0.0;
		
		//gyro
		angleTraveled =0.0;
		
	}

	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	
	@Override
	public void disabledPeriodic() {
		
		//Start scheduler
		Scheduler.getInstance().run();
		
	}

	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		//Get selected autonomous command
		autonomousCommand = chooser.getSelected();
		
		//Reset encoders
		Robot.oi.rightEncoder.reset();
		Robot.oi.leftEncoder.reset();
		
		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
		
	}

	
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		//Start autonomous scheduler
		Scheduler.getInstance().run();
		
		//Put key values on smart dashboard
		SmartDashboard.putString("Drive Angle:", Double.toString(Robot.oi.MainGyro.getAngle()));
		SmartDashboard.putString("Left Drive Distance: ", Double.toString(Robot.oi.leftEncoder.getDistance()));
		SmartDashboard.putString("Right Drive Distance: ", Double.toString(Robot.oi.rightEncoder.getDistance()));
		SmartDashboard.putString("Gear Position: ", shifter.gearPosition());

	}

	
	@Override
	public void teleopInit() {
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null){
			autonomousCommand.cancel();
		}
		Scheduler.getInstance().removeAll();
		
		//Robot.gearCam.setBrightness(100);
		
		//Reset encoders
		Robot.oi.rightEncoder.reset();
		Robot.oi.leftEncoder.reset();
		
	}

	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		//Start scheduler
		Scheduler.getInstance().run();
 
		//Put key values on the smart dashboard
		SmartDashboard.putString("Gear Position: ", shifter.gearPosition());
		SmartDashboard.putString("Drive Direction:", Integer.toString(RobotMap.DIRECTION_MULTIPLIER));		
		SmartDashboard.putString("Right Drive Distance (in inches): ", Double.toString(Robot.oi.rightEncoder.getDistance()));
		SmartDashboard.putString("Left Drive Distance (in inches): ", Double.toString(Robot.oi.leftEncoder.getDistance()));
		
		synchronized (imgLock) {
			SmartDashboard.putString("Target Error:", Double.toString(targetError));
			SmartDashboard.putString("Left Rect Center: ", Double.toString(leftTargetCenter));
			SmartDashboard.putString("Right Rect Center: ", Double.toString(rightTargetCenter));

		}
		
		SmartDashboard.putString("Limit Switch: ", Boolean.toString(Robot.oi.limitSwitch.get()));
		
	}
	
	
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
		//Start live window
		LiveWindow.run();
		
	}
}
