package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.lights.Color;
import org.usfirst.frc.team2713.robot.commands.lights.Lights;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LightSubsystem extends Subsystem {

	private DigitalOutput red;
	private DigitalOutput green;
	private DigitalOutput blue;

	public LightSubsystem() {
		red = new DigitalOutput(RobotMap.RED_DIO_PORT);
		green = new DigitalOutput(RobotMap.GREEN_DIO_PORT);
		blue = new DigitalOutput(RobotMap.BLUE_DIO_PORT);
	}

	public void startTeleop() {
		Lights rave = new Lights(this);
		rave.start();
	}
	
	public void setColor(Color color) {
		red.set(color.getRed() > 127); // "Temporary" method, only shuts colors off/on
		green.set(color.getGreen() > 127);
		blue.set(color.getBlue() > 127);
	}

	public void startAuto(int chosen) {

	}

	public void startDisabled() {

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
