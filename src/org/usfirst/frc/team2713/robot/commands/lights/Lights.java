package org.usfirst.frc.team2713.robot.commands.lights;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

public class Lights extends Command {

	CANTalon lights;
	boolean toDo = true;
	double inc = 40;
	int change = 4;

	public Lights(CANTalon lights) {
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
		if (lights != null) {
			if (toDo) {
				lights.set(1);
				toDo = false;
			} else {
				lights.set(0);
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
			/*
			 * if(toDo) { lights.set(1); toDo = false; } else { lights.set(0);
			 * toDo = true; } try { Thread.sleep((long) ((60.0 / 140.0) *
			 * 400.0)); } catch (InterruptedException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
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
