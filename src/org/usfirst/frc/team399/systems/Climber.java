package org.usfirst.frc.team399.systems;

import org.usfirst.frc.team399.config.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

public class Climber {

	CANTalon pivotRight;
	CANTalon pivotLeft; 
	CANTalon winchRight;
	CANTalon winchLeft;
	Solenoid secondReleaseRight; 
	Solenoid secondReleaseLeft; 
	
	DriveGearbox controllers; 
	
	int currState = 0; 
	int prevState = 0; 
	
	double output = 0.0;
	double goal = 0.0;
	double error = 0.0;
	double prevError = 0.0;
	double sumError = 0.0; 
	
	public static class States {
		public static final int RIGHT_TRIANGLE = 0; 
		public static final int OBTUSE_TRIANGLE = 1;
		public static final int GRAB_ANGLE = 2; 
	}
	

	/**
	 * Constructor for the Climber 
	 * 
	 * @param pR Pivot Right 
	 * @param pL Pivot Left
	 * @param wR Winch Right
	 * @param wL Winch Left
	 * @param sRR Second Release Right (Solenoids)
	 * @param sRL Second Release Left (Solenoids)
	 */
	public Climber(int pR, int pL, int wR, int wL, int sRR, int sRL){
		pivotRight = new CANTalon(pR);
		pivotLeft = new CANTalon(pL);
		winchRight = new CANTalon(wR);
		winchLeft = new CANTalon(wL);
		secondReleaseRight = new Solenoid(sRR);
		secondReleaseLeft = new Solenoid(sRL);
	}
	
	/**
	 * Setting the power for the climbers pivot
	 * 
	 * @param pivotVal
	 */
	public void setPivotClimber(double pivotVal) {
		pivotRight.set(pivotVal);
		pivotLeft.set(-pivotVal);
	}
	
	
	/**
	 * Setting the power for the winch 
	 * 
	 * @param winchVal
	 */
	public void setWinch(double winchVal) {
		winchRight.set(winchVal);
		winchLeft.set(-winchVal);
	}
	 /**
	  * Setting the solenoids to a certain state 
	  * 
	  * @param answer
	  */
	public void setReleases(boolean answer){
		secondReleaseRight.set(answer);
		secondReleaseLeft.set(answer);
	}

	/**
	 * Set the state to what you want 
	 * 
	 * @param state
	 */
	public void setState(int state) {
		prevState = currState; 
		currState = state; 
	}
	
	/**
	 * Resetting the values that change over time
	 */
	public void resetValues() {
		goal = 0.0;
		error = 0.0; 
		prevError = 0.0; 
		sumError = 0.0; 
	}

	/**
	 * Lets the robot run through the states 
	 */
	public void run() {
		switch(currState) {
			case States.RIGHT_TRIANGLE: 
				goal = Constants.Climber.RIGHT_PIVOT_POS;
				output = pidControl(Constants.Climber.RIGHT_PIVOT_P, Constants.Climber.RIGHT_PIVOT_I, Constants.Climber.RIGHT_PIVOT_D);
				break; 
			case States.OBTUSE_TRIANGLE:
				goal = Constants.Climber.OBTUSE_PIVOT_POS;
				output = pidControl(Constants.Climber.OBTUSE_PIVOT_P, Constants.Climber.OBTUSE_PIVOT_I, Constants.Climber.OBTUSE_PIVOT_D);
				break; 
			case States.GRAB_ANGLE: 
				goal = Constants.Climber.GRAB_PIVOT_POS;
				output = pidControl(Constants.Climber.GRAB_PIVOT_P, Constants.Climber.GRAB_PIVOT_I, Constants.Climber.GRAB_PIVOT_D);
				break; 
		}
		this.setPivotClimber(output);
	}
	
	/**
	 * PID Control Loop
	 * @param p Proportional
	 * @param i Intergral
	 * @param d Derivative
	 * 
	 * @return the value we need 
	 */
	public double pidControl(double p, double i, double d) {
		prevError = error; 
		error = (controllers.getEncoderDisplacement() - goal);
		
		sumError += error;
		
		double pOut = p * error; 
		double iOut = i * sumError; 
		double dOut = d * (error - prevError); 
		
		double output = pOut + iOut + dOut; 
		
		return output; 
	}
}
