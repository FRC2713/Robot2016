package org.usfirst.frc.team2713.robot.commands.driveCommands;

import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GoToAngle extends Command{

	DriveSubsystem drive;
	double angle;
	boolean isFinished = false;
	
	public GoToAngle(DriveSubsystem drive, double angle) {
		this.drive = drive;
		this.angle = angle - drive.imu.getAngle();
		requires(drive);
	}
	
	@Override
	protected void initialize() {
		drive.resetPosition();
		drive.rotate(angle, false);
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return drive.getAngleRotated() >= angle;
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
