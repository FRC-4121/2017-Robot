package org.usfirst.frc.team4121.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	DriveTrain subsystem	
 *	
 *	@author Ben Hayden
 */
public class DriveTrainSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	CANTalon leftMotor1 = new CANTalon(1);
	CANTalon leftMotor2 = new CANTalon(1);
	CANTalon rightMotor1 = new CANTalon(1);
	CANTalon rightMotor2 = new CANTalon(1);
	
	RobotDrive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		
		setDefaultCommand(new )
	}
}
