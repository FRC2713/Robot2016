package org.usfirst.frc.team2713.robot.commands.grabberCommands;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.LightManager;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ShootBall extends Command {
	
	LoaderSubsystem loader;
	boolean shouldFinish = false;
	LightManager lightManager;
	
	public ShootBall(LoaderSubsystem loader, LightManager lightManager) {
		this.loader = loader;
		this.lightManager = lightManager;
	}	
	
	@Override
	protected void initialize() {
		loader.stopLoadCommand();
	}

	@Override
	protected void execute() {
		if(loader.lockToShoot.get()) {
			shouldFinish = true;
		} else {
			loader.moveLoader(1);
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
		loader.loadBall(0);
		loader.moveLoader(-1);
		try {
			Thread.sleep(RobotMap.TIME_TO_LOWER_LOADER);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loader.moveLoader(0);
		if(lightManager != null) {
			lightManager.releaseBall();
		}
		loader.startLoadCommand();
		return true;
	}

	@Override
	protected boolean isFinished() {
		if(shouldFinish) {
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
