package org.usfirst.frc.team2713.robot.commands.lights;

import org.usfirst.frc.team2713.robot.subsystems.LightSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class SetColor extends Command {

	private LightSubsystem lights;
	Color color;

	public SetColor(LightSubsystem lights, Color color) {
		this.lights = lights;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		lights.setColor(Color.BLACK);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		lights.setColor(color);
		
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
