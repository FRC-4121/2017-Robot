package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.subsystems.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftCommand extends Command {

	DriveTrainSubsystem driveTrain = new DriveTrainSubsystem();
	
	DoubleSolenoid gearShift = new DoubleSolenoid(0, 1);
	
	Compressor compressor = new Compressor(0);
	
    public ShiftCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gearShift.set(DoubleSolenoid.Value.kForward);
    	
    	compressor.setClosedLoopControl(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int i = 0;
    	
    	while(true)
    	{
    		if(i == 0)
    		{
    			gearShift.set(DoubleSolenoid.Value.kReverse);
    			i++;
    		}
    		else
    		{
    			gearShift.set(DoubleSolenoid.Value.kForward);
    			i--;
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
