package org.usfirst.frc.team4121.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.AnalogTriggerOutput;
import edu.wpi.first.wpilibj.AnalogTriggerOutput.AnalogTriggerType;
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
import org.usfirst.frc.team4121.robot.commands.StopClimbCommand;
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
	public DigitalInput limitSwitch;
	public ADXRS450_Gyro MainGyro;
	public AnalogInput RightEncoder, LeftEncoder;
	public AnalogTrigger leftTrigger, rightTrigger; 
	public AnalogTriggerOutput leftTriggerOutput, rightTriggerOutput;
	public Counter leftCounter, rightCounter;
	Button shoot, feed, climb, shiftUp, shiftDown, gear, boiler, switchDrive, increaseShootSpeed, decreaseShootSpeed;
	
	public OI() {
	
		//Encoders
		LeftEncoder = new AnalogInput(0);  //change port
		RightEncoder = new AnalogInput(1);
		
		//Triggers
		leftTrigger = new AnalogTrigger(LeftEncoder);
		leftTrigger.setLimitsVoltage(.5, 4.5);//change voltage later depending on encoders
		leftTriggerOutput = new AnalogTriggerOutput(leftTrigger, AnalogTriggerType.kRisingPulse);
		rightTrigger = new AnalogTrigger(RightEncoder);
		rightTrigger.setLimitsVoltage(.5, 4.5);//change voltage later depending on encoders
		rightTriggerOutput = new AnalogTriggerOutput(rightTrigger, AnalogTriggerType.kRisingPulse); 
		
		//Counters
		leftCounter = new Counter(leftTriggerOutput);
		leftCounter.setDistancePerPulse(14.2);//change later
		rightCounter = new Counter(rightTriggerOutput);
		rightCounter.setDistancePerPulse(14.2);//change later
		
		//Limit Switch
		limitSwitch = new DigitalInput(4);      
		
		//Gyro
		MainGyro = new ADXRS450_Gyro();
		
		//Joysticks
		leftJoy = new Joystick(0);
		rightJoy = new Joystick(1);
	
		//Buttons
		shoot = new JoystickButton(rightJoy, 1);
		decreaseShootSpeed = new JoystickButton (rightJoy, 2);
		increaseShootSpeed = new JoystickButton (rightJoy, 3);
		switchDrive = new JoystickButton(rightJoy, 4);
		//feed = new JoystickButton(rightJoy, 3);
		climb = new JoystickButton(leftJoy, 1);
		gear = new JoystickButton(leftJoy, 2);
		boiler = new JoystickButton(leftJoy, 3);
		shiftDown = new JoystickButton(leftJoy, 4);
		shiftUp = new JoystickButton(leftJoy, 5);
		
		//Commands
		shoot.whileHeld(new ShootCommand());
		shoot.whenReleased(new StopShootCommand());
		//feed.whileHeld(new FeedCommand());
		climb.whileHeld(new ClimbCommand());
		climb.whenReleased(new StopClimbCommand());
		shiftUp.whenActive(new ShiftUpCommand());
		shiftDown.whenActive(new ShiftDownCommand());
		gear.whenPressed(new FindGearTargetCommand());
		boiler.whenPressed(new FindBoilerTargetCommand());
		switchDrive.whenPressed(new SwitchDriveCommand());
		decreaseShootSpeed.whenPressed(new DecreaseShootSpeedCommand());
		increaseShootSpeed.whenPressed(new IncreaseShootSpeedCommand());
		
	}
}
