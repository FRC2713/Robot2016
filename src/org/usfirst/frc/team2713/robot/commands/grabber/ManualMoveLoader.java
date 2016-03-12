package org.usfirst.frc.team2713.robot.commands.grabber;

import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class ManualMoveLoader extends Command {

	double polarity;
	LoaderSubsystem loader;
	
	public ManualMoveLoader(LoaderSubsystem loader, double polarity) {
		this.polarity = polarity;
		this.loader = loader;
	}
	
	@Override
	protected void initialize() {
		loader.moveLoader.changeControlMode(TalonControlMode.PercentVbus);
	}

	@Override
	protected void execute() {
		loader.moveLoader(polarity);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		loader.moveLoader.set(0);
		double pos = loader.moveLoader.getPosition();
		//loader.moveLoader.changeControlMode(TalonControlMode.Position); // Position mode
		//loader.moveLoader.set(pos); // Hold in place		
	}

}
