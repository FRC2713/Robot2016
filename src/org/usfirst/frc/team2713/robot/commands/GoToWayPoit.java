package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.WayPoit;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.commands.drive.GoToAngle;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToWayPoit extends CommandGroup{

DriveSubsystem drive;
double angle;
double d;
	
	public GoToWayPoit(DriveSubsystem drive, WayPoit w, XBoxController xbox){
		this.drive = drive;
		angle = w.ang;
		d = w.d;
		this.addSequential(new GoToAngle(drive, angle, xbox));
		this.addSequential(new GoForward(drive, d, 1, true, xbox));
	}


}
