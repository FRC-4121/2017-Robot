
package org.usfirst.frc.team4121.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4121.robot.commands.AutonomousStraightCommand;
import org.usfirst.frc.team4121.robot.commands.AutonomousTurnLeftCommand;
import org.usfirst.frc.team4121.robot.commands.AutonomousTurnRightCommand;
import org.usfirst.frc.team4121.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team4121.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team4121.robot.subsystems.ShifterSubsystem;

import com.ctre.CANTalon;

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
	public static ClimberSubsystem climber;
	public static OI oi;
	
	private SendableChooser<Command> chooser = new SendableChooser<>();
	private CANTalon leftMotor1, leftMotor2, leftMotor3, rightMotor1, rightMotor2, rightMotor3, climbMotor;
	private RobotDrive drive, slaveDrive;
	private Joystick leftJoy, rightJoy;
	private DoubleSolenoid shifterSolenoid;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		driveTrain = new DriveTrainSubsystem();
		shifter = new ShifterSubsystem(shifterSolenoid);
		climber = new ClimberSubsystem(climbMotor);
		oi = new OI();
		
		leftMotor1 = new CANTalon(0); //CHANGE THE DEVICE IDS
		leftMotor2 = new CANTalon(1);
		leftMotor3 = new CANTalon(2);
		rightMotor1 = new CANTalon(3);
		rightMotor2 = new CANTalon(4);
		rightMotor3 = new CANTalon(5);
		climbMotor = new CANTalon(6);
		
		leftJoy = new Joystick(0);
		rightJoy = new Joystick(1);
		
		drive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
		slaveDrive = new RobotDrive(leftMotor3, rightMotor3);
		
		shifterSolenoid = new DoubleSolenoid(0, 1);
		shifter.defaultGearPosition();
		
		chooser.addDefault("Straight Foward", new AutonomousStraightCommand());
		chooser.addObject("Turn Right", new AutonomousTurnRightCommand());
		chooser.addObject("Turn Left", new AutonomousTurnLeftCommand());
		SmartDashboard.putData("Auto mode", chooser);
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
		//autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		//if (autonomousCommand != null)
			//autonomousCommand.start();
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
		//if (autonomousCommand != null)
			//autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		while(isOperatorControl() && isEnabled()) {
			drive.tankDrive(leftJoy, rightJoy);
			slaveDrive.tankDrive(leftJoy, rightJoy);
			Timer.delay(0.01);
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
