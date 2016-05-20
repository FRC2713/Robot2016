package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap.Defense;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class BaseSubsystem extends Subsystem {

	protected abstract void initDefaultCommand();
	
	public void startTeleop() {}
	public void startAuto(Defense defense, boolean isRed, boolean leftGoal) {}
	public void startDisabled() {}

}
