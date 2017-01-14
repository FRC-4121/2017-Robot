package org.usfirst.frc.team4121.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShifterSubsystem extends Subsystem {
	Compressor compressor = new Compressor(0); //I do not know if we need this or not
	
	private DoubleSolenoid shifterSolenoid;
	
	public ShifterSubsystem(DoubleSolenoid shifter) {
		this.shifterSolenoid = shifter;
	}
	
    public void initDefaultCommand() {
        //Only set it if we have multiple commands for one subsystem and want one command to always be running but have the option to run other commands
    	//setDefaultCommand(new MySpecialCommand());
    }
    
    public void shiftToggle() {
    	int i = 0;
    	
    	while(true) {
    		if(i == 0) {
    			shifterSolenoid.set(DoubleSolenoid.Value.kReverse); //faster
    			i++;
    		}
    		else {
    			shifterSolenoid.set(DoubleSolenoid.Value.kForward); //slower
    			i--;
    		}
    	}
    }
    
    public Value getPosition() {
    	return shifterSolenoid.get(); //If we figure out what this returns we may be able to make shiftToggle a lot more simple
    }
    
    public void defaultGearPosition() {
    	shifterSolenoid.set(DoubleSolenoid.Value.kForward);
    }
}

