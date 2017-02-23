package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Shoot command
 *
 *	@author Ben Hayden
 */
public class ShootCommand extends Command {
boolean gatesOpen;
    public ShootCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooting);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gatesOpen=false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooting.Shoot(RobotMap.SHOOTER_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(Robot.shooting.getSpeed())== Math.abs(RobotMap.SHOOTER_SPEED) && !gatesOpen)
    	{
    		Robot.shooting.openGates();
    		gatesOpen= true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
