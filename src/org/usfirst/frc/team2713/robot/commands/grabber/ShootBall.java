package org.usfirst.frc.team2713.robot.commands.grabber;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.GoToAnglePID;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.Command;

public class ShootBall extends Command {
	
	LoaderSubsystem loader;
	boolean shouldContinue = false;
	LightManager lightManager;
	Robot robot;
	
	public ShootBall(LoaderSubsystem loader, LightManager lightManager, Robot robot) {
		this.loader = loader;
		this.lightManager = lightManager;
		this.robot = robot;
	}	
	
	@Override
	protected void initialize() {
		loader.stopLoadCommand();
		new GoToAnglePID(loader.moveLoader, 0, robot).start();
	}

	@Override
	protected void execute() {
		if(loader.moveLoader.get() <  10) {
			shouldContinue = true;
		}
		
	}
	
	public boolean releaseBall() {
		loader.loadBall(1);
		try {
			Thread.sleep(RobotMap.TIME_TO_RELEASE_BALL);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if(robot.interuptAllLoaderMover || robot.interuptUpperLevelLoaderMover || robot.interuptLoaderWheels) {
			loader.loadBall(0);
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
