package org.usfirst.frc.team4121.robot.subsystems;

import org.usfirst.frc.team4121.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	Shooter subsystem
 *
 *	@author Ben Hayden
 */

public class ShooterSubsystem extends Subsystem {
	
	CANTalon shooter = new CANTalon(RobotMap.SHOOTER);
	
    public void initDefaultCommand() {
    	
    	
    	
    }
    
public void Shoot(double shootspeed) {
    	
    	shooter.set(shootspeed);
    	
    }

public void DecreaseShootSpeed(){
		RobotMap.SHOOTER_SPEED = RobotMap.SHOOTER_SPEED + .1;
	}
public void IncreaseShootSpeed(){
	RobotMap.SHOOTER_SPEED = RobotMap.SHOOTER_SPEED - .1;
	}
}

