package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.subsystems.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoStraightCommand extends Command {
	
	DriveTrainSubsystem driveTrain = new DriveTrainSubsystem();

    public AutoStraightCommand() {
    	requires(Robot.driveTrain);
    	this.driveTrain = Robot.driveTrain;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveTrain.autoDriveStraight(1.0, 1.0);
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
    protected void interrupted()  {
    }
}
