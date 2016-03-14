package org.usfirst.frc.team2713.robot.commands.grabber;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.GoToAnglePID;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.Command;

public class LoaderPID extends Command {

	LoaderSubsystem loader;
	double angle;
	LightManager lightManager;
	Robot robot;

	public LoaderPID(LoaderSubsystem loader, double angle, LightManager lightManager, Robot robot) {
		this.loader = loader;
		this.angle = angle;
		this.lightManager = lightManager;
		this.robot = robot;
	}

	@Override
	protected void initialize() {
		new GoToAnglePID(loader.moveLoader, angle, robot).start();
	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		if (Math.abs(loader.moveLoader.get() - angle) <= RobotMap.ARM_ANGLE_STOP_POINT) {
			if (lightManager != null) {
				lightManager.finishPID();
			}
			return true;
		}
		if(robot.interuptAllLoaderMover) {
			lightManager.finishPID();
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
