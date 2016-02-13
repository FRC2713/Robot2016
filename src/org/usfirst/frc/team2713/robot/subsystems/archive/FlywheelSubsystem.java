package org.usfirst.frc.team2713.robot.subsystems.archive;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.archive.SpinWheelControls;
import org.usfirst.frc.team2713.robot.subsystems.BaseSubsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.PIDSourceType;

public class FlywheelSubsystem extends BaseSubsystem {

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
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void stopMotors() {
		flywheelShooter.changeControlMode(TalonControlMode.Voltage);
		flywheelShooter.set(0);
		flywheelShooter.changeControlMode(TalonControlMode.Speed);
	}
	
}
