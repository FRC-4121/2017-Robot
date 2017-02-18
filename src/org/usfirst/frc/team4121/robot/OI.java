package org.usfirst.frc.team4121.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


import org.usfirst.frc.team4121.robot.commands.ClimbCommand;
import org.usfirst.frc.team4121.robot.commands.DecreaseShootSpeedCommand;
import org.usfirst.frc.team4121.robot.commands.FeedCommand;
import org.usfirst.frc.team4121.robot.commands.FindBoilerTargetCommand;
import org.usfirst.frc.team4121.robot.commands.FindGearTargetCommand;
import org.usfirst.frc.team4121.robot.commands.IncreaseShootSpeedCommand;
import org.usfirst.frc.team4121.robot.commands.ShiftDownCommand;
import org.usfirst.frc.team4121.robot.commands.ShiftUpCommand;
import org.usfirst.frc.team4121.robot.commands.ShootCommand;
import org.usfirst.frc.team4121.robot.commands.StopShootCommand;
import org.usfirst.frc.team4121.robot.commands.SwitchCameraCommand;
import org.usfirst.frc.team4121.robot.commands.SwitchCommandGroup;
import org.usfirst.frc.team4121.robot.commands.SwitchDriveCommand;

/**
 * This is the main class that initializes and tells what buttons to do what.
 * 
 * @author Ben Hayden
 */
public class OI {
	
	//Initializations
	public Joystick leftJoy, rightJoy;
	//public Encoder LeftEncoder, RightEncoder;
	public DigitalInput limitSwitch;
	public AnalogGyro MainGyro;
	public AnalogInput RightEncoder, LeftEncoder;
	public AnalogTrigger leftTrigger, rightTrigger; 
	public Counter leftCounter, rightCounter;
	Button shoot, feed, climb, shiftUp, shiftDown, gear, boiler, switchDrive, increaseShootSpeed, decreaseShootSpeed;
	
	public OI() {
	
		//Encoders
		LeftEncoder = new AnalogInput(1);//change port
		RightEncoder = new AnalogInput(2);
		
		//trigger
		leftTrigger = new AnalogTrigger(LeftEncoder);
		leftTrigger.setLimitsVoltage(0, 4.9);//change voltage later depending on encoders
		rightTrigger = new AnalogTrigger(RightEncoder);
		rightTrigger.setLimitsVoltage(0, 4.9);//change voltage later depending on encoders
		
		//counters
		leftCounter = new Counter(leftTrigger);
		leftCounter.setDistancePerPulse(14.2);//change later
		rightCounter = new Counter(rightTrigger);
		rightCounter.setDistancePerPulse(14.2);//change later
		
//		LeftEncoder.setDistancePerPulse(15);//change later after calculating diameter
//		RightEncoder = new Encoder(2,3, false, Encoder.EncodingType.k4X);//change last thing later too
//		RightEncoder.setDistancePerPulse(15);//change later after calculating diameter
//		LeftEncoder = new AnalogInput(1);
//		LeftEncoder.
		
		
		//limitSwitch
		limitSwitch = new DigitalInput(4);      
		
		//gyro
		MainGyro = new AnalogGyro(0);
		
		//Joysticks
		leftJoy = new Joystick(0);
		rightJoy = new Joystick(1);
	
		//Buttons
		shoot = new JoystickButton(rightJoy, 1);
		decreaseShootSpeed = new JoystickButton (rightJoy, 2);
		increaseShootSpeed = new JoystickButton (rightJoy, 3);
		switchDrive = new JoystickButton(rightJoy, 4);
		//feed = new JoystickButton(rightJoy, 3);
		gear = new JoystickButton(leftJoy, 2);
		boiler = new JoystickButton(leftJoy, 3);
		shiftDown = new JoystickButton(leftJoy, 4);
		shiftUp = new JoystickButton(leftJoy, 5);
		climb = new JoystickButton(leftJoy, 6);
	
		
	
		//Commands
		shoot.whileHeld(new ShootCommand());
		shoot.whenReleased(new StopShootCommand());
		//feed.whileHeld(new FeedCommand());
		climb.whileHeld(new ClimbCommand());
		shiftUp.whenActive(new ShiftUpCommand());
		shiftDown.whenActive(new ShiftDownCommand());
		gear.whenPressed(new FindGearTargetCommand());
		boiler.whenPressed(new FindBoilerTargetCommand());
		switchDrive.whenPressed(new SwitchDriveCommand());
		decreaseShootSpeed.whenPressed(new DecreaseShootSpeedCommand());
		increaseShootSpeed.cancelWhenPressed(new IncreaseShootSpeedCommand());
		
		/*public Joystick getLeftJoy() {
			return leftJoy;
		}
		
		public Joystick getRightJoy() {
			return rightJoy;
		}*/
		
	}
}
