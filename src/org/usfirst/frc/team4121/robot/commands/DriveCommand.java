package org.usfirst.frc.team4121.robot.commands;

import java.awt.Robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4121.robot.OI;
import org.usfirst.frc.team4121.robot.subsystems.DriveTrainSubsystem;

/**
 *
 */
public class DriveCommand extends Command {

	//DriveTrainSubsystem driveTrain = new DriveTrainSubsystem();
	
	public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//driveTrain.tankDrive(OI.getLeftJoy().getY(), OI.getRightJoy().getY());
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
