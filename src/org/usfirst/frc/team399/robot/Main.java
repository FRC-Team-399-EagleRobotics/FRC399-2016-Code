package org.usfirst.frc.team399.robot;

import org.usfirst.frc.team399.auton.BreachingDefenses;
import org.usfirst.frc.team399.auton.CrossAndTurn;
import org.usfirst.frc.team399.auton.CrossLowBar;
import org.usfirst.frc.team399.auton.HacerNada;
import org.usfirst.frc.team399.auton.LowBarHighShot;
import org.usfirst.frc.team399.config.Ports;
import org.usfirst.frc.team399.systems.Intake;
import org.usfirst.frc.team399.systems.Robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Main extends IterativeRobot{
	
	Robot robot;
	Joystick driverLeft = new Joystick(Ports.Controls.DRIVER_LEFT_JOYSTICK_USB);
	Joystick driverRight = new Joystick(Ports.Controls.DRIVER_RIGHT_JOYSTICK_USB);
	Joystick operator = new Joystick(Ports.Controls.OPERATOR_GAMEPAD_USB);
	SendableChooser autonChooser = null;
	Command autoCommand = null; 
	double scalar = 0.7; 
	
	public void robotInit() {
		autonChooser = new SendableChooser();
		autonChooser.addDefault("Hacer Nada", new HacerNada());
		autonChooser.addObject("Cross the Low Bar", new CrossLowBar());
		autonChooser.addObject("Breach a Defense", new BreachingDefenses());
		autonChooser.addObject("Low Bar & High Goal", new LowBarHighShot());
		autonChooser.addObject("Cross the Defense and Turn", new CrossAndTurn());
		SmartDashboard.putData("Autonomous Chooser" , autonChooser);
		
		robot = Robot.getInstance();
		robot.camera.setQuality(20);
		robot.camera.startAutomaticCapture("cam1");
		robot.intake.resetSensors();
		robot.intake.resetValues();
	}

	public void autonomousInit() {
		autoCommand = (Command) autonChooser.getSelected(); 
		if(autoCommand != null) {
			autoCommand.start();
		}
		robot.intake.resetSensors();
	}
	
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		robot.intake.run();
	}
	
	public void teleopInit() {
		if(autoCommand != null) {
			autoCommand.cancel();
		}
		
		robot.camera.setQuality(20);
		robot.camera.startAutomaticCapture("cam0");
		robot.intake.setState(Intake.State.STOW);
		}	
	
	public void teleopPeriodic() {
		double leftInput = driverLeft.getRawAxis(1);
		double rightInput = driverRight.getRawAxis(1);
		
		robot.drivetrain.tankDrive(leftInput, rightInput);	
		robot.intake.run();
		
		if(!robot.intake.getMagnetReading()) {
			robot.intake.resetSensors();
		} 
		
		if(operator.getRawButton(5)) {
			robot.intake.setState(Intake.State.STOW);
		} else if(operator.getRawButton(6)) {
			robot.intake.setState(Intake.State.INTAKING);
		} else if(operator.getRawButton(9)) {
			robot.intake.setState(Intake.State.MANUAL);
			robot.intake.setManual(operator.getRawAxis(3) * scalar);
		} else if(operator.getRawButton(8)) {
			robot.climber.setPivotClimber(operator.getRawAxis(1) * scalar);
			robot.climber.setWinch(operator.getRawAxis(3) * scalar);
		} else {
			robot.climber.setPivotClimber(0.0);
			robot.climber.setWinch(0.0);
			robot.intake.setManual(0.0);
		}
	
		if(operator.getRawButton(1)) {
			robot.shooter.setShooter(.8, .8);
		} else if(operator.getRawButton(2)) {
			robot.shooter.setShooter(-1.0, -1.0);
			System.out.println("Shooter RPM: " + robot.shooter.getVelocity());
		} else if(operator.getRawButton(3)) {
			robot.shooter.setShooter(1.0, 1.0);
			System.out.println("Shooter RPM: " + robot.shooter.getVelocity());
		} else{
			robot.shooter.setShooter(0, 0);
		} 
		
		if(operator.getPOV() == 0) {
			robot.intake.setWheelIntake(-1.0);
		} else if(operator.getPOV() == 180) {
			robot.intake.setWheelIntake(1.0);
		} else {
			robot.intake.setWheelIntake(0.0);
		}
	}
	
	public void testPeriodic() {
		        
	}
	
	public void testInit() {
		robot.intake.resetSensors();
	}
}
