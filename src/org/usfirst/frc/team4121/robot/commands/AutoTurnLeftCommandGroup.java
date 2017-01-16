package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTurnLeftCommandGroup extends CommandGroup {

    public AutoTurnLeftCommandGroup() {
    	addSequential(new AutoStraightCommand());
    	addSequential(new DelayCommand(3.0)); //Change later
    	addSequential(new AutoTurnLeftCommand());
    	addSequential(new DelayCommand(2.0)); //Change later
    	addSequential(new AutoStraightCommand());
    	addSequential(new DelayCommand(2.0)); //Change later
    	addSequential(new AutoStopCommand());
    	//addSequential(new ) add place gear here
    }
}
