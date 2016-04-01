package org.usfirst.frc.team2713.robot.commands.obstacle;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NavigateBumpyObstacle extends CommandGroup {

	DriveSubsystem drive;
	LightManager lights;
	int count = 0;
	XBoxController xbox;
	double startTime;

	public NavigateBumpyObstacle(DriveSubsystem drive, LightManager lights, Robot robot) {
		this.drive = drive;
		this.lights = lights;
		this.addSequential(new GoForward(drive, 2000, false, robot, false));
	}

}
