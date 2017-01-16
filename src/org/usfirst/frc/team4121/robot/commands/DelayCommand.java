package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DelayCommand extends Command {

	double time;
	Timer timer = new Timer();
	
    public DelayCommand(double time) {
        this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(time<=timer.get()) {
			return true;
		}
    	else {
    		return false;
		}
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	timer.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
