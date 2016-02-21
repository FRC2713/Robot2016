package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.OI;
import org.usfirst.frc.team2713.robot.Waypoit;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.commands.drive.GoToAngle;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToWayPoit extends CommandGroup {
	
	public GoToWayPoit(DriveSubsystem drive, Waypoit w, OI oi){
		this.addSequential(new GoToAngle(drive, w.getAngle(), oi.getXbox()));
		this.addSequential(new GoForward(drive, w.getDistance(), false, oi.getXbox()));
	}
}
