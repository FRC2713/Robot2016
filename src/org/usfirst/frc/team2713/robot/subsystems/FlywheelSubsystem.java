package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;

import org.usfirst.frc.team2713.robot.commands.SpinWheelControls;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FlywheelSubsystem extends Subsystem {

	public CANTalon flywheel;
	SpinWheelControls wheelControl;
	Encoder wheelMeasure;
	SpinWheelControls control;
	
	public FlywheelSubsystem() {
		flywheel = new CANTalon(RobotMap.WHEEL_MOTOR);
		wheelMeasure = new Encoder(8, 9);
		wheelMeasure.setDistancePerPulse(-1.0 / 256.0);
	}
	
	public void startTeleop() {
		control = new SpinWheelControls(3, flywheel, wheelMeasure);
		control.start();
	}
	
	public void startAuto(int chosen) {

	}

	public void startDisabled() {
		control.output();
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
}
