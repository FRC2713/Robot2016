package org.usfirst.frc.team2713.robot.commands.driveCommands;

import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.input.imu.IMU;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveJoystick extends Command{
	
	DriveSubsystem drive;
	XBoxController xbox;
	IMU imu;
	Joystick gamepad;
	
	public ArcadeDriveJoystick(DriveSubsystem drive, Joystick gamepad, IMU imu) {
		this.drive = drive;
		this.gamepad = gamepad;
		this.imu = imu;
		requires(drive);
	}
	
	double scaler;
	double deadband;
	double polarity;
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		scaler =1.0;
		deadband = 0.1;
		polarity = -1;
		//drive.arcadeDrive(((Object) gamepad).getXGamepad()*scaler*polarity, ((Object) gamepad).getYGamepad()*scaler*polarity, deadband);
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

