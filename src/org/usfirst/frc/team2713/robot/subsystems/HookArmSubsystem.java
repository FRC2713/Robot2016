package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HookArmSubsystem extends Subsystem {
	
	HookArmSubsystem hookarm;
	CANTalon arm;
	
	public HookArmSubsystem(){
		if(RobotMap.INIT_HOOKARM){
			arm = new CANTalon(RobotMap.ARM_MOTOR);
			//Init PID here
		}
	}

	public void moveArm(double polarity){
		arm.set(polarity);
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
	
}
