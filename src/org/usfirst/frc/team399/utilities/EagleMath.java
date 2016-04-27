package org.usfirst.frc.team399.utilities;

public class EagleMath {
	
	/**
	 * Scales an input of a range between istartt and istop to a range between ostart and ostop
	 * 
	 * @param value input value
	 * @param istart input value's lower limit
	 * @param istop input value's upper limit
	 * @param ostart output value's lower limit
	 * @param ostop output value's upper limit 
	 * @return the scaled value
	 */
	public static float map(float value, float istart, float istop, float ostart,float ostop) {
		return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
	}
	
	/**
	 * Collapses numbers to +1 0 or -1 depending on its sign. Typically used in 
	 * compare routines to collapse a difference of two longs to an int. 
	 * 
	 * @param diff number to be collapsed to an int 
	 * @return true signum of diff 
	 */
	public static int signum(double diff) {
		if(diff > 0) {
			return 1; 
		} else if (diff < 0) {
			return -1;
		} else {
			return 1;
		}
		
	}
	
	/**
	 * Truncates an input value to specified decimal places
	 *   
	 * @param value what value
	 * @param places what place
	 * @return the value to the specified decimal place
	 */
	public static double truncate(double value, double places) {
		double mutliplier = Math.pow(10, places); 
		return Math.floor(mutliplier * value) / mutliplier; 
	}
	
	/**
	 * This is making sure the motor doesn't go past limits
	 * 
	 * @param in The power going in  
	 * @param low How low the power can go
	 * @param high How high the power can go 
	 * @return
	 */
	public static double cap(double in, double low, double high) {
		if(in < low) {
			in = low; 
		}
		if (in > high) {
			in = high; 
		}
		return in; 
	}
	
	/**
	 * Constrains an infinitely boundless angle(-inf to inf) to lower and high bounds
	 * 
	 * @param angle input angle
	 * @param minAngle lower bound angle
	 * @param maxAngle higher bound angle
	 * @return angle constrained between min and max
	 */
	public static double constrainAngle(double angle, double minAngle, double maxAngle){
		double range = maxAngle - minAngle; 
		double offset = minAngle; 
		angle /= range; 
		angle -= (int) angle; 
		angle *= range; 
		if(angle < offset) {
			angle += range; 
		} 
		return angle; 
	}
}
