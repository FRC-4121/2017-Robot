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
public class AutoTurn extends Command {
	double stopAngle;

	
	private PIDController pid;
	private PIDOutput pidOutput;
	
    public AutoTurn(double angle) { //change in smartdashboard
    	stopAngle = angle;
   
    	requires(Robot.driveTrain);
    	
    	//set up PID controller
	pidOutput = new PIDOutput() {
    		
    		@Override
    		public void pidWrite(double d) {
    			Robot.driveTrain.autoDrive(-d*0.7, d*0.7);
    		}
    	};
    	
    	pid = new PIDController(0.05, 0.0, 0.0, Robot.oi.MainGyro, pidOutput);
    	pid.setAbsoluteTolerance(RobotMap.ANGLE_TOLERANCE);
    	pid.setContinuous();
    	pid.setSetpoint(angle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
         pid.reset();
         pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	Robot.driveTrain.autoDrive(RobotMap.AUTO_TURN_SPEED*turnDirection, RobotMap.AUTO_TURN_SPEED*-turnDirection);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	boolean thereYet = false;
//    	if(stopAngle <= Math.abs((Robot.oi.MainGyro.getAngle())))
//    	{
//    		pid.disable();
//    		thereYet = true;//can change is encoder counts down not up
//    	}
//    	else
//    	{
//    		thereYet = false;
//    	}
    	SmartDashboard.putString("Angle Reached Yet:", Boolean.toString(pid.onTarget()));
//    	return thereYet;
//    	if(pid.onTarget())
//    	{
//    		pid.disable();
//    	}
    	return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    	Robot.driveTrain.autoStop(); //maybe don't need depends on robot
    	Robot.oi.leftCounter.reset();
    	Robot.oi.rightCounter.reset();
    	//Robot.oi.MainGyro.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
