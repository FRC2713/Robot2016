package org.usfirst.frc.team2713.robot.commands.obstacle;

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
	double startTime;

	public NavigateBumpyObstacle(DriveSubsystem drive, LightManager lights, XBoxController xbox) {
		this.drive = drive;
		this.lights = lights;
		this.xbox = xbox;
	}

	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		drive.move(.5);
		lights.setTilted(true);
	}

	@Override
	protected boolean isFinished() {
		if (drive.gyro != null) {
			double roll = drive.gyro.getRoll();
			double pitch = drive.gyro.getPitch();
			double tilt = Math
					.sqrt(roll * roll + pitch * pitch);
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
		} else {
			if(System.currentTimeMillis() - startTime > 5000) {
				drive.move(0);
				return true;
			}
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
