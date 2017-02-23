package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootCommandGroup extends CommandGroup {

    public ShootCommandGroup() {
      addSequential(new ShootCommand()); //Spin up the shooter first
      addSequential(new DelayCommand(3)); 
      addSequential(new OpenGateCommand());
      while(true){
    	  addSequential (new ShootCommand());
    	  
      }
     
      //Keep the gate open while button is pressed might have to have a for loop
//      addSequential(new CloseGateCommand());
    }
}
