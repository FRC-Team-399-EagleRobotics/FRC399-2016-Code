package org.usfirst.frc.team399.config;

public class Constants {
	
	public static class DriveTrain {
		public static final boolean EXTENDED = false; 
		public static final boolean RETRACTED = true; 
		
		public static final double TICKS_TO_INCHES = 19.11;
		
	}
	
	public static class Intake {
		public static final double IR_SENSOR_THRESHOLD = 38900;
		
		public static final int STOW_POS = 0;
		public static final double STOW_P = 0.003; 
		public static final double STOW_I = 0.0;
		public static final double STOW_D = 0.0; 
		
		public static final int INTAKING_POS = 360;  
		public static final double INTAKING_P = 0.005; 
		public static final double INTAKING_I = 0.0; 
		public static final double INTAKING_D = 0.0; 
		
		public static final int PORTCULLIS_POS = 0; 
		public static final double PORTCULLIS_P = 0.0; 
		public static final double PORTCULLIS_I = 0.0; 
		public static final double PORTCULLIS_D = 0.0; 
	}
	
	public static class Climber {
		public static final double RIGHT_PIVOT_POS = 0.0;
		public static final double RIGHT_PIVOT_P = 0.0;
		public static final double RIGHT_PIVOT_I = 0.0; 
		public static final double RIGHT_PIVOT_D = 0.0; 
		
		public static final double OBTUSE_PIVOT_POS = 0.0; 
		public static final double OBTUSE_PIVOT_P = 0.0; 
		public static final double OBTUSE_PIVOT_I = 0.0; 
		public static final double OBTUSE_PIVOT_D = 0.0;
		
		public static final double GRAB_PIVOT_POS = 0.0; 
		public static final double GRAB_PIVOT_P = 0.0;  
		public static final double GRAB_PIVOT_I = 0.0; 
		public static final double GRAB_PIVOT_D = 0.0; 
	}
	
	public static class Shooter {
		public static final double MAX_RPM = 0.0;
		public static final double MIN_RPM = 0.0;
	}
}
