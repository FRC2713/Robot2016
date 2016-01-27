package org.usfirst.frc.team2713.robot.commands.lights;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.LightSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class Lights extends Command {

	boolean toDo = true;
	double inc = 40;
	int change = 4;
	LightSubsystem lights;

	public Lights(LightSubsystem lights) {
		this.lights = lights;
	}

	public Lights() {

	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		if(RobotMap.USE_LIGHT_JAG) {
			talonLight();
		} else {
			servoLights();
		}
	}
	
	public void servoLights() {
		lights.red.set(1);
		lights.green.set(1);
		lights.blue.set(1);
	}
	
	public void talonLight() {
		if (lights != null) {
			if (toDo) {
				lights.lights.set(1);
				toDo = false;
			} else {
				lights.lights.set(0);
				toDo = true;

			}
			if (inc == 200) {
				change = -4;
			}
			if (inc == 40) {
				change = 4;
			}
			inc += change;
			try {
				Thread.sleep((long) inc);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
