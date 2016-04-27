package org.usfirst.frc.team399.auton;

import org.usfirst.frc.team399.commands.IntakeCommand;
import org.usfirst.frc.team399.commands.TankDrive;
import org.usfirst.frc.team399.commands.WaitCommand;
import org.usfirst.frc.team399.systems.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossAndTurn extends CommandGroup {
    
    public  CrossAndTurn() {
    	this.addSequential(new IntakeCommand(0.0, 0.5, Intake.State.STOW));
        this.addSequential(new TankDrive(-0.8, -0.8, 2.0));
        this.addSequential(new WaitCommand(0.5));
        this.addSequential(new TankDrive(0.0, 0.5, 1.0));
    }
}
