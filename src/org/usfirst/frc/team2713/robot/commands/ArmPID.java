package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.commands.lights.Color;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LightSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ArmPID extends Command {

	HookArmSubsystem hookarm;
	double angle;
	LightSubsystem lights;
	
	public ArmPID(HookArmSubsystem hookarm, double angle) {
		this.hookarm = hookarm;
		this.angle = angle;
	} 
	
	public ArmPID(HookArmSubsystem hookarm2, double armLowerLimit, LightSubsystem lights) {
		this.hookarm = hookarm;
		this.angle = angle;
		this.lights = lights;
	}

	@Override
	protected void initialize() {
		hookarm.setAngle(angle);
	}

	@Override
	protected void execute() {
		lights.setColor(Color.BLUE);
	}

	@Override
	protected boolean isFinished() {
		if ((hookarm.arm.get() - .2) <= angle && (hookarm.arm.get() - .2) >= angle) {
			
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
