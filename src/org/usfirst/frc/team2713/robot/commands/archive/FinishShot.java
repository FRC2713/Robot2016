package org.usfirst.frc.team2713.robot.commands.archive;

import org.usfirst.frc.team2713.robot.subsystems.archive.FlywheelSubsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

public class FinishShot extends Command {//Not used
	CANTalon swap;
	FlywheelSubsystem flywheel;
	
	public FinishShot(CANTalon swap, FlywheelSubsystem flywheel) {
		this.swap = swap;
		this.flywheel = flywheel;
		
	}
	
	@Override
	protected void initialize() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		swap.set(0);
		flywheel.stopMotors();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
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
