package org.usfirst.frc.team4121.robot.subsystems;

import org.usfirst.frc.team4121.robot.RobotMap;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Climber subsystem
 * 
 * @author Salivyia Crestpan
 */
public class ClimberSubsystem extends Subsystem {
	public CANTalon climb = new CANTalon(RobotMap.CLIMBER);

    public void initDefaultCommand() {
    	
    }
    
    public void climb(double climbspeed) {
    	climb.set(climbspeed);
    	
    }
}