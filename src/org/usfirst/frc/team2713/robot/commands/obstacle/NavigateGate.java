package org.usfirst.frc.team2713.robot.commands.obstacle;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.arm.ArmPID;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NavigateGate extends CommandGroup {
	
	Robot robot;
	
	public NavigateGate(DriveSubsystem drive, HookArmSubsystem hookarm, LightManager lightManager, Robot robot) {
		addSequential(new ArmPID(hookarm, 0, lightManager, robot));
		addParallel(new ArmPID(hookarm, Math.PI / 2, lightManager, robot));
		addParallel(new GoForward(drive, RobotMap.GATE_DISTANCE, false, robot));
		this.robot = robot;
	}	
	
	@Override
	protected boolean isFinished() {
		if(robot.interuptArm || robot.interuptLoaderWheels) {
			return true;
		}
		return false;
	}

}
