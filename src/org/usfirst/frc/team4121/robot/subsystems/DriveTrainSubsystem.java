package org.usfirst.frc.team4121.robot.subsystems;

import com.ctre.CANTalon;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;
import org.usfirst.frc.team4121.robot.commands.DriveWithJoysticksCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	DriveTrain subsystem	
 *	
 *	@author Ben Hayden
 */
public class DriveTrainSubsystem extends Subsystem {	
	CANTalon leftMotor1 = new CANTalon(RobotMap.LEFT_MOTOR_1);
	CANTalon leftMotor2 = new CANTalon(RobotMap.LEFT_MOTOR_2);
	CANTalon leftMotor3 = new CANTalon(RobotMap.LEFT_MOTOR_3);
	CANTalon rightMotor1 = new CANTalon(RobotMap.RIGHT_MOTOR_1);
	CANTalon rightMotor2 = new CANTalon(RobotMap.RIGHT_MOTOR_2);
	CANTalon rightMotor3 = new CANTalon(RobotMap.RIGHT_MOTOR_3);
	
	//leftMotor1.set(1.0); if RobotDrive doesn't work do it all manually
	RobotDrive drive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
	RobotDrive driveSlave = new RobotDrive(leftMotor3, rightMotor3);
	
	Joystick left = new Joystick(0);
	Joystick right = new Joystick(1);
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticksCommand());
	}
	
	public void drive() {
		drive.tankDrive(Robot.oi.getLeftJoy(), Robot.oi.getRightJoy());
		driveSlave.tankDrive(Robot.oi.getLeftJoy(), Robot.oi.getRightJoy());
	}
	
	public void autoDriveStraight(double leftMotor, double rightMotor) {
		drive.setSafetyEnabled(false);
		driveSlave.setSafetyEnabled(false);
		
		drive.setMaxOutput(1.0);
		driveSlave.setMaxOutput(1.0);
		
		drive.tankDrive(leftMotor, rightMotor);
		driveSlave.tankDrive(leftMotor, rightMotor);
	}
	
	public void autoStop() {
		drive.tankDrive(0, 0);
		driveSlave.tankDrive(0, 0);
	}
}
