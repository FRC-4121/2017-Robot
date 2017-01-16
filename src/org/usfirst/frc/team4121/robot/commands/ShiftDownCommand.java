package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.subsystems.ShifterSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4121.robot.Robot;

/**
 *
 */
public class ShiftDownCommand extends Command {
	
	ShifterSubsystem shifter;
	
    public ShiftDownCommand() {
    	requires(Robot.shifter);
    	this.shifter = Robot.shifter;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shifter.shiftDown();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
