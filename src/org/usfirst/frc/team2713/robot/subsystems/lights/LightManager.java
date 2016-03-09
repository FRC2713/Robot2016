package org.usfirst.frc.team2713.robot.subsystems.lights;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

public class LightManager extends LightSubsystem {

	Boolean redOrBlue;
	public Boolean autoOrTeleop;
	double timeCommandStarted;
	Boolean finishedPID = false;
	double timePIDFinished;
	Boolean ballGrabbed = false;
	Boolean tilted = false;

	public LightManager() {
		super();
		System.out.println(DriverStation.getInstance().getAlliance());
		redOrBlue = DriverStation.getInstance().getAlliance() == Alliance.Red;
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
		} else if(tilted) {
			setColor(Color.GREEN);
		} else if (ballGrabbed) {
			setColor(Color.PINK);
		} else {
			if (redOrBlue) {
				setColor(Color.RED);
			} else {
				setColor(Color.BLUE);
			}
		}
	}

	
	@Override
	public void startAuto(int defense, int startPos, boolean isRed, boolean leftGoal) {
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
		if(autoOrTeleop != null && !autoOrTeleop) {
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
	
	public void setTilted(boolean tilted) {
		this.tilted = tilted;
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
