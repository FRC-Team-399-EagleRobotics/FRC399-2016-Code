package org.usfirst.frc.team399.config;

public class Ports {

	public static class Controls{
		public static final int DRIVER_LEFT_JOYSTICK_USB = 0; 
		public static final int DRIVER_RIGHT_JOYSTICK_USB = 1; 
		public static final int OPERATOR_GAMEPAD_USB = 2; 
		public static final int AUTON_CHOOSER_USB = 3; 
	}
	
	public static class DriveGearbox {
		public static final int RIGHT_GEARBOX_A_ID = 1;
		public static final int RIGHT_GEARBOX_B_ID = 2;
		public static final int RIGHT_GEARBOX_C_ID = 10; 
		public static final int LEFT_GEARBOX_A_ID = 3; 
		public static final int LEFT_GEARBOX_B_ID = 4;
		public static final int LEFT_GEARBOX_C_ID = 9;
		public static final int RIGHT_GEARBOX_ENCODER_A = 0; 
		public static final int RIGHT_GEARBOX_ENCODER_B = 1; 
		public static final int LEFT_GEARBOX_ENCODER_A = 2; 
		public static final int LEFT_GEARBOX_ENCODER_B = 3; 
	}
	
	public static class Shooter {
		public static final int TOP_SHOOTER_ID = 5; 
		public static final int BOTTOM_SHOOTER_ID = 6;
		public static final int TOP_SHOOTER_ENCODER_A = 6;
		public static final int TOP_SHOOTER_ENCODER_B = 7; 
		
	}
	
	public static class Intake {
		public static final int INTAKE_PIVOT_ID = 7; 
		public static final int INTAKE_WHEELS_ID = 8;
		public static final int INTAKE_PIVOT_ENCODER_A = 4; 
		public static final int INTAKE_PIVOT_ENCODER_B = 5; 
		public static final int IR_SENSOR_AI = 3; 
		public static final int MAGNET_SENSOR_DIO = 8; 
	}
	
	public static class Climber {
		public static final int LEFT_CLIMBER_PIVOT_ID = 11; 
		public static final int RIGHT_CLIMBER_PIVOT_ID = 12;
		public static final int LEFT_CLIMBER_GEARBOX_ID = 13;
		public static final int RIGHT_CLIMBER_GEARBOX_ID = 14; 
		public static final int RIGHT_RELEASE = 6; 
		public static final int LEFT_RELEASE = 7; 
	}	
}
