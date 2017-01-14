package org.usfirst.frc.team4121.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {

	private CANTalon motor;
	
	public ClimberSubsystem(CANTalon motor) {
		this.motor = motor;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void climb() {
    	
    }
}

