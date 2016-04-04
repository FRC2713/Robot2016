package org.usfirst.frc.team2713.robot.commands.grabber;

import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ManualLoadBall extends Command {

	LoaderSubsystem loader;
	double polarity;
	
	public ManualLoadBall(LoaderSubsystem loader, double polarity) {
		this.loader = loader;
		this.polarity = polarity;
	}
	
	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		loader.ballLoader.set(polarity);
		System.out.println(loader.ballLoader.getOutputCurrent());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		loader.ballLoader.set(.3);
	}

	@Override
	protected void interrupted() {
		loader.ballLoader.set(.3);
	}

}
