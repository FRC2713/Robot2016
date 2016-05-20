package org.usfirst.frc.team2713.robot.commands.autonomous;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.RobotMap.Defense;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.commands.drive.TurnAround;
import org.usfirst.frc.team2713.robot.commands.grabber.PutLoaderAtTopOrBotton;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.VisionSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {
	
	public AutonomousCommand(Defense defense, DriveSubsystem drive, LoaderSubsystem loader,
			VisionSubsystem vision, Robot robot) {
		if (defense.doNothing()) { // This is already checked, but I'm afraid.
			return;
		}
		
		if (defense.isStatic()) {
			manageStatic(defense, drive, robot);
		} else {
			switch (defense) {
			case LOW_BAR:
				manageLowbar(defense, drive, loader, robot);
				break;
			case PORTCULLIS:
				managePortcullis(defense, drive, loader, robot);
				break;
			}
		}
		
		/*
		 * Do not touch this, this is for the future only.
		 * 
		 * if (defense == Defense.LOW_BAR) {
		 * 	return;
		 * }
		 * 
		 * if (RobotMap.INIT_CAMERA && vision != null) {
		 *	this.addSequential(new VisionAlignment(vision, drive));
		 * }
		 */
	}
	
	private void manageLowbar(Defense defense, DriveSubsystem drive, LoaderSubsystem loader, Robot robot) {
		this.addSequential(new PutLoaderAtTopOrBotton(false, loader));
		this.addSequential(new GoForward(drive, defense.getDistance(), robot, true));
		this.addSequential(new TurnAround(drive));
	}
	
	private void managePortcullis(Defense defense, DriveSubsystem drive, LoaderSubsystem loader, Robot robot) {
		this.addSequential(new PutLoaderAtTopOrBotton(false, loader));
		this.addSequential(new GoForward(drive, defense.getTime(), robot, false));
	}
	
	private void manageStatic(Defense defense, DriveSubsystem drive, Robot robot) {
		long time = defense.getTime();
		double distance = defense.getDistance();
		
		if (time < 0) {
			this.addSequential(new GoForward(drive, distance, robot, true));
		} else {
			this.addSequential(new GoForward(drive, time, robot, false));
		}
	}
}
