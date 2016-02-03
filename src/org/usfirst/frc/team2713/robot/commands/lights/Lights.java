package org.usfirst.frc.team2713.robot.commands.lights;

import java.util.Random;

import org.usfirst.frc.team2713.robot.subsystems.LightSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class Lights extends Command {

	private LightSubsystem lights;

	public Lights(LightSubsystem lights) {
		this.lights = lights;
	}

	@Override
	protected void initialize() {
		lights.setColor(Color.BLACK); // Off.
	}

	@Override
	protected void execute() {
		Random random = new Random();
		lights.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
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
