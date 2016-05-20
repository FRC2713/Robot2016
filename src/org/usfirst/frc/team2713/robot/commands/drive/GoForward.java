package org.usfirst.frc.team2713.robot.commands.drive;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GoForward extends Command {

	DriveSubsystem drive;
	double startTime;
	double polarity;
	double distance;
	Robot robot;
	
	private boolean started;
	boolean distanceOrTime;
	
	public GoForward(DriveSubsystem drive, double distance, Robot robot, boolean distanceOrTime) {
		this.drive = drive;
		this.distance = distance;
		this.robot = robot;
		started = false;
		this.distanceOrTime = distanceOrTime;
		if (distanceOrTime) {
			this.distance = drive.findWheelRotations(distance, RobotMap.DRIVE_WHEEL_DIAMETER);
		}
		requires(drive);
	}

	@Override
	protected void initialize() {
		started = true;
		drive.resetPosition();
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		drive.move(distance / Math.abs(distance));
	}

	@Override
	protected boolean isFinished() {
		return (!distanceOrTime && (System.currentTimeMillis() - startTime) > distance) ||
				(distanceOrTime && Math.abs(drive.getWheelRotations()) >= Math.abs(distance));
	}
	
	public boolean isStarted() {
		return started;
	}

	@Override
	protected void end() {
		drive.move(0);
		drive.resetPosition();
	}

	@Override
	protected void interrupted() {
		drive.move(0);
		drive.resetPosition();
	}

}
