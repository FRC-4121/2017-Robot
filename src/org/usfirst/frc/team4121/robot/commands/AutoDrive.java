package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {
	double distance; //Make global
	double direction; //-1=Reverse, +1=Forward(reverse is for gear forward is for shooting)
	
    public AutoDrive(double dis, double dir) { //intakes distance and direction
    	distance = dis;
    	direction = dir;
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.autoDrive(direction*RobotMap.DRIVE_SPEED, direction*RobotMap.DRIVE_SPEED);//sets direction of driving
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean thereYet = false;
    	if (distance <= ((Robot.oi.LeftEncoder.getDistance()+Robot.oi.RightEncoder.getDistance())/2.0)) //averages the encoders
    	{
    		thereYet = true;//can change is encoder counts down not up
    	}
    	else
    	{
    		thereYet = false;
    	}
    	return thereYet;
       
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.autoStop(); //maybe don't need depends on robot
    	Robot.oi.LeftEncoder.reset();//resets encoders
    	Robot.oi.RightEncoder.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
