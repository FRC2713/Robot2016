package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class SpinWheelControls extends Command {

	int intervalCount = 3;
	int desiredSpeed = 0;
	double RPS;
	double lastRPS;
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
		if (intervalCount > 2) {
			lastRPS = RPS;
			RPS = getRPS();
			lastError = error;
			error = ((desiredSpeed - RPS));
			System.out.println(RPS);
			double proportinal = RobotMap.Kp * (error); // Change in Velocity
			double differential = RobotMap.Kd * (error - lastError) / (System.currentTimeMillis() - lastTime);
			double integral = RobotMap.Ki * (error - lastError) * (System.currentTimeMillis() - lastTime);
			lastTime = System.currentTimeMillis();
			double toCorrect = proportinal + integral + differential;
			if(flyWheel.get() + toCorrect < 1) {
				flyWheel.set(flyWheel.get() + toCorrect);
			} else {
				flyWheel.set(1);
			}
			intervalCount = 0;
		} else {
			intervalCount++;
		}
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
		double RPMS = (currentRadians - lastRadians) / (currentTime - lastTimeRad);
		lastTimeRad = currentTime;
		intervalCount = 0;
		return 1000 * RPMS;
	}

}
