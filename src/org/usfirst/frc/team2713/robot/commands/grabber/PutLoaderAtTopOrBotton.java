package org.usfirst.frc.team2713.robot.commands.grabber;

import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class PutLoaderAtTopOrBotton extends Command {

	boolean top;
	LoaderSubsystem loader;
	double startTime = 0;
	
	public PutLoaderAtTopOrBotton(boolean top, LoaderSubsystem loader) {
		this.loader = loader;
		this.top = top;
	}
	
	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		if(top && !loader.moveLoader.isRevLimitSwitchClosed()) {
			loader.moveLoader(.75);
		}
		if(!top) {
			loader.moveLoader(-1);
		}
	}

	@Override
	protected boolean isFinished() {
		return (top && loader.moveLoader.isRevLimitSwitchClosed())
				|| (!top && System.currentTimeMillis() - startTime > 600);
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
