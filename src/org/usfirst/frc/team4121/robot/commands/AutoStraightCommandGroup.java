package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStraightCommandGroup extends CommandGroup {

    public AutoStraightCommandGroup() {
    	addSequential(new AutoStraightCommand());
    	addSequential(new DelayCommand(3)); //Change later
    	addSequential(new AutoStopCommand());
    }
}
