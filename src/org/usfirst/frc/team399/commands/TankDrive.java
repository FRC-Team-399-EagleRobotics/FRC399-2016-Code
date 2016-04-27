package org.usfirst.frc.team399.commands;

import org.usfirst.frc.team399.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDrive extends Command {

	private double leftIn; 
	private double rightIn; 
	private double timeout; 
	
    public TankDrive(double leftIn, double rightIn, double timeout) {
       this.leftIn = leftIn; 
       this.rightIn = rightIn; 
       this.timeout = timeout; 
    }

    protected void initialize() {
    	Robot.getInstance().drivetrain.tankDrive(leftIn, rightIn);
    	setTimeout(timeout);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return this.isTimedOut();
    }

    protected void end() {
    	Robot.getInstance().drivetrain.tankDrive(0.0, 0.0);
    }

    protected void interrupted() {
    	Robot.getInstance().drivetrain.tankDrive(0.0, 0.0);
    }
}
