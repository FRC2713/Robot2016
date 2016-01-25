package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.subsystems.FlywheelSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;

public class MoveFromLoadToShoot extends Command {

	LoaderSubsystem loader;
	FlywheelSubsystem flywheel;
	double startTime;
	
	public MoveFromLoadToShoot(LoaderSubsystem loader, FlywheelSubsystem flywheel) {
		this.loader = loader;
		PowerDistributionPanel PDP = new PowerDistributionPanel();
		this.flywheel = flywheel;
	}
	
	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();		
	}

	@Override
	protected void execute() {
		loader.swap.set(1);		
	}

	@Override
	protected boolean isFinished() {
		if(System.currentTimeMillis() - startTime > 5000) {
			loader.swap.set(0);	
			flywheel.stopMotors();
			return true;
		}
		return false;
	}

	@Override
	protected void end() {

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
