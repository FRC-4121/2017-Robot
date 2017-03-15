package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NewDriveStraightCommandGoup extends CommandGroup {

    public NewDriveStraightCommandGoup() {
    	addSequential(new ShiftUpCommand());
    	addSequential(new NewAutoDriveCommand(0.5, 0.5));
    	addParallel(new NewLimitSwitchCommand());
    	addSequential(new DelayCommand(8));
    	addSequential(new AutoStopCommand());
    }
    
}
