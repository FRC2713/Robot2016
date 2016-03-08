package org.usfirst.frc.team2713.robot.commands.arm;

import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class ManualMoveArm extends Command {
	
	double polarity;
	HookArmSubsystem hookarm;
	
	public ManualMoveArm(HookArmSubsystem hookarm, double polarity) {
		this.polarity = polarity;
		this.hookarm = hookarm;
	}
	
	@Override
	protected void initialize() {
		hookarm.arm.changeControlMode(TalonControlMode.PercentVbus);
	}

	@Override
	protected void execute() {
		hookarm.arm.set(polarity);
	}

	@Override
	protected boolean isFinished() {
		if(polarity == 0) {
			hookarm.arm.changeControlMode(TalonControlMode.Position);
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
