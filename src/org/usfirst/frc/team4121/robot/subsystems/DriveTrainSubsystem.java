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
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		
		setDefaultCommand(new DriveCommand());
	}
	
	public void operatorControl() {
	}
}
