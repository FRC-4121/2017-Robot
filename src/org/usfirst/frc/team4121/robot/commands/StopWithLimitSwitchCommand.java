package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopWithLimitSwitchCommand extends Command {

	//Robots don't quit
	
    public StopWithLimitSwitchCommand() {
      // requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//You can't keep a never ending loop in here
    	
    	while(Robot.oi.limitSwitch.get() == false)
    	{
    		Robot.driveTrain.autoDrive(-.4, -.4);// can chnge speeds later depending on testing
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.driveTrain.autoStop();
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
