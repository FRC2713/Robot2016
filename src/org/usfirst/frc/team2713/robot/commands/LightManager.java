package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.subsystems.LightSubsystem;

import org.usfirst.frc.team2713.robot.subsystems.LightSubsystem.Color;

public class LightManager extends LightSubsystem {

	Boolean redOrBlue;
	public Boolean autoOrTeleop;
	double timeCommandStarted;
	boolean finishedPID;
	double timePIDFinished;
	boolean ballGrabbed;

	public LightManager(Boolean redOrBlue) {
		super();
		this.redOrBlue = redOrBlue;
	}

	public void managerLights() {
		if (autoOrTeleop == null) {
			if (redOrBlue) {
				setColor(Color.RED);
			} else {
				setColor(Color.BLUE);
			}
		} else if (autoOrTeleop != null && (System.currentTimeMillis() - timeCommandStarted) < 4000) {
			if (autoOrTeleop) {
				setColor(Color.CYAN);
			} else {
				setColor(Color.MAGENTA);
			}
		} else if (ballGrabbed) {
			setColor(Color.PINK);
		} else if (finishedPID) {
			if (System.currentTimeMillis() - timePIDFinished < 4000) {
				setColor(Color.GREEN);
			}
			if (System.currentTimeMillis() - timePIDFinished > 4000) {
				finishedPID = false;
			}
		} else {
			if (redOrBlue) {
				setColor(Color.RED);
			} else {
				setColor(Color.BLUE);
			}
		}
	}

	
	@Override
	public void startAuto(int chosen) {
		autoOrTeleop = true;
		timeCommandStarted = System.currentTimeMillis();
	}
		
	@Override
	public void startTeleop() {
		autoOrTeleop = false;
		timeCommandStarted = System.currentTimeMillis();
	}
	
	@Override
	public void startDisabled() {
		if(!autoOrTeleop) {
			finishMatch();
		}
	}

	public void finishPID() {
		finishedPID = true;
		timePIDFinished = System.currentTimeMillis();
	}

	public void grabBall() {
		ballGrabbed = true;
	}

	public void releaseBall() {
		ballGrabbed = false;
	}

	public void finishMatch() {
		for (int i = 0; i < 5; i++) {
			setColor(Color.RED);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setColor(Color.BLUE);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setColor(Color.GREEN);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
