package org.usfirst.frc.team2713.robot.commands.drive;

import org.usfirst.frc.team2713.robot.Robot;

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
	
	public GoForward(DriveSubsystem drive, double distance, boolean shouldStopIfStuck, Robot robot, boolean distanceOrTime) {
		this.drive = drive;
		this.distance = distance - 7;
		this.robot = robot;
		started = false;
		this.distanceOrTime = distanceOrTime;
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
		drive.move(1* distance / Math.abs(distance));
	}

	@Override
	protected boolean isFinished() {
		if(distanceOrTime && Math.abs(drive.getDistance()) > Math.abs(distance)) {
			return true;
		}
		if(!distanceOrTime && (System.currentTimeMillis() - startTime) > distance) {
			return true;
		}
		return false;
	}
	
	public boolean isStarted() {
		return started;
	}

	@Override
	protected void end() {
		drive.move(0);
	}

	@Override
	protected void interrupted() {
		drive.move(0);
	}

}
