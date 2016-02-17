package org.usfirst.frc.team2713.robot.commands;


import org.usfirst.frc.team2713.robot.WayPoit;
import org.usfirst.frc.team2713.robot.commands.driveCommands.GoForward;
import org.usfirst.frc.team2713.robot.commands.driveCommands.GoToAngle;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToWaypoint extends CommandGroup {

	public GoToWaypoint(DriveSubsystem drive, WayPoit destination, WayPoit myLocation) {
		double yDistance = destination.y - myLocation.y;
		double xDistance = destination.x - myLocation.y;
		double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
		double angle = drive.imu.getAngle();
		if(distance != 0) {
			angle = Math.asin(Math.abs(yDistance) / distance) * 180 / Math.PI;
		}
		if(xDistance < 0 && yDistance < 0) {
			angle = angle - 180;
		} else if((xDistance >= 0 && yDistance < 0)) {
			angle = Math.asin(yDistance / distance);
		} else if(xDistance < 0 && yDistance >= 0) {
			angle = Math.acos(xDistance / distance);
		}
		this.addSequential(new GoToAngle(drive, angle));
		this.addSequential(new GoForward(drive, distance, 1, true));
	}
	
}
