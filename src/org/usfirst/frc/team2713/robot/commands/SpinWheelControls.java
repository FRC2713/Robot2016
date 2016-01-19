package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.FlywheelSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class SpinWheelControls extends Command {

	int desiredSpeed = 0;
	int currentSpeed = 0;
	double RPM;
	double lastRPM;
	double lastTime;
	double error;
	double lastError;
	FlywheelSubsystem flyWheel;
	
	public SpinWheelControls(int desiredSpeed, FlywheelSubsystem flyWheel) {
		this.desiredSpeed = desiredSpeed;
		this.flyWheel = flyWheel;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		lastRPM = RPM;
		RPM = flyWheel.getRPM();
		lastError = error;
		error = ((RPM - desiredSpeed) - (lastRPM - desiredSpeed));
		double proportinal = RobotMap.Kp * (error); //Change in Velocity
		double differential = RobotMap.Kd *(error - lastError) / (System.currentTimeMillis() - lastTime); //Change in error / sec
		double integral = RobotMap.Ki *(error - lastError) * (System.currentTimeMillis() - lastTime); //How much the error has offset you by
		lastTime = System.currentTimeMillis();
		double toCorrect = proportinal + integral + differential;
		currentSpeed += toCorrect;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
