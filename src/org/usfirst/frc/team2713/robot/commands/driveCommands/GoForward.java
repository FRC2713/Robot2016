package org.usfirst.frc.team2713.robot.commands.driveCommands;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GoForward extends Command {

	DriveSubsystem drive;
	double polarity;
	double distance;
	double timesRun = 0;
	boolean isFinished = false;
	boolean shouldStopIfStuck;

	public GoForward(DriveSubsystem drive, double distance, double polarity, boolean shouldStopIfStuck) {
		this.drive = drive;
		this.distance = distance;
		this.polarity = polarity;
		this.shouldStopIfStuck = shouldStopIfStuck;
		requires(drive);
	}

	@Override
	protected void initialize() {
		drive.rightFrontWheelEncoder.reset();
	}

	@Override
	protected void execute() {
		if ((drive.rightFrontWheelEncoder.get() < distance)) {
			drive.move((distance - drive.rightFrontWheelEncoder.get() / distance) * polarity);
			if (shouldStopIfStuck) {
				timesRun++;
				if (timesRun > 10 && isStuck()) {
					isFinished = true;
				}
			}
		} else {
			isFinished = true;
		}

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		drive.move(0);
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

	public boolean isStuck() {
		double acceleration = Math
				.sqrt(drive.imu.getAccelX() * drive.imu.getAccelX() + drive.imu.getAccelY() * drive.imu.getAccelY());
		if (acceleration - RobotMap.ACCELERATION_STOP_POINT < 0
				&& acceleration + RobotMap.ACCELERATION_STOP_POINT > 0) {
			return true;
		}
		return false;
	}

}
