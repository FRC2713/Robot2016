package org.usfirst.frc.team2713.robot.commands;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;


public class LoadBall extends Command {

	private double polarity = 1;
	LoaderSubsystem load;
	DigitalInput control;

	public LoadBall(LoaderSubsystem loader, double polarity) {
		this.load = loader;
		this.control = control;
		requires(loader);
	}
	


	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		if(control.get()) {
			load.loadBall(polarity);	
		} else {
			load.loadBall(0);
		}
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
