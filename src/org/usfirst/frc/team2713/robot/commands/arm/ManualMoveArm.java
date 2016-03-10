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
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() { // whileHeld calls cancel(), so set to 0.
		hookarm.arm.set(0);
		double pos = hookarm.arm.getPosition();
		hookarm.arm.changeControlMode(TalonControlMode.Position); // Position mode
		hookarm.arm.set(pos); // Hold in place
	}

}
