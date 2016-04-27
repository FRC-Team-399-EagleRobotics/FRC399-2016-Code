package org.usfirst.frc.team399.commands;

import org.usfirst.frc.team399.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterCommand extends Command {

	double topShot; 
	double botShot;
	double timeout; 
	
    public ShooterCommand(double topShot, double botShot, double timeout) {
    	this.topShot = topShot; 
    	this.botShot = botShot;
    	this.timeout = timeout; 
    }

    protected void initialize() {
    	Robot.getInstance().shooter.setShooter(topShot, botShot);
    	this.setTimeout(timeout);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return this.isTimedOut();
    }

    protected void end() {
    	Robot.getInstance().shooter.setShooter(0.0, 0.0);
    }

    protected void interrupted() {
    	Robot.getInstance().shooter.setShooter(0.0, 0.0);
    }
}
