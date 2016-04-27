package org.usfirst.frc.team399.commands;

import org.usfirst.frc.team399.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightForward extends Command{

	double timout; 
	double distance;
	double speedLimit; 
	
	public DriveStraightForward(double timeout, double distance, double speedLimit) {
		this.timout = timeout; 
		this.distance = Robot.getInstance().drivetrain.inchesToTicks(distance); 
		this.speedLimit = speedLimit;
	}
	
	@Override
	protected void initialize() {
		this.setTimeout(timout);
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
	
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}

}
