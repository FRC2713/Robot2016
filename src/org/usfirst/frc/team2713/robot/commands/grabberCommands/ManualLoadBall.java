package org.usfirst.frc.team2713.robot.commands.grabberCommands;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		loader.ballLoader.set(polarity);
	}

	@Override
	protected boolean isFinished() {
		if(polarity == 0) {
			loader.ballLoader.set(0);
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
