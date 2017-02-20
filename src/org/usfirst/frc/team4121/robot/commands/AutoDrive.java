package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {
	
	double distance; //Make global
	double direction; //-1=Reverse, +1=Forward(reverse is for gear forward is for shooting)
	double angle;  //drive angle
	
	private PIDController pid;
	private PIDOutput pidOutput;
	
	
    public AutoDrive(double dis, double dir, double ang) { //intakes distance, direction, and angle
    	
    	requires(Robot.driveTrain);
    	
    	//Set local variables
    	distance = dis;
    	direction = dir;
    	angle = ang;
    	
    	//Set up PID control
    	pidOutput = new PIDOutput() {
    		
    		@Override
    		public void pidWrite(double d) {
    			Robot.driveTrain.autoDrive(direction*RobotMap.DRIVE_SPEED + d, direction*RobotMap.DRIVE_SPEED - d);
    		}
    	};
    	
    	pid = new PIDController(0.1, 0.0, 0.0, Robot.oi.MainGyro, pidOutput);
    	pid.setAbsoluteTolerance(RobotMap.ANGLE_TOLERANCE);
    	pid.setSetpoint(angle);
    	
    }

    
    // Called just before this Command runs the first time
    protected void initialize() {
    	
        Robot.distanceTraveled = 0;
        pid.reset();
        pid.enable();
        
    }

    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Robot.driveTrain.autoDrive(direction*RobotMap.DRIVE_SPEED, direction*RobotMap.DRIVE_SPEED);//sets direction of driving
    	
    }

    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	boolean thereYet = false;
    	
    	Robot.distanceTraveled = Robot.oi.leftCounter.getDistance() + 2.84 * Robot.oi.LeftEncoder.getAverageVoltage();
    	
    	if (distance <= Robot.distanceTraveled)
    	{
    		thereYet = true;
    	}

    	return thereYet;

    }

    
    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.driveTrain.autoStop(); //maybe don't need depends on robot
    	Robot.oi.leftCounter.reset();
    	Robot.oi.rightCounter.reset();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
