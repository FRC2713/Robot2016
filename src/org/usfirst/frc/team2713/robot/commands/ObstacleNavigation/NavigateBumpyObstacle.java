package org.usfirst.frc.team2713.robot.commands.ObstacleNavigation;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.Command;

public class NavigateBumpyObstacle extends Command {

	DriveSubsystem drive;
	LightManager lights;
	int count = 0;
	XBoxController xbox;
	
	public NavigateBumpyObstacle(DriveSubsystem drive, LightManager lights, XBoxController xbox) {
		this.drive = drive;
		this.lights = lights;
		this.xbox = xbox;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		drive.move(.5);
		lights.setTilted(true);
	}

	@Override
	protected boolean isFinished() {
		double tilt = Math
				.sqrt(drive.imu.getRoll() * drive.imu.getRoll() + drive.imu.getPitch() * drive.imu.getPitch());
		if (tilt - RobotMap.IS_TILTED_CONSTANT < 0 && tilt + RobotMap.IS_TILTED_CONSTANT > 0) {
			// Checks if the robot is flat
			count++;
			if (count > 10) {
				lights.setTilted(false);
				drive.move(0);
				return true;
			}
		} else {
			count = 0;
		}
		double xboxTotal = Math.abs(xbox.getRightY()) + Math.abs(xbox.getLeftY());
		if(xboxTotal > .1) {
			return true;
		}
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
