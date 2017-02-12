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
import org.usfirst.frc.team4121.robot.extraClasses.VisionProcessor;
import org.usfirst.frc.team4121.robot.extraClasses.VisionRead;
import org.usfirst.frc.team4121.robot.extraClasses.MyVisionThread;
import org.usfirst.frc.team4121.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team4121.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team4121.robot.subsystems.ShifterSubsystem;
import org.usfirst.frc.team4121.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team4121.robot.subsystems.VisionSubsystem;

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
	public static DriveTrainSubsystem driveTrain;
	public static ShifterSubsystem shifter;
	public static ShooterSubsystem shooter;
	public static ClimberSubsystem climber;
	public static VisionProcessor vision;
	public static VisionRead visionReader;
	public static FindGearTargetCommand findGear;
	public static FindBoilerTargetCommand findBoiler;
	public static VisionSubsystem visionSub;
	public static OI oi;
	//public static VisionThreadBoiler visionThreadBoiler;
	//public static VisionThreadGear visionThreadGear;
	public static double[] visionArray;
	public static Object imgLock;
	public static UsbCamera gearCam;
	public static UsbCamera boilerCam;
	public static MyVisionThread visionThread;
	public static CameraServer camServer;
	public Thread myThread;
	private VisionThread visionProcThread;
	
	private SendableChooser<Command> chooser;
	
	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		driveTrain = new DriveTrainSubsystem();
		shifter = new ShifterSubsystem();
		shooter = new ShooterSubsystem();
		climber = new ClimberSubsystem();
		chooser = new SendableChooser<>();
		autonomousCommand = new ExampleCommand();
		findGear = new FindGearTargetCommand();
		findBoiler = new FindBoilerTargetCommand();
		visionSub = new VisionSubsystem();
		//visionThreadBoiler = new VisionThreadBoiler();
		//visionThreadGear = new VisionThreadGear();
		imgLock = new Object();
		vision = new VisionProcessor();
		oi = new OI();
		
		//Initialize dashboard choosers
		chooser.addDefault("Do nothing", new AutoStopCommand());
		chooser.addObject("Straight Foward", new AutoDriveStraightCommandGroup());
		chooser.addObject("Turn Left", new AutoTurnLeftCommandGroup());
		chooser.addObject("Turn Right", new AutoTurnRightCommandGroup());

		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putString("Vision: ", vision.tempDouble());
		
		
		//Initialize cameras and start autocapture for dashboard
		camServer = CameraServer.getInstance();
		visionThread = new MyVisionThread();
		myThread = new Thread(visionThread);
		myThread.setDaemon(true);
		myThread.start();
		
	
		//Initialze Vision Processing
		visionReader = new VisionRead();
		 visionProcThread = new VisionThread(gearCam,visionReader, new VisionRunner.Listener<VisionRead>() {

			@Override
			public void copyPipelineOutputs(VisionRead pipeline) {
				
				synchronized (imgLock) {
					visionArray = vision.update(pipeline);
				}
			}
		});
		
		 visionProcThread.start();
		
		

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
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

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
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		// if (autonomousCommand != null)
		// autonomousCommand.cancel();

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putString("Gear Position", shifter.gearPosition());
		//SmartDashboard.putBoolean("Lined Up to Gear: ", findGear.isLinedUp());
		//SmartDashboard.putBoolean("Lined Up to Boiler: ", findBoiler.isLinedUp());
		//SmartDashboard.putString("Vision: ", vision.tempDouble());
		//SmartDashboard.putBoolean("Thread on", Robot.visionThread.gearCam);
		synchronized (imgLock) {
			SmartDashboard.putString("Distance between x", Double.toString(visionArray[0]));
			SmartDashboard.putString("is Facing", Double.toString(visionArray[1]));
			SmartDashboard.putString("Ratio of areas", Double.toString(visionArray[2]));

		}
		
	}
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
