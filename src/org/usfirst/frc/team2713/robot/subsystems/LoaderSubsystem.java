package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;



import org.usfirst.frc.team2713.robot.commands.LoadBall;
import org.usfirst.frc.team2713.robot.commands.archive.ShootShot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LoaderSubsystem extends Subsystem {

	public CANTalon moveLoader;
	public CANTalon ballLoader;
	LoadBall loadCommand;
	ShootShot shootCommand;
	public DigitalInput loadswitch;
	public DigitalInput lockToShoot;
	
	public LoaderSubsystem() {
		if(RobotMap.INIT_LOADER) {
			moveLoader = new CANTalon(RobotMap.MOVE_LOAD_MOTOR);
			ballLoader = new CANTalon(RobotMap.BALL_LOADER_MOTOR);
			loadswitch = new DigitalInput(RobotMap.LOADER_LIMIT_SWITCH);
			lockToShoot = new DigitalInput(RobotMap.LOCK_TO_SHOOT__LIMIT_SWITCH);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public void startTeleop() {
		startLoadCommand(); //Needed?
	}
	
	public void startAuto(int chosen) {
		startLoadCommand();
	}
	
	public void startDisabled() {
		
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
		if(loadCommand != null) {
			loadCommand.cancel();
		}
		loadCommand = new LoadBall(this);
		loadCommand.start();
	}
}
