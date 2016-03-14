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
			System.out.println("Hi!");
			//loader.moveLoader(-1);
		}
		if(!topOrBottom) {
			loader.moveLoader(-1);
		}
	}

	@Override
	protected boolean isFinished() {
		if(topOrBottom && loader.moveLoader.isRevLimitSwitchClosed()) {
			loader.moveLoader(0);
			return true;
		}
		if(!topOrBottom && System.currentTimeMillis() - startTime > 200) {
			loader.moveLoader(0);
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
