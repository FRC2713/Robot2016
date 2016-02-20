package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.WayPoit;
import org.usfirst.frc.team2713.robot.commands.driveCommands.GoForward;
import org.usfirst.frc.team2713.robot.commands.driveCommands.GoToAngle;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToWayPoit extends CommandGroup{

DriveSubsystem drive;
double angle;
double d;
	
	public GoToWayPoit(DriveSubsystem drive, WayPoit w){
		this.drive = drive;
		angle = w.ang;
		d = w.d;
		this.addSequential(new GoToAngle(drive, angle));
		this.addSequential(new GoForward(drive, d, true));
	}


}
