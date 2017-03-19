package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class StopWithLimitSwitchCommand extends Command {

	//Declare local variables
	private double direction; //-1=Reverse, +1=Forward(reverse is for gear forward is for shooting)
	private double angle;
	private double stoptime;
	
	private PIDController pid;
	private PIDOutput pidOutput;
	
	private Timer timer = new Timer();
	
	
	//Class constructor
    public StopWithLimitSwitchCommand(double driveDir, double driveAng, double stopTime) {

    	requires(Robot.driveTrain);
    	
    	//Set local variables
    	angle= driveAng;
    	direction = driveDir;
    	stoptime = stopTime;
    	
    	//Setup PID controller
    	pidOutput = new PIDOutput() {
    		
    		@Override
    		public void pidWrite(double d) {
    			Robot.driveTrain.autoDrive(direction*RobotMap.AUTO_DRIVE_SPEED*0.55 - d, direction*RobotMap.AUTO_DRIVE_SPEED*0.55 + d);
    		}
    		
    	};
    	
    	pid = new PIDController(0.045, 0.0, 0.0, Robot.oi.MainGyro, pidOutput);
    	pid.setAbsoluteTolerance(RobotMap.ANGLE_TOLERANCE);
    	pid.setContinuous();
    	pid.setSetpoint(angle);
    	
    }

    
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	pid.reset();
        pid.enable();
        timer.reset();
        timer.start();
        
    }

    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	//Declare return flag
    	boolean shouldStop = false;
    	
    	//Check elapsed time
    	if (stoptime <= timer.get())
    	{
    		
    		//Too much time has elapsed.  Stop this command
    		pid.disable();
    		
    	}
    	else
    	{
 
    		//Check status of limit switch
        	if(Robot.oi.limitSwitch.get())
        	{
        		
        		shouldStop = true;// can chnge speeds later depending on testing
        		pid.disable();
        		
        	}
        	else //limit switch pressed
        	{
        		
        		shouldStop = false;
        	}
    		
    	}
    	
    	//Return the flag
        return shouldStop;
        
    }

    
    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.driveTrain.autoStop();

    }

    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	Robot.driveTrain.autoStop();

    }
}
