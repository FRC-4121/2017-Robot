package org.usfirst.frc.team4121.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4121.robot.commands.ClimbCommand;
import org.usfirst.frc.team4121.robot.commands.ExampleCommand;
import org.usfirst.frc.team4121.robot.commands.FeedCommand;
import org.usfirst.frc.team4121.robot.commands.PlaceGearCommand;
import org.usfirst.frc.team4121.robot.commands.ShootCommand;

/**
 * This is the main class that initializes and tells what buttons to do what.
 * 
 * @author Ben Hayden
 */
public class OI {
	
	//Initializations
	Joystick leftJoy, rightJoy;
	Button shoot, feed, climb, shiftUp, shiftDown, gearUp, gearDown;
	
	public OI() {
	
		//Joysticks\
	
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
	
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	}
	
	public Joystick getLeftJoy()
	{
		return leftJoy;
	}
	
	public Joystick getRightJoy()
	{
		return rightJoy;
	}
	
}
