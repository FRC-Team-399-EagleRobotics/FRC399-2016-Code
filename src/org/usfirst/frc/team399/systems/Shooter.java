package org.usfirst.frc.team399.systems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CANTalon;

public class Shooter {
	
	CANTalon topShooter; 
	CANTalon bottomShooter; 
	
	Encoder topEnc; 
	Encoder botEnc; 
	
	double topEncVal = 0;
	double botEncVal = 0;  
	double currPos = 0.0; 
	double prevPos = 0.0; 
	double prevTime = 0.0; 
	double vel = 0.0; 
	
	int ticksPerRevolution = 4096;
	
	/**
	 * Constructor for Shooter
	 * 
	 * @param tS Shooter motor for the top motor
	 * @param bS Shooter motor for the bottom motor 
	 */
	public Shooter(int tS, int bS) {
		topShooter = new CANTalon(tS);
		bottomShooter = new CANTalon(bS); 
	}
	
	/**
	 * Reset the values that change with time
	 */
	public void resetValues() {
		topEncVal = 0;
		botEncVal = 0; 
		prevPos = 0; 
		currPos = 0; 
		prevTime = 0; 
		vel = 0;
	}
	
	/**
	 * Return the encoder value for the Encoders
	 * 
	 * @return ticks from both encoders
	 */
	public double getEncoderValue() {
		topEncVal = this.getTopValue();
		botEncVal = this.getBottomValue();
		double aveEncVal = (topEncVal + botEncVal)/2;
		return aveEncVal; 
	} 
	
	/**
	 * Return the encoder value for the Top Encoder
	 * 
	 * @return ticks from the enocders
	 */
	public double getTopValue() {
		topEncVal = topShooter.getEncPosition() / ticksPerRevolution; 
		return topEncVal; 
	}
	
	/**
	 * Return the encoder value for the Bottom Encoder
	 * 
	 * @return ticks from the encoders
	 */
	public double getBottomValue() {
		botEncVal = bottomShooter.getEncPosition() / ticksPerRevolution;
		return botEncVal; 
	}
	
	/**
	 * Get the Velocity from the shooter encoders
	 * 
	 * @return Shooter velocity
	 */
	public double getVelocity() {
		prevPos = currPos; 
		currPos = this.getEncoderValue(); 
		vel = (currPos - prevPos) / (System.currentTimeMillis() - prevTime);
		vel *= 1000;
		prevTime = System.currentTimeMillis(); 
		return vel; 
	}
	
	/**
	 * Setting the Values for the Shooter Wheels
	 * 
	 * @param topShotVal value you give the motor
	 * @param bottomShotVal value you give the motor
	 */
	public void setShooter(double topShotVal, double bottomShotVal) {
		bottomShooter.set(-bottomShotVal);
		topShooter.set(-topShotVal);
	}
	
	/**
	 * Bangbang function to get the shooter to a wanted velocity
	 * 
	 * @param tGoal the wanted goal 
	 */
	public void bangBang(int tGoal) { 
		if(this.getVelocity() < tGoal) {
			topShooter.set(-1.0);
			bottomShooter.set(-1.0);
		} else if(this.getBottomValue() > tGoal){
			topShooter.set(0.0);
			bottomShooter.set(0.0);
		} 
	}
}
