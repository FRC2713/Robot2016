package org.usfirst.frc.team2713.robot.commands;
import org.usfirst.frc.team2713.robot.OI;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.input.imu.IMU;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command{
	
	DriveSubsystem drive;
	XBoxController xbox;
	IMU imu;
	
	public ArcadeDrive(DriveSubsystem drive, XBoxController xbox, IMU imu) {
		this.drive = drive;
		this.xbox = xbox;
		this.imu = imu;
		requires(drive);
	}
	
	public ArcadeDrive(DriveSubsystem drive, XBoxController xbox) {
		this.drive = drive;
		this.xbox = xbox;
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
		// TODO Auto-generated method stub
		scaler =1.0;
		deadband = 0.1;
		polarity = -1;
		System.out.println(imu.getAngle());
		drive.ArcadeDrive(OI.xbox.getLeftY()*scaler*polarity,OI.xbox.getRightX()*scaler*polarity, deadband);
		
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

