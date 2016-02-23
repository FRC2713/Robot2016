package org.usfirst.frc.team2713.robot.commands.drive;

import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.sensors.GyroAccelWrapper;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command {

	DriveSubsystem drive;
	XBoxController xbox;
	GyroAccelWrapper gyro;

	public TankDrive(DriveSubsystem drive, XBoxController xbox, GyroAccelWrapper gyro) {
		this.drive = drive;
		this.xbox = xbox;
		this.gyro = gyro;
	}

	double scaler;
	double deadband;
	double polarity;

	protected void execute() {
		scaler = 1.0;
		deadband = 0.1;
		polarity = -1;
		drive.tankDrive(xbox.getLeftY() * scaler * polarity, xbox.getRightY() * scaler * polarity, deadband);
		drive.powerTotal = Math.abs(xbox.getLeftY()) + 2 * Math.abs(xbox.getRightY());	
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

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
