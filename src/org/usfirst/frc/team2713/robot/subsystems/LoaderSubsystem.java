package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.LoadBall;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LoaderSubsystem extends Subsystem {

	public static LoaderSubsystem loader;
	CANTalon load;
	public CANTalon swap;
	LoadBall loadCommand;
	DigitalInput loadswitch;
	
	
	public LoaderSubsystem(){
		if(RobotMap.INIT_LOADER){
			loadswitch = new DigitalInput(1);
			load = new CANTalon(RobotMap.LOAD_MOTOR);
			swap = new CANTalon(RobotMap.SWAP_MOTOR);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public void startTeleop() {

	}
	
	public void startAuto(int chosen) {
		loadCommand = new LoadBall(this, loadswitch);
		loadCommand.start();
	}
	
	public void startDisabled() {
		
	}

	public void loadBall(double polarity) {
		load.set(polarity);
		
	}
}
