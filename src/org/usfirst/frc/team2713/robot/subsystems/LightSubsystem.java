package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.lights.Lights;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LightSubsystem extends Subsystem {

	public CANTalon lights;
	public Servo red;
	public Servo green;
	public Servo blue;
	
	public LightSubsystem() {
		if (RobotMap.USE_LIGHT_JAG) {
			lights = new CANTalon(RobotMap.LIGHT_TALON);
		} else {
			red = new Servo(RobotMap.RED_PWM_PORT);
			green = new Servo(RobotMap.GREEN_PWM_PORT);
			blue = new Servo(RobotMap.BLUE_PWM_PORT);
		}
	}

	public void startTeleop() {
		Lights rave = new Lights(this);
		rave.start();
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
