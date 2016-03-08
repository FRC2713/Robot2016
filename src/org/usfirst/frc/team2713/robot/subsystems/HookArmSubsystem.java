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
		arm.reverseOutput(true); // If the arm moves the wrong way, swap this.
		arm.configEncoderCodesPerRev(1);
		arm.setPID(RobotMap.KpArm, RobotMap.KiArm, RobotMap.KdArm);
		arm.setPIDSourceType(PIDSourceType.kDisplacement);
		arm.changeControlMode(TalonControlMode.Position);
		
		arm.enableForwardSoftLimit(false); // Insurance
		arm.enableReverseSoftLimit(false); // We PROBABLY don't need it, so that means we need it.
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
