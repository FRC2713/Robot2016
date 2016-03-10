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

}
