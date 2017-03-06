package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTurnRightCommandGroup extends CommandGroup {

    public AutoTurnRightCommandGroup() {
     //addSequential(new AutoDrive(distance, -1));
     //addSequential(new AutoTurn(60, 1));
     //addSequential(new AutoDrive(distance, -1));
     //Vision
     //Place gear
    	//addSequential ( new AutoTurn (90, -1));
    	addSequential(new AutoDrive(110, 1,0));
    	addSequential (new AutoTurn (-59));
    	addSequential(new AutoDrive(60, 1,-59));
    	addSequential(new StopWithLimitSwitchCommand(1, -59));
    	addSequential (new DelayCommand(15));
    	addSequential (new AutoStopCommand());
    }
}
