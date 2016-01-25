package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.lights.Lights;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LightSubsystem extends Subsystem {

	CANTalon light;

	public LightSubsystem() {
		if (RobotMap.USE_LIGHT_JAG) {
			light = new CANTalon(RobotMap.LIGHT_TALON);
		}
	}

	public void startTeleop() {
		if (RobotMap.USE_LIGHT_JAG) {
			Lights rave = new Lights(light);
			rave.start();
		}
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
