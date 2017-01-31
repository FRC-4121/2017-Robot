package org.usfirst.frc.team4121.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4121.robot.commands.ClimbCommand;
import org.usfirst.frc.team4121.robot.commands.FeedCommand;
import org.usfirst.frc.team4121.robot.commands.FindBoilerTargetCommand;
import org.usfirst.frc.team4121.robot.commands.FindGearTargetCommand;
import org.usfirst.frc.team4121.robot.commands.ShiftDownCommand;
import org.usfirst.frc.team4121.robot.commands.ShiftUpCommand;
import org.usfirst.frc.team4121.robot.commands.ShootCommand;
import org.usfirst.frc.team4121.robot.commands.SwitchControlsCommand;

/**
 * This is the main class that initializes and tells what buttons to do what.
 * 
 * @author Ben Hayden
 */
public class OI {
	
	//Initializations
	static Joystick leftJoy, rightJoy;
	static Encoder LeftEncoder, RightEncoder;
	Button shoot, feed, climb, shiftUp, shiftDown, gear, boiler, switchControls;
	
	public OI() {
	
		//Encoders
		Encoder LeftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);//change last thing later too
		LeftEncoder.setDistancePerPulse(15);//change later after calculating diameter
		Encoder RightEncoder= new Encoder(2,3, false, Encoder.EncodingType.k4X);//change last thing later too
		RightEncoder.setDistancePerPulse(15);//change later after calculating diameter
		
		//Joysticks
		leftJoy = new Joystick(0);
		rightJoy = new Joystick(1);
	
		//Buttons
		shoot = new JoystickButton(rightJoy, 1);
		feed = new JoystickButton(rightJoy, 3);
		climb = new JoystickButton(leftJoy, 6);
		shiftUp = new JoystickButton(leftJoy, 5);
		shiftDown = new JoystickButton(leftJoy, 4);
		gear = new JoystickButton(leftJoy, 2);
		boiler = new JoystickButton(rightJoy, 2);
		switchControls = new JoystickButton(rightJoy, 6);
		
		//Commands
		shoot.whileHeld(new ShootCommand());
		//feed.whileHeld(new FeedCommand());
		climb.whileHeld(new ClimbCommand());
		shiftUp.whenActive(new ShiftUpCommand());
		shiftDown.whenActive(new ShiftDownCommand());
		gear.whenPressed(new FindGearTargetCommand());
		boiler.whenPressed(new FindBoilerTargetCommand());
		switchControls.whenPressed(new SwitchControlsCommand());
		
	}
	public Encoder getLeftEncoder()
	{
		return LeftEncoder;
	}
	public Encoder getRightEncoder()
	{
		return RightEncoder;
	}
	
	public Joystick getLeftJoy() {
		return leftJoy;
	}
	
	public Joystick getRightJoy() {
		return rightJoy;
	}
	
}
