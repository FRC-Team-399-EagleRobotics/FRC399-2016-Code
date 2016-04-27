package org.usfirst.frc.team399.auton;

import org.usfirst.frc.team399.commands.IntakeCommand;
import org.usfirst.frc.team399.commands.TankDrive;
import org.usfirst.frc.team399.systems.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowBar extends CommandGroup {
    
    public  CrossLowBar() {
    	this.addSequential(new IntakeCommand(0.0, 0.5, Intake.State.INTAKING));
        this.addSequential(new TankDrive(0.4, 0.5, 8.0));
    }
}