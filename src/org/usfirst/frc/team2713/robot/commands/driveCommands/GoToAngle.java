package org.usfirst.frc.team2713.robot.commands.driveCommands;

import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GoToAngle extends Command{

	DriveSubsystem drive;
	double angle;
	boolean isFinished = false;
	
	public GoToAngle(DriveSubsystem drive, double angle) {
		this.drive = drive;
		this.angle = angle;
		requires(drive);

	}
	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		if(drive.imu == null) {
			isFinished = true;
		}
		if ((drive.imu.getAngle() > angle + 4 || drive.imu.getAngle() < angle - 4)) {
			drive.rotate((angle - drive.imu.getAngle()) * Math.PI / 180.0);
		} else if ((drive.imu.getAngle() < angle + 4 && drive.imu.getAngle() > angle - 4)) {
			drive.rotate(0);
			isFinished = true;
		}

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isFinished;
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
