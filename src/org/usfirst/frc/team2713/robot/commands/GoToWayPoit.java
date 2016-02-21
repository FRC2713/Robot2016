package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.Waypoit;
import org.usfirst.frc.team2713.robot.commands.driveCommands.GoForward;
import org.usfirst.frc.team2713.robot.commands.driveCommands.GoToAngle;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToWayPoit extends CommandGroup {
	
	public GoToWayPoit(DriveSubsystem drive, Waypoit w){
		this.addSequential(new GoToAngle(drive, w.getAngle()));
		this.addSequential(new GoForward(drive, w.getDistance(), true));
	}
}
