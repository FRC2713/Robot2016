package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class commandBase extends Command {
	public static DriveSubsystem drive = new DriveSubsystem();
	public static LoaderSubsystem load = new LoaderSubsystem();
	public boolean isCreated;

	public commandBase() {
		
	}

	public void initDrive() {
		if (drive == null) {
			//drive = new DriveSubsystem();
		}
	}

	public void initLoad() {
		if (load == null) {
			//grab = new GrabberSubsystem();
		}
	}

	protected void initialize() {

	}

	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
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

