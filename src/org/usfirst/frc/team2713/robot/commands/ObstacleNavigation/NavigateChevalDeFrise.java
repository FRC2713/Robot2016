package org.usfirst.frc.team2713.robot.commands.ObstacleNavigation;

import org.usfirst.frc.team2713.robot.RobotMap;

import org.usfirst.frc.team2713.robot.commands.ArmPID;
import org.usfirst.frc.team2713.robot.commands.GoForward;
import org.usfirst.frc.team2713.robot.commands.LightManager;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NavigateChevalDeFrise extends CommandGroup {

	public NavigateChevalDeFrise(DriveSubsystem drive, HookArmSubsystem hookarm, LightManager lightManager) {
		this.addSequential(new ArmPID(hookarm, RobotMap.ARM_LOWER_LIMIT, lightManager));
		this.addSequential(new GoForward(drive, 1, 2));
	}

}
