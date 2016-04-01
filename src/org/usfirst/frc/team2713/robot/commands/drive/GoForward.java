package org.usfirst.frc.team2713.robot.commands.drive;

import org.usfirst.frc.team2713.robot.Robot;

import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GoForward extends Command {

	DriveSubsystem drive;
	double polarity;
	double distance;
	Robot robot;
	
	private boolean started;
	
	public GoForward(DriveSubsystem drive, double distance, boolean shouldStopIfStuck, Robot robot) {
		this.drive = drive;
		this.distance = distance - 7;
		this.robot = robot;
		started = false;
		requires(drive);
	}

	@Override
	protected void initialize() {
		started = true;
		drive.resetPosition();
	}

	@Override
	protected void execute() {
		drive.move(1* distance / Math.abs(distance));
	}

	@Override
	protected boolean isFinished() {
		if(Math.abs(drive.getDistance()) > Math.abs(distance)) {
			System.out.println(drive.getDistance());
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
