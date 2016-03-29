package org.usfirst.frc.team2713.robot.commands.autonomous;

import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GoDistanceFromWall extends Command {

	private double distance;
	private DriveSubsystem drive;
	
	public GoDistanceFromWall(double distance, DriveSubsystem drive) {
		this.distance = distance;
		this.drive = drive;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		drive.move(.5);
	}

	@Override
	protected boolean isFinished() {
		return drive.ultrasonicFront.getRangeInches() > distance;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		drive.move(0);		
	}

}
