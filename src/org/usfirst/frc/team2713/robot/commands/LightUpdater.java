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
		double blueWait = (1 - lights.blueVal) * 20;
		double redWait = (1 - lights.redVal) * 20;
		double greenWait = (1 - lights.greenVal) * 20;
		if(System.currentTimeMillis() - blueTime > blueWait) {
			if(blueOn) {
				lights.blue.set(false);
			} else {
				lights.blue.set(true);
			}
			blueTime = System.currentTimeMillis();
			blueOn = !blueOn;
		}
		if(System.currentTimeMillis() - redTime > redWait) {
			if(redOn) {
				lights.red.set(false);
			} else {
				lights.red.set(true);
			}
			redTime = System.currentTimeMillis();
			redOn = !redOn;
		}
		if(System.currentTimeMillis() - greenTime > greenWait) {
			if(greenOn) {
				lights.green.set(false);
			} else {
				lights.green.set(true);
			}
			greenTime = System.currentTimeMillis();
			greenOn = !greenOn;
		}
	}
}
