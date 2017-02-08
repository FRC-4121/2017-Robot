package org.usfirst.frc.team4121.robot.subsystems;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;
import org.usfirst.frc.team4121.robot.extraClasses.VisionThreadBoiler;
import org.usfirst.frc.team4121.robot.extraClasses.VisionThreadGear;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Vision Subsystem
 * 
 * @author Ben Hayden
 */
public class VisionSubsystem extends Subsystem {
	
	//Subsystem error
	
	double visionArrayZero;
	double visionArrayOne;
	
	public void initDefaultCommand() {
	}

	public void findBoiler() {
		double tolerance = 10.0;
		synchronized(Robot.imgLock) {
			visionArrayZero=Robot.visionArray[0];
			visionArrayOne=Robot.visionArray[1];
		}
		boolean boilerTargetCentered = false;
		while (!boilerTargetCentered) {
			if (visionArrayZero < -tolerance) {
				Robot.driveTrain.autoDrive(-.1, .1);
			}
			else if (visionArrayOne > tolerance) {
				Robot.driveTrain.autoDrive(.1, -.1);
			}
			else{
				boilerTargetCentered = true;
			}
		}
	}

	/*public void findGear() { //Robots don't quit
		boolean gearTargetCentered = false;
		synchronized(Robot.imgLock) {
			visionArrayOne=Robot.visionArray[1];
		}
		while (!gearTargetCentered) {
			if (visionArrayOne == -1.0) {
				Robot.driveTrain.autoDrive(.1, -.1);
			}
			else if (visionArrayOne == 1.0) {
				Robot.driveTrain.autoDrive(-.1, .1);
			}
			else if (visionArrayOne == -5.0) {
				gearTargetCentered = true;
			}
			else {
				gearTargetCentered = true;
			}
		}
	}*/
	
	public void switchCameras() {
		if(VisionThreadBoiler.visionThread.isAlive()) {
			RobotMap.RUN_BOILER_THREAD=false;
			RobotMap.RUN_GEAR_THREAD=true;
			VisionThreadGear.startGearThread();
		}
		else if(VisionThreadGear.visionThread.isAlive()) {
			RobotMap.RUN_GEAR_THREAD=false;
			RobotMap.RUN_BOILER_THREAD=true;
			VisionThreadBoiler.startBoilerThread();
		}
	}
}
