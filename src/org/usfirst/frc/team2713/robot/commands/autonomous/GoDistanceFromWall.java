package org.usfirst.frc.team2713.robot.commands.autonomous;

import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GoDistanceFromWall extends Command {

	private double distance;
	private DriveSubsystem drive;
	private int pol;
	
	public GoDistanceFromWall(double distance, DriveSubsystem drive) {
		this.distance = distance;
		this.drive = drive;
	}
	
	@Override
	protected void initialize() {
		if (drive.ultrasonicFront.getRangeInches() < distance) {
			pol = -1;
		} else {
			pol = 1;
		}
	}

	@Override
	protected void execute() {
		drive.move(.5 * pol);
	}

	@Override
	protected boolean isFinished() {
		return drive.ultrasonicFront.getRangeInches() >= distance;
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
