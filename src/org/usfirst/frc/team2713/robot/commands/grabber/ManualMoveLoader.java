package org.usfirst.frc.team2713.robot.commands.grabber;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ManualMoveLoader extends Command {

	double polarity;
	LoaderSubsystem loader;
	
	public ManualMoveLoader(LoaderSubsystem loader, double polarity) {
		this.polarity = polarity;
		this.loader = loader;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		loader.moveLoader(loader.moveLoader.get() + (Math.PI / 180) * polarity);
		/*
		if(loader.moveLoader.get() > RobotMap.LOADER_UPPER_LIMIT) {
			loader.moveLoader.set(RobotMap.LOADER_UPPER_LIMIT);
		}
		if(loader.moveLoader.get() < RobotMap.LOADER_LOWER_LIMIT) {
			loader.moveLoader.set(RobotMap.LOADER_LOWER_LIMIT);
		}
		*/
	}

	@Override
	protected boolean isFinished() {
		if(polarity == 0) {
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
