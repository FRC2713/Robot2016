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
	double startTime = 0;
	
	public GoForward(DriveSubsystem drive, double distance, boolean shouldStopIfStuck, Robot robot) {
		this.drive = drive;
		this.distance = distance;
		this.shouldStopIfStuck = shouldStopIfStuck;
		this.robot = robot;
		requires(drive);
	}

	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		drive.leftback.set(1 * distance / Math.abs(distance));
		drive.rightback.set(-1* distance / Math.abs(distance));
	}

	@Override
	protected boolean isFinished() {
		if(System.currentTimeMillis() - startTime > Math.abs(distance)) {
			drive.leftback.set(0);
			drive.rightback.set(0);
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
