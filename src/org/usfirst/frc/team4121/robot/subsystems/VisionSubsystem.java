package org.usfirst.frc.team4121.robot.subsystems;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.extraClasses.VisionProcessor;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Vision Subsystem
 * 
 * @author Matt Dray
 */
public class VisionSubsystem extends Subsystem {

	private VisionProcessor shooterCamera = new VisionProcessor(0);
	private VisionProcessor gearCamera = new VisionProcessor(1);

	public void initDefaultCommand() {
	}

	public void findBoiler() {
		double[] visionArray;
		double tolerance = 10; // adjust later depending on testing, our
								// accepted values, in units of screen pixels
		boolean boilerTargetCentered = false;
		while (!boilerTargetCentered) {
			visionArray = shooterCamera.update(); // fix this to macth Matt's code

			if (visionArray[0] < -tolerance) {
				Robot.driveTrain.autoDriveStraight(-.1, .1);
			} else if (visionArray[1] > tolerance) {
				Robot.driveTrain.autoDriveStraight(.1, -.1);
			} else // if within accepted tolerance wil exit loop
			{
				boilerTargetCentered = true;
			}

		}
	}

	public void findGear() {
		double[] visionArray;

		boolean gearTargetCentered = false;
		while (!gearTargetCentered) {
			visionArray = gearCamera.update(); // fix this to macth Matt's code

			if (visionArray[1] == -1) {
				Robot.driveTrain.autoDriveStraight(.1, -.1);
			} else if (visionArray[1] == 1) {
				Robot.driveTrain.autoDriveStraight(-.1, .1);
			} else if (visionArray[1] == -5) // accounts for errors, calculated
												// in Matt's program
			{
				gearTargetCentered = true;
			} else {
				gearTargetCentered = true;
			}

		}
	}
	
	public VisionProcessor getGearProcessor() {
		return gearCamera;
	}
	
	public VisionProcessor getShooterProcessor() {
		return shooterCamera;
	}
}
