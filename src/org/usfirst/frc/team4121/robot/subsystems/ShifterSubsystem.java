package org.usfirst.frc.team4121.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShifterSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	DoubleSolenoid gearShift = new DoubleSolenoid(0, 1);
	
	Compressor compressor = new Compressor(0);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void shiftToggle() {
    	
    	gearShift.set(DoubleSolenoid.Value.kReverse);
    	/*int i = 0;
    	
    	while(true)
    	{
    		if(i == 0)
    		{
    			//faster
    			gearShift.set(DoubleSolenoid.Value.kReverse);
    			i++;
    		}
    		else
    		{
    			//slower
    			gearShift.set(DoubleSolenoid.Value.kForward);
    			i--;
    		}
    	}*/
    }
    
    /*public void startCompressor() {
    	compressor.setClosedLoopControl(true);
    }*/
    
    public void defaultGearPosition() {
    	gearShift.set(DoubleSolenoid.Value.kForward);
    }
}

