package org.usfirst.frc.team2713.robot.commands;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.usfirst.frc.team2713.robot.sensors.IMU;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class DataCollection extends Command {
	DriveSubsystem drive;
	HookArmSubsystem hookarm;
	LoaderSubsystem loader;
	LightSubsystem light;
	IMU imu;
	ArrayList<Double> batteryVoltage;
	ArrayList<Double> armTotal;
	ArrayList<Double> loaderTotal;
	ArrayList<Double> lightTotal;
	ArrayList<Double> driveTotal;
	ArrayList<Double> imuData;
	ArrayList<Double> timeData;
	double startTime;

	public DataCollection(DriveSubsystem drive, HookArmSubsystem hookarm, LoaderSubsystem loader, LightSubsystem light,
			IMU imu) {
		this.drive = drive;
		this.hookarm = hookarm;
		this.loader = loader;
		this.light = light;
		this.imu = imu;
		batteryVoltage = new ArrayList<Double>();
		driveTotal = new ArrayList<Double>();
		armTotal = new ArrayList<Double>();
		loaderTotal = new ArrayList<Double>();
		lightTotal = new ArrayList<Double>();
		imuData = new ArrayList<Double>();
		timeData = new ArrayList<Double>();
	}

	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		batteryVoltage.add(DriverStation.getInstance().getBatteryVoltage());
		if (drive != null) {
			driveTotal.add(drive.getDriveTotal());
		} else {
			driveTotal.add(0.0);
		}
		
		if (hookarm != null) {
			armTotal.add(Math.abs(hookarm.arm.getBusVoltage()));
		} else {
			armTotal.add(0.0);
		}
		
		if (loader != null) {
			loaderTotal.add(Math.abs(loader.moveLoader.get()) + Math.abs(loader.ballLoader.get()));
		} else {
			loaderTotal.add(0.0);
		}
		
		if (light != null) {
			// lightTotal.add(light.red. + light.green.get() +
			// light.blue.get());
		} else {
			lightTotal.add(0.0);
		}
		
		if (imu != null) {
			imuData.add((imu.getAngle()));
		} else {
			imuData.add(0.0);
		}
		
		timeData.add(System.currentTimeMillis() - startTime);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		outputDataToCSV();
	}

	@Override
	protected void interrupted() {
		outputDataToCSV();
	}

	private void outputDataToCSV() {
		File output = new File("/home/lvuser/output.csv");
		FileWriter write = null;
		try {
			write = new FileWriter(output);
			PrintWriter print = new PrintWriter(write);
			print.println("Time,Battery Voltage,Arm Power,Loader Power,Flywheel Power,Light Power,Drive Power,IMU Yaw");
			for (int i = 0; i < batteryVoltage.size(); i++) {
				try {
					print.print(timeData.get(i) + ",");
				} catch (IndexOutOfBoundsException ex) {

				}
				if (batteryVoltage != null) {
					try {
						print.print(batteryVoltage.get(i) + ",");
					} catch (IndexOutOfBoundsException ex) {

					}
				}
				if (armTotal != null) {
					try {
						print.print(armTotal.get(i) + ",");
					} catch (IndexOutOfBoundsException ex) {

					}
				}
				if (loaderTotal != null) {
					try {
						print.print(loaderTotal.get(i) + ",");
					} catch (IndexOutOfBoundsException ex) {

					}
				}

				if (lightTotal != null) {
					try {
						print.print(lightTotal.get(i) + ",");
					} catch (IndexOutOfBoundsException ex) {

					}
				}
				if (driveTotal != null) {
					try {
						print.print(driveTotal.get(i) + ",");
					} catch (IndexOutOfBoundsException ex) {

					}
				}
				if (imu != null) {
					try {
						print.print(imuData.get(i) + ",");
					} catch (IndexOutOfBoundsException ex) {

					}
				}
				print.println("");
			}
			print.flush();
			print.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
