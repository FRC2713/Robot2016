package org.usfirst.frc.team2713.robot.commands.drive;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
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
		drive.leftback.changeControlMode(TalonControlMode.Position);
		drive.rightback.changeControlMode(TalonControlMode.Position);
		drive.resetPosition();
		drive.move(distance);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		if(Math.abs(drive.getDistanceTraveled()) >= distance) {
			drive.leftback.changeControlMode(TalonControlMode.PercentVbus);
			drive.setPercentVBus();
			return true;
		}
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

	public boolean isStuck() {
		if (drive.gyro != null) {
			double x = drive.gyro.getAccelX();
			double y = drive.gyro.getAccelY();
			double acceleration = Math.sqrt(x * x + y * y);
			if (acceleration - RobotMap.ACCELERATION_STOP_POINT < 0
					&& acceleration + RobotMap.ACCELERATION_STOP_POINT > 0) {
				//return false;
			}
		}
		return false;
	}

}
