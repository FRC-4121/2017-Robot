package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {
	
	double distance; //Make global
	double direction; //-1=Reverse, +1=Forward(reverse is for gear forward is for shooting)
	double angle;  //drive angle
	double time;  //time out time
	
	private PIDController pid;
	private PIDOutput pidOutput;

	private Timer timer = new Timer();
	
	//Class constructor
    public AutoDrive(double dis, double dir, double ang, double stopTime) { //intakes distance, direction, angle, and stop time
    	
    	requires(Robot.driveTrain);
    	
    	//Set local variables
    	distance = dis;
    	direction = dir;
    	angle = ang;
    	time = stopTime;
    	
    	//Set up PID control
    	pidOutput = new PIDOutput() {
    		
    		@Override
    		public void pidWrite(double d) {
    			Robot.driveTrain.autoDrive(direction*RobotMap.AUTO_DRIVE_SPEED - d, direction*RobotMap.AUTO_DRIVE_SPEED + d);
    		}
    	};
    	
    	pid = new PIDController(0.045, 0.0, 0.0, Robot.oi.MainGyro, pidOutput);
    	pid.setAbsoluteTolerance(RobotMap.ANGLE_TOLERANCE);
    	pid.setContinuous();
    	pid.setSetpoint(angle);
    	
    }

    
    // Called just before this Command runs the first time
    protected void initialize() {
    	
        Robot.distanceTraveled = 0.0;
        pid.reset();
        pid.enable();
        timer.start();
        Robot.oi.rightEncoder.reset();
        Robot.oi.leftEncoder.reset();
        
    }

    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	    	
    }

    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	//Declare return flag
    	boolean thereYet = false;
    	
    	//Check elapsed time
    	if(time<=timer.get())
    	{
    		
    		//Too much time has elapsed.  Stop this command.
    		pid.disable();
    		thereYet = true;
    		
    	}
    	else
    	{
    		
    	
    		//Robot.distanceTraveled = (Robot.oi.leftEncoder.getDistance() + Robot.oi.rightEncoder.getDistance()) / 2.0;
    		Robot.distanceTraveled = (Robot.oi.rightEncoder.getDistance());
    		if (distance <= Robot.distanceTraveled)
    		{
    			
    			//Robot has reached its destination.  Stop this command
    			pid.disable();
    			thereYet = true;
    			
    		}
    	}

    	//Return the flag
    	return thereYet;
    	

    }

    
    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.driveTrain.autoStop(); //maybe don't need depends on robot
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	pid.disable();
    }
}
