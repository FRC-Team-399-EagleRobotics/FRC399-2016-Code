package org.usfirst.frc.team399.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team399.commands.IntakeCommand;
import org.usfirst.frc.team399.commands.TankDrive;
import org.usfirst.frc.team399.systems.Intake;

public class BreachingDefenses extends CommandGroup {
    
    public  BreachingDefenses() {
        System.out.println("Breaching a Defense!");  
        this.addSequential(new IntakeCommand(0.0, 0.5, Intake.State.STOW));
        this.addSequential(new TankDrive(-0.9, -1.0, 10.0));
    }
}
