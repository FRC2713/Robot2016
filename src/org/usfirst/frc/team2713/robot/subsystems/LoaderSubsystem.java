package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LoaderSubsystem extends Subsystem {

	public static LoaderSubsystem loader;
	CANTalon load;
	
	
	public LoaderSubsystem(){
		if(RobotMap.INIT_LOADER){
			load = new CANTalon(RobotMap.LOAD_MOTOR);
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
		load.set(polarity);
		
	}
}
