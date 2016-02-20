package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.grabberCommands.LoadBall;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.can.CANMessageNotFoundException;

public class LoaderSubsystem extends BaseSubsystem {

	public CANTalon moveLoader;
	public CANTalon ballLoader;
	LoadBall loadCommand;
	public DigitalInput loadswitch; // What if this isnt present? Backups!
	public DigitalInput lockToShoot; // What if this isnt present? Backups!
	LightManager lights;

	public LoaderSubsystem(LightManager lights) {
		moveLoader = new CANTalon(RobotMap.MOVE_LOAD_MOTOR);
		moveLoader.configEncoderCodesPerRev(RobotMap.ENCODER_PULSE);
		moveLoader.setPID(RobotMap.KpLoader, RobotMap.KiLoader, RobotMap.KdLoader);
		moveLoader.setPIDSourceType(PIDSourceType.kRate);
		moveLoader.changeControlMode(TalonControlMode.Position);
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
	public void startAuto(int defense, int startPos, boolean isRed, boolean leftGoal) {
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
	
	public void resetPossition() {
		moveLoader.setPosition(0);
		moveLoader.set(0);
	}
}
