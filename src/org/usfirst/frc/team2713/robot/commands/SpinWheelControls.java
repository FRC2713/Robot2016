package org.usfirst.frc.team2713.robot.commands;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.FlywheelSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class SpinWheelControls extends Command {

	CANTalon flyWheel;
	double desiredSpeed = 0;
	double RPS;
	double lastTime;
	double error;
	double lastError;
	double lastRotations;
	double currentRotations;
	double lastTimeRotations;
	double globalIntegral = 0.0;
	ArrayList<Double> speed = new ArrayList<Double>();
	int count = 0;

	public SpinWheelControls(double desiredSpeed, CANTalon flyWheel) {
		this.desiredSpeed = desiredSpeed;
		this.flyWheel = flyWheel;
	}

	protected void initialize() {

	}

	@Override
	protected void execute() {
		flyWheel.set(desiredSpeed);
		flyWheel.set(flyWheel.getOutputVoltage());
	}

	protected boolean isFinished() {
		System.out.println(flyWheel.get());
		double RPS = flyWheel.get();
		this.speed.add(RPS);
		if ((RPS - 10) <= desiredSpeed && (RPS + 10) >= desiredSpeed) {
			count++;
			if (count > 10) {
				System.out.println("Done");
				output();
				return true;
			}
		} else {
			count = 0;
		}
		return false;
	}

	protected void end() {
		output();
	}

	protected void interrupted() {
		output();
	}

	public double getRPS() {
		return flyWheel.get();
	}

	public void output() {
		File output = new File("/home/lvuser/output.txt");
		FileWriter write = null;
		try {
			write = new FileWriter(output);
			PrintWriter print = new PrintWriter(write);
			print.println(RobotMap.KdWheel + " Derivative");
			print.println(RobotMap.KpWheel + " Proportional");
			print.println(RobotMap.KiWheel + " Integral");
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
