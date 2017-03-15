package org.usfirst.frc.team4121.robot.subsystems;

import org.usfirst.frc.team4121.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LimitSwitchSubsystem extends Subsystem {

    public void initDefaultCommand() {
    }
    
    public void stopWithLimitSwitch() {
    	if(!Robot.oi.limitSwitch.get()) {
    		Robot.driveTrain.autoStop();
    	}
    }
}

