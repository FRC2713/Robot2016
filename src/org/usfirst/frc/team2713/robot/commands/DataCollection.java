package org.usfirst.frc.team2713.robot.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LightSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.archive.FlywheelSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class DataCollection extends Command {
	DriveSubsystem drive;
	HookArmSubsystem hookarm;
	LoaderSubsystem loader;
	LightSubsystem light;
	FlywheelSubsystem flywheel;
	ArrayList<Double> batteryVoltage;
	ArrayList<Double> armTotal;
	ArrayList<Double> loaderTotal;
	ArrayList<Double> flywheelTotal;
	ArrayList<Double> lightTotal;
	ArrayList<Double> driveTotal;

	public DataCollection(DriveSubsystem drive, HookArmSubsystem hookarm, LoaderSubsystem loader, LightSubsystem light,
			FlywheelSubsystem flywheel) {
		this.drive = drive;
		this.hookarm = hookarm;
		this.loader = loader;
		this.light = light;
		this.flywheel = flywheel;
		batteryVoltage = new ArrayList<Double>();
		if (drive != null) {
			driveTotal = new ArrayList<Double>();
		}
		if (hookarm != null) {
			armTotal = new ArrayList<Double>();
		}
		if (loader != null) {
			loaderTotal = new ArrayList<Double>();
		}
		if (light != null) {
			lightTotal = new ArrayList<Double>();
		}
		if (flywheel != null) {
			flywheelTotal = new ArrayList<Double>();
		}
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		batteryVoltage.add(DriverStation.getInstance().getBatteryVoltage());
		if (drive != null) {
			driveTotal.add(Math.abs(drive.left.get()) + Math.abs(drive.right.get()) + Math.abs(drive.leftback.get())
					+ Math.abs(drive.rightback.get()));
		}
		if (hookarm != null) {
			armTotal.add(Math.abs(hookarm.arm.getBusVoltage()));
		}
		if (loader != null) {
			loaderTotal.add(Math.abs(loader.moveLoader.get()) + Math.abs(loader.ballLoader.get()));
		}
		if (light != null) {
			// lightTotal.add(light.red. + light.green.get() +
			// light.blue.get());
		}
		if (flywheel != null) {
			flywheelTotal.add(Math.abs(flywheel.flywheelShooter.getBusVoltage()));
		}
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
			print.println("Battery Voltage,Arm Power,Loader Power,Flywheel Power,Light Power,Drive Power");
			for (int i = 0; i < batteryVoltage.size(); i++) {
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
				if (flywheelTotal != null) {
					try {
						print.print(flywheelTotal.get(i) + ",");
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
						print.print(driveTotal.get(i));
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
