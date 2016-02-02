package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;



import org.usfirst.frc.team2713.robot.commands.LoadBall;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LoaderSubsystem extends Subsystem {

	CANTalon moveLoad;
	public CANTalon ballLoader;
	LoadBall loadCommand;
	DigitalInput loadswitch;
	
	public LoaderSubsystem() {
		if(RobotMap.INIT_LOADER) {
			loadswitch = new DigitalInput(RobotMap.LOADER_LIMIT_SWITCH);
			moveLoad = new CANTalon(RobotMap.LOAD_MOTOR);
			ballLoader = new CANTalon(RobotMap.BALL_LOADER_MOTOR);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public void startTeleop() {

	}
	
	public void startAuto(int chosen) {
	}
	
	public void startDisabled() {
		
	}

	public void loadBall(double polarity) {
		moveLoad.set(polarity);
		
	}
}
