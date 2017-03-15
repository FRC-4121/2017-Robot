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
    	//addSequential(new AutoDrive(109, 1,0));//change to 103
    	//addSequential (new AutoTurn (-59));
    	//addParallel(new DelayCommand(10));
    	//addSequential(new AutoStopCommand());
    	
    	//addSequential(new StopWithLimitSwitchCommand(1, -59));
    	//addSequential (new DelayCommand(15));
    	//addSequential (new AutoStopCommand());
//    	addSequential(new AutoDrive(48, 1,-60));
    	//addSequential(new StopWithLimitSwitchCommand(1, -60));
    	//addSequential (new DelayCommand(15));
    	//addSequential (new AutoStopCommand());
    	//addSequential(new AutoDrive(36, 1,60));
    	addSequential(new ShiftUpCommand());
    	addSequential(new NewAutoDriveCommand(0.5, 0.5));
    	addSequential (new DelayCommand(5));
    	addSequential (new NewAutoDriveCommand(0,.5));
    	addSequential(new DelayCommand(1));
    	addSequential(new NewAutoDriveCommand(.5, .5));
    	addParallel(new NewLimitSwitchCommand());
    	addSequential(new DelayCommand(5));
    	addSequential(new AutoStopCommand());
   
    }
}
