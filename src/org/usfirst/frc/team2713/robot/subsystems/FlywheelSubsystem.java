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

	public CANTalon flywheel1;
	public CANTalon flywheel2;
	SpinWheelControls wheelControl;
	SpinWheelControls control;
	
	public FlywheelSubsystem() {
		flywheel1 = new CANTalon(RobotMap.WHEEL1_MOTOR);
		flywheel2 = new CANTalon(RobotMap.WHEEL2_MOTOR);
		flywheel1.configEncoderCodesPerRev(RobotMap.ENCODER_PULSE);
		flywheel1.setPID(RobotMap.KpWheel, RobotMap.KiWheel, RobotMap.KdWheel);
		flywheel1.setPIDSourceType(PIDSourceType.kRate);
		flywheel1.changeControlMode(TalonControlMode.Speed);
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
		flywheel1.changeControlMode(TalonControlMode.Voltage);
		flywheel1.set(0);
		flywheel2.set(0);
		flywheel1.changeControlMode(TalonControlMode.Speed);
	}
	
}
