package org.usfirst.frc.team2713.robot.commands.drive;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GoToAngle extends Command{

	DriveSubsystem drive;
	double angle;
	boolean isFinished = false;
	XBoxController xbox;
	
	public GoToAngle(Robot robot, DriveSubsystem drive, double angle, XBoxController xbox) {
		this.drive = drive;
		this.angle = robot.getGyro().getAngle() - angle;
		this.xbox = xbox;
		requires(drive);
	}
	
	@Override
	protected void initialize() {
		drive.resetPosition();
	}

	@Override
	protected void execute() {
		drive.rotate(angle);
	}

	@Override
	protected boolean isFinished() { // The follow is flawed, I will investigate eventually
		return Math.abs(drive.getAngleRotated()) >= Math.abs(angle);
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
