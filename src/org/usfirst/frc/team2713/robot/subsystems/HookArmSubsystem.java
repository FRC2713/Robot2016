package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HookArmSubsystem extends Subsystem {
	
	public CANTalon arm;
	
	public HookArmSubsystem(){
		if(RobotMap.INIT_HOOKARM){
			arm = new CANTalon(RobotMap.ARM_MOTOR); //As long as distance per pulse is 1
			arm.configEncoderCodesPerRev(RobotMap.ENCODER_PULSE);
			arm.setPID(RobotMap.KpArm, RobotMap.KiArm, RobotMap.KdArm);
			arm.setPIDSourceType(PIDSourceType.kRate);
			arm.changeControlMode(TalonControlMode.Position);		
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
	
	public void setAngle(double angle) {
		arm.set(angle);
	}
	
}
