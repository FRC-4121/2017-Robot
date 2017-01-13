package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;
import org.usfirst.frc.team4121.robot.subsystems.DriveTrainSubsystem;

/**
 *
 */
public class AutoDriveCommand extends Command {
	private double targetDistance, timeElapsed,leftPosition,rightPosition;

    public AutoDriveCommand(double distance) 
    {
        // Use requires() here to declare subsystem dependencies
    	requires(DriveTrainSubsystem);
    	this.targetDistance = distance;
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
