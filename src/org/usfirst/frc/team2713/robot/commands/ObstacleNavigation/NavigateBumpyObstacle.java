package org.usfirst.frc.team2713.robot.commands.ObstacleNavigation;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.Command;

public class NavigateBumpyObstacle extends Command {

	DriveSubsystem drive;
	LightManager lights;
	int count = 0;

	public NavigateBumpyObstacle(DriveSubsystem drive, LightManager lights) {
		this.drive = drive;
		this.lights = lights;
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
