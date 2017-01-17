package org.usfirst.frc.team4121.robot.subsystems;

import org.usfirst.frc.team4121.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShifterSubsystem extends Subsystem {
	
	Compressor compressor = new Compressor(RobotMap.COMPRESSOR); //I do not know if we need this or not
	
	public DoubleSolenoid shifterSolenoid = new DoubleSolenoid(0,1);
	
    public void initDefaultCommand() {
        //Only set it if we have multiple commands for one subsystem and want one command to always be running but have the option to run other commands
    	//setDefaultCommand(new MySpecialCommand());
    }
    
    public void shiftUp() {
    	shifterSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void shiftDown() {
    	shifterSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void defaultGearPosition() {
    	shifterSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public String gearPosition() {
    	if(shifterSolenoid.get() == DoubleSolenoid.Value.kForward) {
    		return "kForward (Change later)";
    	}
    	else if(shifterSolenoid.get() == DoubleSolenoid.Value.kReverse) {
    		return "kReverse (Change later)";
    	}
    	return "Neither";
    }
}

