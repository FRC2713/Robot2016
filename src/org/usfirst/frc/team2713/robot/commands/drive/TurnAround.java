package org.usfirst.frc.team2713.robot.commands.drive;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class TurnAround extends Command {
	DriveSubsystem drive;
	
	public TurnAround(DriveSubsystem drive) {
		this.drive = drive;
		requires(drive);
	}
	
	@Override
	protected void initialize() {
		drive.resetPosition();
	}

	@Override
	protected void execute() {
		drive.rotate(180, 1);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(drive.getAngleRotated()) >= Math.abs(180);
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
