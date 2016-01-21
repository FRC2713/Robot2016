package org.usfirst.frc.team2713.robot.commands;
import org.usfirst.frc.team2713.robot.OI;
import edu.wpi.first.wpilibj.command.Command;


public class LoadBall extends commandBase {

	private int polarity;

	public LoadBall(int polarity){
		requires(load);
	}
	

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
			load.loadBall(polarity);	

		
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
