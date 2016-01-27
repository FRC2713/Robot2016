package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;

import org.usfirst.frc.team2713.robot.commands.SpinWheelControls;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FlywheelSubsystem extends Subsystem {

	public CANTalon flywheelShooter;
	SpinWheelControls wheelControl;
	SpinWheelControls control;
	
	public FlywheelSubsystem() {
		flywheelShooter = new CANTalon(RobotMap.SHOOTER_WHEEL_MOTOR);
		flywheelShooter.configEncoderCodesPerRev(RobotMap.ENCODER_PULSE);
		flywheelShooter.setPID(RobotMap.KpWheel, RobotMap.KiWheel, RobotMap.KdWheel);
		flywheelShooter.setPIDSourceType(PIDSourceType.kRate);
		flywheelShooter.changeControlMode(TalonControlMode.Speed);
	}
	
	public void startTeleop() {

	}
	
	public void startAuto(int chosen) {

	}

	public void startDisabled() {

	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void stopMotors() {
		flywheelShooter.changeControlMode(TalonControlMode.Voltage);
		flywheelShooter.set(0);
		flywheelShooter.changeControlMode(TalonControlMode.Speed);
	}
	
}
