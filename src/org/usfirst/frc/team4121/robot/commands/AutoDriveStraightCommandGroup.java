package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveStraightCommandGroup extends CommandGroup {

    public AutoDriveStraightCommandGroup() {
    	addSequential(new ShiftUpCommand());
    	addSequential(new AutoDrive(65, 1, 0)); //mess with distance
    	addSequential(new StopWithLimitSwitchCommand(1, 0));
    	
    	//Vision
    	//Place gear
    }
}
