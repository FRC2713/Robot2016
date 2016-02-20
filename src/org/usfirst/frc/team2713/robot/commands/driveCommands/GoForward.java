package org.usfirst.frc.team2713.robot.commands.driveCommands;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GoForward extends Command {

	DriveSubsystem drive;
	double polarity;
	double distance;
	double timesRun = 0;
	boolean shouldStopIfStuck;

	public GoForward(DriveSubsystem drive, double distance, boolean shouldStopIfStuck) {
		this.drive = drive;
		this.distance = distance;
		this.shouldStopIfStuck = shouldStopIfStuck;
		requires(drive);
	}

	@Override
	protected void initialize() {
		drive.resetPosition();
		drive.move(distance);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return drive.getDistanceTraveled() == distance;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	public boolean isStuck() {
		if (drive.imu != null) {
			double acceleration = Math.sqrt(
					drive.imu.getAccelX() * drive.imu.getAccelX() + drive.imu.getAccelY() * drive.imu.getAccelY());
			if (acceleration - RobotMap.ACCELERATION_STOP_POINT < 0
					&& acceleration + RobotMap.ACCELERATION_STOP_POINT > 0) {
				return true;
			}
		}
		return false;
	}

}
