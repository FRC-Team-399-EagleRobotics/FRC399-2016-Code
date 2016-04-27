package org.usfirst.frc.team399.commands;

import org.usfirst.frc.team399.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {

	double speed = 0.0; 
	double timeout = 0.0; 
	int state; 
	
    public IntakeCommand(double speed, double timeout, int state) {
       this.speed = speed;
       this.timeout = timeout; 
       this.state = state; 
    }

    protected void initialize() {
    	Robot.getInstance().intake.setState(state);
    	Robot.getInstance().intake.setWheelIntake(speed);
    	this.setTimeout(timeout);
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return this.isTimedOut();
    }

    protected void end() {
    	Robot.getInstance().intake.setWheelIntake(0.0);
    }

    protected void interrupted() {
    	Robot.getInstance().intake.setWheelIntake(0.0);
    }
}
