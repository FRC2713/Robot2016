package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class SpinWheelControls extends Command {

	int desiredSpeed = 0;
	int currentSpeed = 0;
	double RPS;
	double lastRPM;
	double lastTime;
	double error;
	double lastError;
	double lastRadians;
	double currentRadians;
	double lastTimeRad;
	CANTalon flyWheel;
	Encoder encoder;
	
	public SpinWheelControls(int desiredSpeed, CANTalon flyWheel, Encoder encoder) {
		this.desiredSpeed = desiredSpeed;
		this.flyWheel = flyWheel;
		this.encoder = encoder;		
	}
	
	protected void initialize() {
	
	}

	@Override
	protected void execute() {
		lastRPM = RPS;
		RPS = getRPS();
		lastError = error;
		error = ((RPS - desiredSpeed) - (lastRPM - desiredSpeed));
		double proportinal = RobotMap.Kp * (error); //Change in Velocity
		double differential = RobotMap.Kd *(error - lastError) / (System.currentTimeMillis() - lastTime); //Change in error / sec
		double integral = RobotMap.Ki *(error - lastError) * (System.currentTimeMillis() - lastTime); //How much the error has offset you by
		lastTime = System.currentTimeMillis();
		double toCorrect = proportinal + integral + differential;
		//currentSpeed += toCorrect;
		flyWheel.set(.1);
		System.out.println(encoder.get());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {
		System.out.println("Hi");
	}
	
	public double getRPS() {
		lastRadians = currentRadians;
		currentRadians = encoder.getDistance();
		double currentTime = System.currentTimeMillis();
		double RadiansPMS = (currentRadians - lastRadians) / (currentTime - lastTimeRad);
		return 1000 * RadiansPMS / (2 * Math.PI);
	}

}
