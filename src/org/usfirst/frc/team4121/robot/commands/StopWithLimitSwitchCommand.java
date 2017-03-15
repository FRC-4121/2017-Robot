package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopWithLimitSwitchCommand extends Command {

	//Robots don't quit
	double direction; //-1=Reverse, +1=Forward(reverse is for gear forward is for shooting)
	double angle;
	
	private PIDController pid;
	private PIDOutput pidOutput;
	
    public StopWithLimitSwitchCommand(double driveDir, double driveAng) {
      // requires(Robot.driveTrain);
    	requires(Robot.driveTrain);
    	
    	angle= driveAng;
    	direction = driveDir;
    	
    	pidOutput = new PIDOutput() {
    		
    		@Override
    		public void pidWrite(double d) {
    			Robot.driveTrain.autoDrive(direction*RobotMap.AUTO_DRIVE_SPEED*0.55 - d, direction*RobotMap.AUTO_DRIVE_SPEED*0.55 + d);
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
    	//You can't keep a never ending loop in here
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	boolean shouldStop = false;
    	
    	if(Robot.oi.limitSwitch.get())
    	{
    		shouldStop = true;// can chnge speeds later depending on testing
    		pid.disable();
    	}
    	else //limit switch pressed
    	{
    		
    		shouldStop = false;
    	}
    
        return shouldStop;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.autoStop();
    	System.out.println("ended...");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	Robot.driveTrain.autoStop();
    	System.out.println("interrupted...");

    }
}
