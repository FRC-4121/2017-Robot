package org.usfirst.frc.team4121.robot.subsystems;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;
import org.usfirst.frc.team4121.robot.commands.DriveWithJoysticksCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**         
 *	DriveTrain subsystem	
 *	                                                                    
 *	@author Ben Hayden
 */
public class DriveTrainSubsystem extends Subsystem {
	
	double leftDirection, rightDirection;
	
	//Initializing all Talons using CAN                                                
	CANTalon leftMotor1 = new CANTalon(RobotMap.LEFT_MOTOR_1);
	CANTalon leftMotor2 = new CANTalon(RobotMap.LEFT_MOTOR_2);
	CANTalon leftMotor3 = new CANTalon(RobotMap.LEFT_MOTOR_3);
	CANTalon rightMotor1 = new CANTalon(RobotMap.RIGHT_MOTOR_1);
	CANTalon rightMotor2 = new CANTalon(RobotMap.RIGHT_MOTOR_2);
	CANTalon rightMotor3 = new CANTalon(RobotMap.RIGHT_MOTOR_3);
	
	//Creating 2 robot drives for all 6 motors
	RobotDrive drive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
	RobotDrive driveSlave = new RobotDrive(leftMotor3, rightMotor3);
	
	//Initializing both joysticks
	Joystick left = new Joystick(0);
	Joystick right = new Joystick(1);
	
	
	//Setting the default command to DriveWithJoysticksCommands
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticksCommand());
	}
	
	//Drive method that creates two tank drives with the left and right joysticks
	public void drive() {
		if (Robot.oi.leftJoy.getDirectionDegrees()>= 0)
		{
			leftDirection=1;
		}
		else
		{
			leftDirection=-1;
		}
		if (Robot.oi.rightJoy.getDirectionDegrees()>= 0)
		{
			rightDirection=1;
		}
		else
		{
			rightDirection=-1;
		}
		
		drive.tankDrive(Robot.oi.leftJoy.getMagnitude()*RobotMap.DIRECTION_MULTIPLIER*leftDirection, Robot.oi.rightJoy.getMagnitude()*RobotMap.DIRECTION_MULTIPLIER*rightDirection);
		driveSlave.tankDrive(Robot.oi.leftJoy.getMagnitude()*RobotMap.DIRECTION_MULTIPLIER*leftDirection, Robot.oi.rightJoy.getMagnitude()*RobotMap.DIRECTION_MULTIPLIER*rightDirection);
		
		
		drive.setSafetyEnabled(false);
		driveSlave.setSafetyEnabled(false);
		
		drive.setMaxOutput(0.8);
		driveSlave.setMaxOutput(0.8);
	}
	
	//Auto drive that inputs two doubles for the speeds of the motors
	public void autoDrive(double leftMotor, double rightMotor) {
		drive.setSafetyEnabled(false);
		driveSlave.setSafetyEnabled(false);
		
		drive.setMaxOutput(0.8);
		driveSlave.setMaxOutput(0.8);
		
		drive.tankDrive(leftMotor, rightMotor);
		driveSlave.tankDrive(leftMotor, rightMotor);
	}
	
	//A method that stops all motors
	public void autoStop() {
		drive.tankDrive(0, 0);
		driveSlave.tankDrive(0, 0);
	}
	
	public void switchControls() {
		drive.tankDrive(RobotMap.DIRECTION_MULTIPLIER * Robot.oi.leftJoy.getMagnitude(), RobotMap.DIRECTION_MULTIPLIER * Robot.oi.leftJoy.getMagnitude());
		driveSlave.tankDrive(Robot.oi.leftJoy, Robot.oi.rightJoy);
		
		drive.setSafetyEnabled(false);
		driveSlave.setSafetyEnabled(false);
		
		drive.setMaxOutput(0.8);
		driveSlave.setMaxOutput(0.8);
	}
}

