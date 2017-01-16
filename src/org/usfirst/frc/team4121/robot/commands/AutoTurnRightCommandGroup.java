package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTurnRightCommandGroup extends CommandGroup {

    public AutoTurnRightCommandGroup() {
    	addSequential(new AutoStraightCommand());
    	addSequential(new DelayCommand(3.0)); //Change later
    	addSequential(new AutoTurnRightCommand());
    	addSequential(new DelayCommand(2.0)); //Change later
    	addSequential(new AutoStraightCommand());
    	addSequential(new DelayCommand(2.0)); //Change later
    	addSequential(new AutoStopCommand());
    	//addSequential(new ) add place gear here
    }
}
