package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

public class LightUpdater {

	LightManager lights;
	double blueTime = System.currentTimeMillis();
	boolean blueOn = false;
	double redTime = System.currentTimeMillis();
	boolean redOn = false;
	double greenTime = System.currentTimeMillis();
	boolean greenOn = false;

	public LightUpdater(LightManager lights) {
		this.lights = lights;
	}
	
	public void updateLights() {
		double blueWait = (lights.blueVal / 255) * 20;
		double redWait = (lights.redVal / 255) * 20;
		double greenWait = (lights.greenVal / 255) * 20;
		lights.blue.setPWMRate(blueWait);
		lights.red.setPWMRate(redWait);
		lights.green.setPWMRate(greenWait);
	}
}
