package org.usfirst.frc.team399.systems;

import org.usfirst.frc.team399.config.Constants;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class Intake {
	
	CANTalon pivotIntake; 
	CANTalon wheelIntake;
	Encoder pivotEncoder; 
	DigitalInput magnetSensor;  
	AnalogInput irSensor;

	int currState = 0; 
	int prevState = 0; 
	
	double output; 
	double goal = 0.0;
	double error = 0.0; 
	double prevError = 0.0; 
	double sumError = 0.0; 
	double manualIn;
	
	int ticksPerRevolution = 1024;
	int count = 1;
	int countNum; 
	int distance;
	int pivot;
	int prevVal;
	int irValue; 
	int avgValue;
	
	boolean answer;
	 
	public static class State {
		public static final int STOW = 0; 
		public static final int INTAKING = 1; 
		public static final int PORTCULLIS = 2;
		public static final int MANUAL = 3; 
	}
	
	/**
	 * Constructor for Intake
	 * 
	 * @param pI motor for the pivot
	 * @param wI motor for the wheels
	 */
	public Intake(int pI, int wI) {
		pivotIntake = new CANTalon(pI);
		wheelIntake = new CANTalon(wI);
	}
	/**
	 * Contrucotr for the Intake Sensors 
	 * @param iEA Intake encoder pivot port A
	 * @param iEB Intake encoder pivot port B
	 * @param irS IR Sensor port
	 */
	public void setSensors(int iEA, int iEB, int irS, int mS) {
		pivotEncoder = new Encoder(iEA, iEB);
		irSensor = new AnalogInput(irS);
		magnetSensor = new DigitalInput(mS);
	
	}
	
	/**
	 * Get the reading from the magnet sensor
	 * 
	 * @return
	 */
	public boolean getMagnetReading() {
		answer = magnetSensor.get();
		return answer; 
	}
	
	 /**
	  * Return the IR value
	  * 
	  * @return the IR value
	  */
	public int getIRReading() {
			irValue = irSensor.getValue();
		return irValue; 
	}
	
	/**
	 * Setting the Values for the Intake Motors
	 * 
	 * @param pivVal pivoting value
	 * @param wheVal wheel value
	 */
	public void setPivotIntake(double pivVal) {
		pivotIntake.set(-pivVal);
	}
	
	/**
	 * Set the Intake pivot to a analog stick
	 * 
	 * @param pivVal The analog stick reading
	 */
	public void setManual(double pivVal) {
		if(this.getEncoderDistance() < 0) {
			pivotIntake.set(0.0);
		} manualIn = -pivVal;
	}
	
	/**
	 * Set the value for the intake wheels 
	 * 
	 * @param wheVal what value you want
	 */
	public void setWheelIntake(double wheVal) {
		if(this.getIRReading() > Constants.Intake.IR_SENSOR_THRESHOLD && wheVal < 0) {
			wheelIntake.set(0.0);
		} else { 
			wheelIntake.set(wheVal);
		}
	}
	
	/**
	 * Reset the encoder
	 */
	public void resetSensors() {
		pivotEncoder.reset();
	}
	
	/**
	 * Get the encoder reading
	 * 
	 * @return the encoder reading
	 */
	public int getEncoderDistance() {
		distance = -pivotEncoder.get();
		return distance; 
	} 
	
	/**
	 * Reseting the values that change over time
	 */
	public void resetValues() {
		goal = 0.0;
		error = 0.0; 
		prevError = 0.0; 
		sumError = 0.0; 
		pivot = 0; 
		distance = 0;
	}
	
	/**
	 * Setting the state of the intake
	 *  
	 * @param state Wanted State
	 */
	public void setState(int state) {
		prevState = currState; 
		currState = state; 
	}
	
	/**
	 *Function that lets the robot run through the states 
	 */
	public void run() {
		switch(currState) {
			case State.STOW:
				goal = Constants.Intake.STOW_POS; 
				output = pidControl(Constants.Intake.STOW_P, Constants.Intake.STOW_I, Constants.Intake.STOW_D);
				break; 
			case State.INTAKING: 
				goal = Constants.Intake.INTAKING_POS; 
				output = pidControl(Constants.Intake.INTAKING_P, Constants.Intake.INTAKING_I, Constants.Intake.INTAKING_D);
				break; 
			case State.PORTCULLIS:
				goal = Constants.Intake.PORTCULLIS_POS; 
				output = pidControl(Constants.Intake.PORTCULLIS_P, Constants.Intake.PORTCULLIS_I, Constants.Intake.PORTCULLIS_D);
				break; 
			case State.MANUAL:
				output = manualIn;
				break; 
 		}
		this.setPivotIntake(output);
	}
	
	/**
	 * PID Control for the Intake 
	 * 
	 * @param p Proportional 
	 * @param i Integral
	 * @param d Derivative
	 * 
	 * @return The needed output for the system
	 */
	public double pidControl(double p, double i, double d) {
		prevError = error; 
		error = (this.getEncoderDistance() - goal);
		
		sumError += error;
		
		double pOut = p * error; 
		double iOut = i * sumError; 
		double dOut = d * (error - prevError); 
		
		double output = pOut + iOut + dOut; 
		return output; 
	}
}