package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveStraightCommandGroup extends CommandGroup {

    public AutoDriveStraightCommandGroup() {
    	addSequential(new AutoDrive(11, -1)); //mess with distance
    	
    	//Vision
    	//Place gear
    }
}
