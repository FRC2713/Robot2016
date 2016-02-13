package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.commands.driveCommands.GoForward;
import org.usfirst.frc.team2713.robot.commands.driveCommands.GoToAngle;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToWaypoint extends CommandGroup {

	public GoToWaypoint(DriveSubsystem drive, double waypointX, double waypointY, double lastX, double lastY) {
		double yDistance = waypointY - lastY;
		double xDistance = waypointX - lastX;
		double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
		double angle = Math.asin(yDistance / xDistance) * 180 / Math.PI;
		this.addSequential(new GoToAngle(drive, angle));
		this.addSequential(new GoForward(drive, distance, 1));
	}
	
}
