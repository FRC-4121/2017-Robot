package org.usfirst.frc.team4121.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4121.robot.commands.ClimbCommand;
import org.usfirst.frc.team4121.robot.commands.FeedCommand;
import org.usfirst.frc.team4121.robot.commands.ShootCommand;

/**
 * This is the main class that initializes and tells what buttons to do what.
 * 
 * @author Ben Hayden
 */
public class OI {
	
	//Initializations
	static Joystick leftJoy, rightJoy;
	Button shoot, feed, climb, shiftUp, shiftDown, gearUp, gearDown;
	
	public OI() {
	
		//Joysticks
		leftJoy = new Joystick(0);
		rightJoy = new Joystick(1);
	
		//Buttons
		shoot = new JoystickButton(rightJoy, 1);
		feed = new JoystickButton(rightJoy, 3);
		climb = new JoystickButton(leftJoy, 6);
		shiftUp = new JoystickButton(leftJoy, 5);
		shiftDown = new JoystickButton(leftJoy, 4);
		gearUp = new JoystickButton(leftJoy, 3);
		gearDown = new JoystickButton(leftJoy, 2);
		
		//Commands
		shoot.whileHeld(new ShootCommand());
		feed.whileHeld(new FeedCommand());
		climb.whileHeld(new ClimbCommand());
	
	}
}
