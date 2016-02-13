package org.usfirst.frc.team2713.robot.commands.driveCommands;

import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GoForward extends Command{

	DriveSubsystem drive;
	double polarity;
	double distance;
	boolean isFinished = false;
	
	public GoForward(DriveSubsystem drive, double distance, double polarity) {
		this.drive = drive;
		this.distance = distance;
		this.polarity = polarity;
		requires(drive);

	}
	@Override
	protected void initialize() {
		drive.rightFrontWheelEncoder.reset();
	}

	@Override
	protected void execute() {
		if ((drive.rightFrontWheelEncoder.get() < distance)) {
			drive.move((distance - drive.rightFrontWheelEncoder.get() / distance) * polarity);
		} else {
			drive.move(0);
			isFinished = true;
		}

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isFinished;
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
