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
        
    }

    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Robot.driveTrain.autoDrive(direction*RobotMap.AUTO_DRIVE_SPEED, direction*RobotMap.AUTO_DRIVE_SPEED);//sets direction of driving
    	
    }

    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	boolean thereYet = false;
    	
    	double leftDistance = Robot.oi.leftCounter.getDistance() + 2.84 * Robot.oi.LeftEncoder.getAverageVoltage();
    	double rightDistance = Robot.oi.rightCounter.getDistance() + 2.84 * Robot.oi.RightEncoder.getAverageVoltage();
    	Robot.distanceTraveled = (leftDistance + rightDistance) / 2.0;
    	
    	if (distance <= Robot.distanceTraveled)
    	{
    		//RobotMap.AUTO_DRIVE_SPEED = 0.0;
    		pid.disable();
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
