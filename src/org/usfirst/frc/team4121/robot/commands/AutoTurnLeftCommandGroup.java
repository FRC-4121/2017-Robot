package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTurnLeftCommandGroup extends CommandGroup {

    public AutoTurnLeftCommandGroup() {
    	//addSequential(new AutoDrive(distance, -1));
    	//addSequential(new AutoTurn(60, -1));
    	//addSequential(new AutoDrive(distance, -1));
    	//Vision
    	//Place gear
    	//start 41.5 from center line
    	addSequential(new AutoDrive(107, 1,0));
    	addSequential (new AutoTurn (-59));
//    	addSequential(new AutoDrive(48, 1,-60));
    	//addSequential(new StopWithLimitSwitchCommand(1, -60));
    	//addSequential (new DelayCommand(15));
    	//addSequential (new AutoStopCommand());
    	//addSequential(new AutoDrive(36, 1,60));
   
    }
}
