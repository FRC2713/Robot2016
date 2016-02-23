package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.PIDSourceType;

public class HookArmSubsystem extends BaseSubsystem {

	public CANTalon arm;
	public double totalPower;

	public HookArmSubsystem() {
		arm = new CANTalon(RobotMap.ARM_MOTOR); // As long as distance per pulse
		arm.configEncoderCodesPerRev(RobotMap.ENCODER_PULSE);
		arm.setPID(RobotMap.KpArm, RobotMap.KiArm, RobotMap.KdArm);
		arm.setPIDSourceType(PIDSourceType.kRate);
		arm.changeControlMode(TalonControlMode.Position);
		arm.setForwardSoftLimit(RobotMap.ARM_UPPER_LIMIT);
		arm.enableForwardSoftLimit(true);
		arm.setReverseSoftLimit(RobotMap.ARM_LOWER_LIMIT);
		arm.enableReverseSoftLimit(true);
	}

	@Override
	protected void initDefaultCommand() {
	}

	public void setAngle(double angle) {
		arm.set(angle);
	}
	
	public void resetPostition() {
		arm.setPosition(0);
	}

}
