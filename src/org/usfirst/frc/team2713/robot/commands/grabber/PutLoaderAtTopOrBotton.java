package org.usfirst.frc.team2713.robot.commands.grabber;

import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class PutLoaderAtTopOrBotton extends Command {

	boolean topOrBottom;
	LoaderSubsystem loader;
	
	public PutLoaderAtTopOrBotton(boolean topOrBotton, LoaderSubsystem loader) {
		this.loader = loader;
		this.topOrBottom = topOrBottom;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		if(topOrBottom && !loader.moveLoader.isRevLimitSwitchClosed()) {
			loader.moveLoader(-1);
		}
		if(!topOrBottom) {
			loader.moveLoader(1);
		}
	}

	@Override
	protected boolean isFinished() {
		if(topOrBottom && loader.moveLoader.isRevLimitSwitchClosed()) {
			loader.moveLoader(0);
			return true;
		}
		if(!topOrBottom) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
