package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootCommandGroup extends CommandGroup {

    public ShootCommandGroup() {
      addSequential(new ShootCommand()); //Spin up the shooter first
      //addSequential(new wait) put a wait here
      addSequential(new OpenGateCommand());
      //Keep the gate open while button is pressed might have to have a for loop
      addSequential(new CloseGateCommand());
    }
}
