package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4121.robot.extraClasses.VisionProcesser;


/**
 *
 */
public class FindGearTargetCommand extends Command {
	//public VisionProcesser vProcesser = new VisionProcesser("http://10.37.12.76"); //insert http://[camera ip address] into quotes

    public FindGearTargetCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/*double[] visionArray;
    	
    	boolean gearTargetCentered= false;
    	while (!gearTargetCentered)
    	{
    		visionArray= vProcesser.update(); //fix this to macth Matt's code
    		
    		
    		if(visionArray[1]==-1)
    		{
    			Robot.driveTrain.autoDriveStraight(-.1, .1);
    		}
    		else if(visionArray[1]==1)
    		{
    			Robot.driveTrain.autoDriveStraight(.1, -.1);
    		}
    		else  if(visionArray[1]==-5) //accounts for errors, calculated in Matt's program
    		{
    			gearTargetCentered=true;
    		}
    		else
    		{
    			gearTargetCentered=true;
    		}
    		
    	}*/
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }
    
    public boolean isLinedUp()
    {
		return isFinished();
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
