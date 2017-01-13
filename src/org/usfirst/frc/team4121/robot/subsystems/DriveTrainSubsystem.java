package org.usfirst.frc.team4121.robot.subsystems;

import org.usfirst.frc.team4121.robot.commands.DriveCommand;

import com.ctre.CANTalon;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	DriveTrain subsystem	
 *	
 *	@author Ben Hayden
 */
public class DriveTrainSubsystem extends Subsystem 
{	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	CANTalon leftMotor1 = new CANTalon(0);
	CANTalon leftMotor2 = new CANTalon(1);
	CANTalon rightMotor1 = new CANTalon(1);
	CANTalon rightMotor2 = new CANTalon(1);
	
	RobotDrive drive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		
		setDefaultCommand(new DriveCommand());
	}
	
	public void operatorControl() {
	}
	
	public void tankDrive(double left, double right) {
		leftMotor1.set(-left);
		leftMotor2.set(-left);
		rightMotor1.set(right);
		rightMotor2.set(right);
	}
	
	public void stopMotors() {
		leftMotor1.set(0);
		leftMotor2.set(0);
		rightMotor1.set(0);
		rightMotor2.set(0);
	}
}
