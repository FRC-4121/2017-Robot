package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {
	double distance;
	double direction; //-1=Reverse, +1=Forward(reverse is for gear forward is for shooting)
	
    public AutoDrive(double dis, double dir) { //add to smartdashboard
    	distance = dis;
    	direction = dir;
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.autoDrive(direction*RobotMap.DRIVE_SPEED, direction*RobotMap.DRIVE_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean thereYet = false;
    	if (distance <= ((Robot.oi.LeftEncoder.getDistance()+Robot.oi.RightEncoder.getDistance())/2.0))
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
    	Robot.oi.LeftEncoder.reset();
    	Robot.oi.RightEncoder.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
