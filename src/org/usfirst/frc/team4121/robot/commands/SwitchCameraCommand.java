package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Switches from front camera to rear camera or other way around
 * 
 * @author Ben Hayden
 */
public class SwitchCameraCommand extends Command {

    public SwitchCameraCommand() {
    	requires(Robot.visionSub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.visionThread.switchCameras();
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
