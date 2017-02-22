package org.usfirst.frc.team399.auton;

import org.usfirst.frc.team399.commands.IntakeCommand;
import org.usfirst.frc.team399.commands.ShooterCommand;
import org.usfirst.frc.team399.commands.TankDrive;
import org.usfirst.frc.team399.commands.WaitCommand;
import org.usfirst.frc.team399.systems.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarHighShot extends CommandGroup {
    
    public  LowBarHighShot() {
    	this.addParallel(new ShooterCommand(1.0, 1.0, 12.0)); 
    	this.addSequential(new TankDrive(0.5, 0.5, 2.51)); 
    	this.addSequential(new WaitCommand(0.5)); 
        this.addSequential(new TankDrive(0.0, 0.56, 0.5)); 
        this.addSequential(new TankDrive(0.5, 0.5, 0.9)); 
        this.addSequential(new WaitCommand(4.0)); 
        this.addSequential(new IntakeCommand(1.0, 1.0, Intake.State.INTAKING));
    }
}
