package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.LightManager;
import org.usfirst.frc.team2713.robot.commands.LoadBall;
import org.usfirst.frc.team2713.robot.commands.archive.ShootShot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class LoaderSubsystem extends BaseSubsystem {

	public CANTalon moveLoader;
	public CANTalon ballLoader;
	LoadBall loadCommand;
	ShootShot shootCommand;
	public DigitalInput loadswitch; // What if this isnt present? Backups!
	public DigitalInput lockToShoot; // What if this isnt present? Backups!
	LightManager lights;

	public LoaderSubsystem(LightManager lights) {
		moveLoader = new CANTalon(RobotMap.MOVE_LOAD_MOTOR);
		ballLoader = new CANTalon(RobotMap.BALL_LOADER_MOTOR);
		loadswitch = new DigitalInput(RobotMap.LOADER_LIMIT_SWITCH);
		lockToShoot = new DigitalInput(RobotMap.LOCK_TO_SHOOT__LIMIT_SWITCH);
		this.lights = lights;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void startTeleop() {
		startLoadCommand(); // Needed?
	}

	@Override
	public void startAuto(int chosen) {
		startLoadCommand();
	}

	public void loadBall(double polarity) {
		ballLoader.set(polarity);
	}

	public void moveLoader(double polarity) {
		moveLoader.set(polarity);
	}

	public void stopLoadCommand() {
		loadCommand.cancel();
	}

	public void startLoadCommand() {
		if (loadCommand != null) {
			loadCommand.cancel();
		}
		loadCommand = new LoadBall(this, lights);
		loadCommand.start();
	}
}
