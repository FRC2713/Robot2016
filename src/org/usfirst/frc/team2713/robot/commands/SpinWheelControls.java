package org.usfirst.frc.team2713.robot.commands;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.FlywheelSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class SpinWheelControls extends Command {

	FlywheelSubsystem flyWheel;
	int intervalCount = 3;
	double desiredSpeed = 0;
	double RPS;
	double lastTime;
	double error;
	double lastError;
	double lastRotations;
	double currentRotations;
	double lastTimeRotations;
	ArrayList<Double> proportional;
	ArrayList<Double> differential;
	ArrayList<Double> integral;
	ArrayList<Double> speed;

	public SpinWheelControls(double desiredSpeed, FlywheelSubsystem flyWheel) {
		this.desiredSpeed = desiredSpeed;
		this.flyWheel = flyWheel;
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
			double proportinal = RobotMap.KpWheel * (error); // Change in Velocity
			double differential = RobotMap.KdWheel * (error - lastError) / (System.currentTimeMillis() - lastTime);
			double integral = RobotMap.KiWheel * (error - lastError) * (System.currentTimeMillis() - lastTime);
			lastTime = System.currentTimeMillis();
			double toCorrect = proportinal + integral + differential;
			if (flyWheel.flywheel.get() + toCorrect < 1) {
				flyWheel.flywheel.set(flyWheel.flywheel.get() + toCorrect);
			} else {
				flyWheel.flywheel.set(1);
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

	protected boolean isFinished() { //Once it is up to speed (for a time?), it stops
		if(flyWheel.flywheel.get() - .1 < desiredSpeed && flyWheel.flywheel.get() + .1 > desiredSpeed) {
			return true;
		}
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {

	}

	public double getRPS() {
		lastRotations = currentRotations;
		currentRotations = flyWheel.wheelMeasure.getDistance();
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
