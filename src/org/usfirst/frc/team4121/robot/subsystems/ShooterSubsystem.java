package org.usfirst.frc.team4121.robot.subsystems;

import org.usfirst.frc.team4121.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	Shooter subsystem
 *
 *	@author Ben Hayden
 */

public class ShooterSubsystem extends Subsystem {
	
	public CANTalon shooter = new CANTalon(RobotMap.SHOOTER);
//changeControlMode(TalonControlMode.Speed);
	
 public void initDefaultCommand() { //speed controller
//    	
//    	shooter.changeControlMode(TalonControlMode.Speed);
//    	shooter.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//    	shooter.setF(.05);
//    	shooter.setP(0);
//    	shooter.setI(0);
//    	shooter.setD(0);
//    	shooter.configNominalOutputVoltage(0.0, 0.0);
//    	shooter.configPeakOutputVoltage(12.0, 0.0);
//    	
//    	
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

