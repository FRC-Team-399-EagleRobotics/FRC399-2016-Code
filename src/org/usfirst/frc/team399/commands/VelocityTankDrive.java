package org.usfirst.frc.team399.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VelocityTankDrive extends Command {

	double velocity; 
	double timeout; 
	
    public VelocityTankDrive(double velocity, double timeout) {
    	this.velocity = velocity; 
    	this.timeout = timeout; 
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return this.isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
