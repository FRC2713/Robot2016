package org.usfirst.frc.team2713.robot.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.usfirst.frc.team2713.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class SpinWheelControls extends Command {

	int intervalCount = 3;
	int desiredSpeed = 0;
	double RPS;
	double lastTime;
	double error;
	double lastError;
	double lastRotations;
	double currentRotations;
	double lastTimeRotations;
	CANTalon flyWheel;
	Encoder encoder;
	ArrayList<Double> proportional;
	ArrayList<Double> differential;
	ArrayList<Double> integral;
	ArrayList<Double> speed;

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
			RPS = getRPS();
			lastError = error;
			error = ((desiredSpeed - RPS));
			System.out.println(RPS);
			double proportinal = RobotMap.Kp * (error); // Change in Velocity
			double differential = RobotMap.Kd * (error - lastError) / (System.currentTimeMillis() - lastTime);
			double integral = RobotMap.Ki * (error - lastError) * (System.currentTimeMillis() - lastTime);
			lastTime = System.currentTimeMillis();
			double toCorrect = proportinal + integral + differential;
			if (flyWheel.get() + toCorrect < 1) {
				flyWheel.set(flyWheel.get() + toCorrect);
			} else {
				flyWheel.set(1);
			}
			intervalCount = 0;
			this.proportional.add(proportinal);
			this.differential.add(differential);
			this.integral.add(integral);
			this.speed.add(RPS);
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
		lastRotations = currentRotations;
		currentRotations = encoder.getDistance();
		double currentTime = System.currentTimeMillis();
		double RPMS = (currentRotations - lastRotations) / (currentTime - lastTimeRotations);
		lastTimeRotations = currentTime;
		intervalCount = 0;
		return 1000 * RPMS;
	}

	public void output() {
		File output = new File(System.currentTimeMillis() + ".txt");
		FileWriter write = null;
		try {
			write = new FileWriter(output);
			PrintWriter print = new PrintWriter(write);
			print.println("Spinner Output");
			print.println("Proportional");
			for (int i = 0; i < proportional.size(); i++) {
				print.println(proportional.get(i));
			}
			print.println("Differential");
			for (int i = 0; i < differential.size(); i++) {
				print.println(differential.get(i));
			}
			print.println("Integral");
			for (int i = 0; i < integral.size(); i++) {
				print.println(integral.get(i));
			}
			print.println("RPS");
			for (int i = 0; i < speed.size(); i++) {
				print.println(speed.get(i));
			}
			print.flush();
			print.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
