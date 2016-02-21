package org.usfirst.frc.team2713.robot.commands.obstacle;

import org.usfirst.frc.team2713.robot.OI;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.arm.ArmPID;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NavigateChevalDeFrise extends CommandGroup {

	public NavigateChevalDeFrise(DriveSubsystem drive, HookArmSubsystem hookarm, LightManager lightManager, OI oi) {
		this.addSequential(new ArmPID(hookarm, RobotMap.ARM_LOWER_LIMIT, lightManager, oi));
		this.addSequential(new GoForward(drive, RobotMap.CHEVAL_DE_FRISE_DISTANCE, false, oi.getXbox()));
	}

}
