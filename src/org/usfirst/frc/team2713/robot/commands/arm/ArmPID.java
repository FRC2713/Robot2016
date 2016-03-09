package org.usfirst.frc.team2713.robot.commands.arm;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.GoToAnglePID;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.Command;

public class ArmPID extends Command {

	HookArmSubsystem hookarm;
	double angle;
	LightManager lightManager;
	Robot robot;

	public ArmPID(HookArmSubsystem hookarm, double angle, LightManager lightManager, Robot robot) {
		this.hookarm = hookarm;
		this.angle = angle;
		this.lightManager = lightManager;
		this.robot = robot;
	}

	@Override
	protected void initialize() {
		new GoToAnglePID(hookarm.arm, angle).start();
	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		if (Math.abs(hookarm.arm.get() - angle) <= RobotMap.ARM_ANGLE_STOP_POINT) {
			if (lightManager != null) {
				lightManager.finishPID();
			}
			return true;
		}
		if(robot.oi.manualMoveLoader()) {
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
