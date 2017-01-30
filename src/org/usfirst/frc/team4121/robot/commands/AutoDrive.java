package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.OI;
import org.usfirst.frc.team4121.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {
	double distance;
    public AutoDrive(double d) {
    	distance = d;
    	requires(Robot.driveTrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.newAutoDrive(.5, .5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean thereYet = false;
    	if (distance <= Robot.oi.getLeftEncoder().getDistance())
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
    	Robot.driveTrain.autoStop();
    	Robot.oi.getLeftEncoder().reset();
    
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
