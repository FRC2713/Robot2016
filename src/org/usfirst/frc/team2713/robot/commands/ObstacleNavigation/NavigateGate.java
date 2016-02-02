package org.usfirst.frc.team2713.robot.commands.ObstacleNavigation;

import org.usfirst.frc.team2713.robot.commands.ArmPID;
import org.usfirst.frc.team2713.robot.commands.GoForward;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NavigateGate extends CommandGroup {

	public NavigateGate(DriveSubsystem drive, HookArmSubsystem hookarm) {
		addSequential(new ArmPID(hookarm, 0));
		addSequential(new GoForward(drive, .3, .5));
		addParallel(new ArmPID(hookarm, Math.PI / 2));
		addParallel(new GoForward(drive, 2, .5));
	}	

}
