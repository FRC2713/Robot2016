package org.usfirst.frc.team2713.robot.commands.obstacle;

import org.usfirst.frc.team2713.robot.Robot;

import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.commands.grabber.PutLoaderAtTopOrBotton;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NavigateChevalDeFrise extends CommandGroup {

	Robot robot;
	
	public NavigateChevalDeFrise(DriveSubsystem drive, LoaderSubsystem loader, LightManager lightManager, Robot robot) {
		this.robot = robot;
		this.addSequential(new GoForward(drive, 24, true, robot, true));
		this.addSequential(new PutLoaderAtTopOrBotton(false, loader));
		this.addSequential(new GoForward(drive, 1000, true, robot, false));
	}
	
	@Override
	protected boolean isFinished() {
		if(robot.interuptArm || robot.interuptLoaderWheels) {
			return true;
		}
		return false;
	}

}
