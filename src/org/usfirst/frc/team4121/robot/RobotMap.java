package org.usfirst.frc.team4121.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author Ben Hayden
 */
public class RobotMap {
	public static final int LEFT_MOTOR_1 = 0;
	public static final int LEFT_MOTOR_2 = 1;
	public static final int LEFT_MOTOR_3 = 2;
	public static final int RIGHT_MOTOR_1 = 3;
	public static final int RIGHT_MOTOR_2 = 4;
	public static final int RIGHT_MOTOR_3 = 5;
	
	public static final int CLIMBER = 6;
	
	public static final int FEEDER = 7;
	
	public static final int SHOOTER = 8;
	
	public static final double DRIVE_SPEED = 1.0;
	
	public static int DIRECTION_MULTIPLIER = -1;
	
	public static final int DIRECTION_FORWARD = 1;
	public static final int DIRECTION_REVERSE = -1;
	
	public static final int COMPRESSOR = 0;
}
