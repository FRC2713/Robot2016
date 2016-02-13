package org.usfirst.frc.team2713.robot.commands.armCommands;

import org.usfirst.frc.team2713.robot.commands.LightManager;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class ArmPID extends Command {

	
	HookArmSubsystem hookarm;
	double angle;
	LightManager lightManager;
	
	public ArmPID(HookArmSubsystem hookarm, double angle, LightManager lightManager) {
		this.hookarm = hookarm;
		this.angle = angle;
		this.lightManager = lightManager;
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
		if ((hookarm.arm.get() - .2) <= angle && (hookarm.arm.get() - .2) >= angle) {
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
