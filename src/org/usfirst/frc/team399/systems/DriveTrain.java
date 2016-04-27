package org.usfirst.frc.team399.systems;

import org.usfirst.frc.team399.config.Constants;

public class DriveTrain {

	DriveGearbox controllers; 
	
	/**
	 * Constructor for the DriveTrain
	 * 
	 * @param controllers 
	 */
	public DriveTrain(DriveGearbox controllers) {
		this.controllers = controllers; 
	}
	
	/**
	 * Setting the outputs for the TankDrive
	 * 
	 * @param leftPower 
	 * @param rightPower 
	 */
	public void tankDrive(double leftPower, double rightPower) {

		if(Math.abs(leftPower) < 0.1){
			leftPower = 0; 
		}
		if(Math.abs(rightPower) < 0.1) {
			rightPower = 0; 
		}
		
		controllers.leftA.set(-leftPower);
		controllers.leftB.set(-leftPower);
		controllers.leftC.set(-leftPower);
		controllers.rightA.set(rightPower);
		controllers.rightB.set(rightPower);
		controllers.rightC.set(rightPower);
	}
	
	/**
	 * Arcade Drive function that takes throttle and turning values and 
	 * converts them to right and left drive controls
	 * 
	 * @param throttle
	 * @param turning
	 */
	public void arcadeDrive(double throttle, double turning) {
		tankDrive(throttle + turning, throttle - turning);
	}
	
	/**
	 * Changed the inches to ticks for the encoder
	 * 
	 * @param inches we want to move
	 * @return how many ticks that is
	 */
	public double inchesToTicks(double inches) {
		return inches * Constants.DriveTrain.TICKS_TO_INCHES;
	}
}