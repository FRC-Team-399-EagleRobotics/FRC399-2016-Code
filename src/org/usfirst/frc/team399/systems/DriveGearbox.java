package org.usfirst.frc.team399.systems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;

public class DriveGearbox {

	CANTalon leftA; 
	CANTalon leftB; 
	CANTalon leftC;
	CANTalon rightA; 
	CANTalon rightB;
	CANTalon rightC;
	
	Encoder leftEnc; 
	Encoder rightEnc;
	
	double encoderDisplacement = 0.0;
	double error = 0.0; 
	double prevError = 0.0;
	double difError = 0.0;
	double driveOutput = 0.0;
	
	/**
	 * Constructor for the Gearbox
	 * 
	 * @param lA First Left Motor
	 * @param lB Second Left Motor
	 * @param rA First Right Motor
	 * @param rB Second Right Motor
	 * @param lSA First Left Solenoid
	 * @param lSB Second Left Solenoid
	 * @param rSA First Right Solenoid
	 * @param rSB Second Right Solenoid
	 */
	public DriveGearbox(int lA, int lB, int lC, int rA, int rB, int rC) {
		leftA = new CANTalon(lA);
		leftB = new CANTalon(lB);
		leftC = new CANTalon(lC);
		
		rightA = new CANTalon(rA);
		rightB = new CANTalon(rB);
		rightC = new CANTalon(rC);
	}
	
	/**
	 * Constructor for the Sensors 
	 * 
	 * @param leftEncA First Left Encoder
	 * @param leftEncB Second Left Encoder
	 * @param rightEncA First Right Encoder
	 * @param rightEncB Second Right Encoder
	 */
	public void setSensors(int leftEncA, int leftEncB, int rightEncA, int rightEncB) {		
		leftEnc = new Encoder(leftEncA, leftEncB); 
		rightEnc = new Encoder(rightEncA, rightEncB);
		rightEnc.reset();
		leftEnc.reset();
	}
	
	/**
	 * Reseting the sensors 
	 */
	public void resetSensors() {
		rightEnc.reset();
		leftEnc.reset();
	} 
	
	/**
	 *  Reset the Values that are changing with time
	 */
	public void resetValues() {
		encoderDisplacement = 0.0;
		error = 0.0; 
		prevError = 0.0;
		difError = 0.0;
		driveOutput = 0.0;

	}

	/**
	 * Returns the difference between the encoder values on the left and right
	 * 
	 * @param clear 
	 * @return
	 */
	public double getEncoderTurn() {
		double left = -leftEnc.get();
		double right = rightEnc.get();
		return (left - right);
	}
	
	/**
	 * Get the encoder displacement from the last reset
	 * 
	 * @return
	 */
	public double getEncoderDisplacement() {
		double left = -leftEnc.get();
		double right = rightEnc.get();
		double encoderValue = (left + right) / 2;
		return encoderValue;
	}	
}