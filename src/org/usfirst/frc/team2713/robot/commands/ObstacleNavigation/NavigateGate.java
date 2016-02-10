package org.usfirst.frc.team2713.robot.commands.ObstacleNavigation;

import org.usfirst.frc.team2713.robot.commands.ArmPID;
import org.usfirst.frc.team2713.robot.commands.GoForward;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LightSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NavigateGate extends CommandGroup {
	LightSubsystem lights;
	
	public NavigateGate(DriveSubsystem drive, HookArmSubsystem hookarm, LightSubsystem lights) {
		addSequential(new ArmPID(hookarm, 0, lights));
		addSequential(new GoForward(drive, .3, .5));
		addParallel(new ArmPID(hookarm, Math.PI / 2, lights));
		addParallel(new GoForward(drive, 2, .5));
	}	

}
