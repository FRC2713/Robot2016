package org.usfirst.frc.team2713.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class BaseSubsystem extends Subsystem {

	protected abstract void initDefaultCommand();
	
	public void startTeleop() {}
	public void startAuto(int defense, int startPos, boolean isRed, boolean leftGoal) {}
	public void startDisabled() {}

}
