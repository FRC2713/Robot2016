package org.usfirst.frc.team2713.robot.commands.armCommands;

import org.usfirst.frc.team2713.robot.OI;
import org.usfirst.frc.team2713.robot.RobotMap;

import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.Command;

public class ArmPID extends Command {

	HookArmSubsystem hookarm;
	double angle;
	LightManager lightManager;
	OI oi;

	public ArmPID(HookArmSubsystem hookarm, double angle, LightManager lightManager, OI oi) {
		this.hookarm = hookarm;
		this.angle = angle;
		this.lightManager = lightManager;
		this.oi = oi;
	}

	@Override
	protected void initialize() {
		hookarm.setAngle(angle);
	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		if ((hookarm.arm.get() - RobotMap.ARM_ANGLE_STOP_POINT) <= angle
				&& (hookarm.arm.get() - RobotMap.ARM_ANGLE_STOP_POINT) >= angle) {
			if (lightManager != null) {
				lightManager.finishPID();
			}
			return true;
		}
		if(oi.manualMoveLoader()) {
			lightManager.finishPID();
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
