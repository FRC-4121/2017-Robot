package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command {
	double driveSpeed=.5;
	double stopAngle;
	int turnDirection;  //-1=Left, +1=Right
    public AutoTurn(double angle, int direction) {
    	
    	stopAngle = angle;
    	turnDirection=direction;
    	requires(Robot.driveTrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.newAutoDrive(driveSpeed*turnDirection, driveSpeed*-turnDirection);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean thereYet = false;
    	if (stopAngle <= Math.abs((Robot.oi.getmainGyro().getAngle())))
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
