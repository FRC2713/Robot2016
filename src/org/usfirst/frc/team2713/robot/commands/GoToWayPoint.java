package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.Waypoint;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.commands.drive.GoToAngle;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToWayPoint extends CommandGroup {
	
	public GoToWayPoint(DriveSubsystem drive, Waypoint w, Robot robot){
		this.addSequential(new GoToAngle(robot, drive, w.getAngle(), robot.oi.getXbox()));
		this.addSequential(new GoForward(drive, w.getDistance(), false, robot));
	}
}
