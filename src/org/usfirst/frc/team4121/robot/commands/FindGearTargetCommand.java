package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class FindGearTargetCommand extends Command {


//	private PIDController pid;
//	private PIDOutput pidOutput;
//	
	private double directionMultiplier;
	private double targetError;
	
    public FindGearTargetCommand() {
//    	//requires(Robot.visionSub);
    	requires(Robot.driveTrain);
//    	
//    	//set up PID controller
//    	pidOutput = new PIDOutput() {
//    		
//    		@Override
//    		public void pidWrite(double d) {
//    			Robot.driveTrain.autoDrive(-d*.8, d*.8);
//    		}
//    	};
//    	pid.
//    	pid = new PIDController(0.05, 0.0, 0.0, Robot.oi.MainGyro, pidOutput);
//    	pid.setAbsoluteTolerance(RobotMap.ANGLE_TOLERANCE);
//    	pid.setContinuous();
//    	pid.setSetpoint(angle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	directionMultiplier=0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    
 
		synchronized(Robot.imgLock) {
			targetError=Robot.visionArray[0];
		}
		SmartDashboard.putString("Target Error:", Double.toString(targetError));
		if(targetError<0)
		{
			directionMultiplier=1;
		}
		else
		{
			directionMultiplier = -1;
		}
		
		Robot.driveTrain.autoDrive(.4*directionMultiplier, -.4*directionMultiplier);
			
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(targetError)<10)//in pixels
    	{
    		return true;
    	}
    	else
    	{
    		 return false;
    	}
      
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
