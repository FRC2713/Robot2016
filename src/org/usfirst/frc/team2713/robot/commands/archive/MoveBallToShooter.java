package org.usfirst.frc.team2713.robot.commands.archive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

public class MoveBallToShooter extends Command {//Not used

	CANTalon loader;
	int count = 0;
	
	public MoveBallToShooter(CANTalon loader) {
		this.loader = loader;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		if(count == 0) {
			loader.set(1);
			count++;
		}
	}

	@Override
	protected boolean isFinished() {
		if(count > 0) {
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
