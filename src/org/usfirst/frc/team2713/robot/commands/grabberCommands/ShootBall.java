package org.usfirst.frc.team2713.robot.commands.grabberCommands;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.Command;

public class ShootBall extends Command {
	
	LoaderSubsystem loader;
	boolean shouldContinue = false;
	LightManager lightManager;
	
	public ShootBall(LoaderSubsystem loader, LightManager lightManager) {
		this.loader = loader;
		this.lightManager = lightManager;
	}	
	
	@Override
	protected void initialize() {
		loader.stopLoadCommand();
		loader.moveLoader.set(0);
	}

	@Override
	protected void execute() {
		if(loader.moveLoader.get() < 0) {
			shouldContinue = true;
		}
		if(shouldContinue) {
			
		}
		
	}
	
	public boolean releaseBall() {
		loader.moveLoader(0);
		loader.loadBall(-1);
		try {
			Thread.sleep(RobotMap.TIME_TO_RELEASE_BALL);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loader.moveLoader.set(Math.PI / 2);
		if(lightManager != null) {
			lightManager.releaseBall();
		}
		loader.startLoadCommand();
		return true;
	}

	@Override
	protected boolean isFinished() {
		if(shouldContinue) {
			return releaseBall();
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
