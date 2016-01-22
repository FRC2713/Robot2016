package org.usfirst.frc.team2713.robot.commands;
import org.usfirst.frc.team2713.robot.OI;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.command.Command;


public class LoadBall extends Command {

	private double polarity;
	LoaderSubsystem load;

	public LoadBall(double polarity) {
		this.load = load;
		requires(load);
	}
	

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		load.loadBall(polarity);	
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
