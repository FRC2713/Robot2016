package org.usfirst.frc.team2713.robot.commands.grabber;

import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class PutLoaderAtTopOrBotton extends Command {

	boolean topOrBottom;
	LoaderSubsystem loader;
	double startTime = 0;
	
	public PutLoaderAtTopOrBotton(boolean topOrBottom, LoaderSubsystem loader) {
		this.loader = loader;
		this.topOrBottom = topOrBottom;
	}
	
	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		if(topOrBottom && !loader.moveLoader.isRevLimitSwitchClosed()) {
			loader.moveLoader(.75);
		}
		if(!topOrBottom) {
			loader.moveLoader(-1);
		}
	}

	@Override
	protected boolean isFinished() {
		if(topOrBottom && loader.moveLoader.isRevLimitSwitchClosed()) {
			return true;
		}
		if(!topOrBottom && System.currentTimeMillis() - startTime > 500) {
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		loader.moveLoader(0);				
	}

	@Override
	protected void interrupted() {
		loader.moveLoader(0);		
	}

}
