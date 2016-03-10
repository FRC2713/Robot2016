package org.usfirst.frc.team2713.robot.commands.drive;

import org.usfirst.frc.team2713.robot.Robot;
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
	Robot robot;

	public GoForward(DriveSubsystem drive, double distance, boolean shouldStopIfStuck, Robot robot) {
		this.drive = drive;
		this.distance = distance;
		this.shouldStopIfStuck = shouldStopIfStuck;
		this.robot = robot;
		requires(drive);
	}

	@Override
	protected void initialize() {
		drive.leftback.set(1);
		drive.rightback.set(1);
	}

	@Override
	protected void execute() {
		System.out.println(drive.leftback.getPosition());
	}

	@Override
	protected boolean isFinished() {
		if(Math.abs(drive.getRightDistanceTraveled()) >= Math.abs(distance)
				&& Math.abs(drive.getLeftDistanceTraveled()) >= Math.abs(distance)) {
			return true;
		}
		if(robot.interuptDrive) {
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

}
