package org.usfirst.frc.team2713.robot.commands.ObstacleNavigation;

import org.usfirst.frc.team2713.robot.OI;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.armCommands.ArmPID;
import org.usfirst.frc.team2713.robot.commands.driveCommands.GoForward;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NavigateGate extends CommandGroup {
	
	public NavigateGate(DriveSubsystem drive, HookArmSubsystem hookarm, LightManager lightManager, OI oi) {
		addSequential(new ArmPID(hookarm, 0, lightManager, oi));
		addParallel(new ArmPID(hookarm, Math.PI / 2, lightManager, oi));
		addParallel(new GoForward(drive, RobotMap.GATE_DISTANCE, RobotMap.SPEED_TO_DO_GATE, false, oi.getXbox()));
	}	

}
