package org.usfirst.frc.team399.systems;

import org.usfirst.frc.team399.config.Ports;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;

public class Robot {
	
	private static Robot instance = null; 

	public Compressor comp;
	
	public Climber climber; 
	
	public DriveGearbox gearbox; 
	
	public DriveTrain drivetrain;
	
	public Intake intake;
	
	public Shooter shooter; 
	
	public CameraServer camera; 

	public static Robot getInstance() {
		if(instance == null) {
			instance = new Robot();
		}
		return instance;
	}
	
	private Robot() {
		
		camera = CameraServer.getInstance();
		
		comp = new Compressor();
		climber = new Climber(Ports.Climber.RIGHT_CLIMBER_PIVOT_ID, Ports.Climber.LEFT_CLIMBER_PIVOT_ID, Ports.Climber.RIGHT_CLIMBER_GEARBOX_ID, Ports.Climber.LEFT_CLIMBER_GEARBOX_ID,
				Ports.Climber.RIGHT_RELEASE, Ports.Climber.LEFT_RELEASE);
		gearbox = new DriveGearbox(Ports.DriveGearbox.LEFT_GEARBOX_A_ID, Ports.DriveGearbox.LEFT_GEARBOX_B_ID, Ports.DriveGearbox.LEFT_GEARBOX_C_ID,
				Ports.DriveGearbox.RIGHT_GEARBOX_A_ID, Ports.DriveGearbox.RIGHT_GEARBOX_B_ID, Ports.DriveGearbox.RIGHT_GEARBOX_C_ID);
		drivetrain = new DriveTrain(gearbox);
		intake = new Intake(Ports.Intake.INTAKE_PIVOT_ID, Ports.Intake.INTAKE_WHEELS_ID);
		shooter = new Shooter(Ports.Shooter.TOP_SHOOTER_ID, Ports.Shooter.BOTTOM_SHOOTER_ID);
		
		gearbox.setSensors(Ports.DriveGearbox.LEFT_GEARBOX_ENCODER_A,Ports.DriveGearbox.LEFT_GEARBOX_ENCODER_B, Ports.DriveGearbox.RIGHT_GEARBOX_ENCODER_A,Ports.DriveGearbox.RIGHT_GEARBOX_ENCODER_B);
		intake.setSensors(Ports.Intake.INTAKE_PIVOT_ENCODER_A, Ports.Intake.INTAKE_PIVOT_ENCODER_B, Ports.Intake.IR_SENSOR_AI, Ports.Intake.MAGNET_SENSOR_DIO);
	}
}
