package org.usfirst.frc.team4121.robot.subsystems;

import org.usfirst.frc.team4121.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	Shifter subsystem
 *
 *	@author Ben Hayden
 */
public class ShifterSubsystem extends Subsystem {
	
	//Initializes the compressor
	//I do not know if we need this or not
	Compressor compressor = new Compressor(RobotMap.COMPRESSOR);
	
	//initializes the double solenoid
	public DoubleSolenoid shifterSolenoid = new DoubleSolenoid(0,1);
	
    public void initDefaultCommand() {
    }
    
    //Changes the solenoid to kReverse or slow mode
    public void shiftUp() {
    	shifterSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    //Changes the solenoid to kForward or fast mode
    public void shiftDown() {
    	shifterSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    //Makes the gear position default to kForward or fast
    //Unused right now
    public void defaultGearPosition() {
    	shifterSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    //Returns what gear position we are in (used in the Smart Dashboard)
    public String gearPosition() {
    	if(shifterSolenoid.get() == DoubleSolenoid.Value.kForward) {
    		return "Fast";
    	}
    	else if(shifterSolenoid.get() == DoubleSolenoid.Value.kReverse) {
    		return "Slow";
    	}
    	return "Neither";
    }
}

