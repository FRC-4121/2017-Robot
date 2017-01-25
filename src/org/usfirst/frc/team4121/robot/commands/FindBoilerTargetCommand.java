package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4121.robot.extraClasses.VisionProcesser;

/**
 *
 */
public class FindBoilerTargetCommand extends Command {

 	public VisionProcesser vProcesser = new VisionProcesser("http://10.37.12.76"); //insert http://[camera ip address] into quotes
    public FindBoilerTargetCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	//visionsubsytem
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    // initializing int to something, how far off to left or right robot is
    	double[] visionArray;
    	double tolerance=10; //adjust later depending on testing, our accepted values, in units of screen pixels
    	boolean boilerTargetCentered= false;
    	while (!boilerTargetCentered)
    	{
    		visionArray= vProcesser.update(); 
    		
    		if(visionArray[0]< -tolerance)
    		{
    			Robot.driveTrain.autoDriveStraight(-.1, .1);
    		}
    		else if(visionArray[1]> tolerance)
    		{
    			Robot.driveTrain.autoDriveStraight(.1, -.1);
    		}
    		else //if within accepted tolerance wil exit loop
    		{
    			boilerTargetCentered=true;
    		}
    		
    	}
    	
    	
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