package org.usfirst.frc.team4121.robot.subsystems;

//import java.awt.Robot;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.extraClasses.VisionProcessor;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Vision subsystem
 * 
 * @author Matt Dray
 */
public class VisionSubsystem extends Subsystem {

	public VisionProcessor vProcessor = new VisionProcessor(0);

	public void initDefaultCommand() {
	}

	public void findGear() {
		double[] visionArray;

		boolean gearTargetCentered = false;
		while (!gearTargetCentered) {
			visionArray = vProcessor.update();

			if (visionArray[1] == -1) {
				Robot.driveTrain.autoDriveStraight(-.1, .1);
			}
			else if (visionArray[1] == 1) {
				Robot.driveTrain.autoDriveStraight(.1, -.1);
			}
			else if (visionArray[1] == -5) {
				gearTargetCentered = true;
			}
			else {
				gearTargetCentered = true;
			}
		}
	}

	public void findBoiler() {
		double[] visionArray;
		double tolerance = 10; // adjust later depending on testing, our
								// accepted values, in units of screen pixels
		boolean boilerTargetCentered = false;
		while (!boilerTargetCentered) {
			visionArray = vProcessor.update();

			if (visionArray[0] < -tolerance) {
				Robot.driveTrain.autoDriveStraight(-.1, .1);
			}
			else if (visionArray[1] > tolerance) {
				Robot.driveTrain.autoDriveStraight(.1, -.1);
			} 
			else { // if within accepted tolerance wil exit loop
				boilerTargetCentered = true;
			}

		}
	}
}
