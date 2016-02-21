package org.usfirst.frc.team2713.robot.commands.arm;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class MoveHook extends Command {

	HookArmSubsystem hookarm;
	double polarity;
	
	public MoveHook(HookArmSubsystem hookarm, double polarity){
		this.hookarm = hookarm;
		this.polarity = polarity;
		requires(hookarm);
	}
	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		if(hookarm.arm.get() < RobotMap.ARM_UPPER_LIMIT && hookarm.arm.get() > RobotMap.ARM_LOWER_LIMIT) {
			if(hookarm.arm.get() + (Math.PI / 180.0) * polarity > RobotMap.ARM_UPPER_LIMIT) {
				hookarm.setAngle(RobotMap.ARM_UPPER_LIMIT);
			} else if(hookarm.arm.get() + (Math.PI / 180.0) * polarity < RobotMap.ARM_LOWER_LIMIT) {
				hookarm.setAngle(RobotMap.ARM_LOWER_LIMIT);
			} else {
				hookarm.setAngle(hookarm.arm.get() + (Math.PI / 180.0) * polarity);
			}
		}	
	}

	@Override
	protected boolean isFinished() {
		if(polarity == 0) {
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
