package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class ArmPID extends Command {

	int intervalCount = 3;
	double desiredAngle = 0;
	double Angle;
	double lastTime;
	double error;
	double lastError;
	double lastRadians;
	double currentRadians;
	double lastTimeRad;
	CANTalon hookarm;
	Potentiometer pot;
	 double degrees = pot.get();

	public ArmPID(int desiredAngle, CANTalon hookarm, Potentiometer pot) {
		this.desiredAngle = desiredAngle;
		this.hookarm = hookarm;
		this.pot = pot;
	}

	protected void initialize() {

	}

	@Override
	protected void execute() {
		if (intervalCount > 2) {
			Angle = degrees;
			lastError = error;
			error = ((desiredAngle - Angle));
			System.out.println(Angle);
			double proportinal = RobotMap.KpArm * (error); // Change in Velocity
			double differential = RobotMap.KdArm * (error - lastError) / (System.currentTimeMillis() - lastTime);
			double integral = RobotMap.KiArm * (error - lastError) * (System.currentTimeMillis() - lastTime);
			lastTime = System.currentTimeMillis();
			double toCorrect = proportinal + integral + differential;
			if(hookarm.get() + toCorrect < 1) {
				hookarm.set(hookarm.get() + toCorrect);
			} else {
				hookarm.set(1);
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
		degrees = pot.get();
		lastRadians = currentRadians;
		currentRadians = degrees;
		double currentTime = System.currentTimeMillis();
		double RPMS = (currentRadians - lastRadians) / (currentTime - lastTimeRad);
		lastTimeRad = currentTime;
		intervalCount = 0;
		return 1000 * RPMS;
	}

}
