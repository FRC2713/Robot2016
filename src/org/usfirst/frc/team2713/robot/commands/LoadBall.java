package org.usfirst.frc.team2713.robot.commands;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;


public class LoadBall extends Command {

	private double polarity = 1;
	LoaderSubsystem load;
	DigitalInput control;

	public LoadBall(LoaderSubsystem load, DigitalInput control) {
		this.load = load;
		this.control = control;
		requires(load);
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
