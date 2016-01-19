package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.SpinWheelControls;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FlywheelSubsystem extends Subsystem {

	Talon wheel;
	SpinWheelControls wheelControl;
	
	public FlywheelSubsystem() {
		wheel = new Talon(RobotMap.WHEEL_MOTOR);
		wheelControl = new SpinWheelControls(0, this);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public double getRPM() {
		return 0;
	}

}
