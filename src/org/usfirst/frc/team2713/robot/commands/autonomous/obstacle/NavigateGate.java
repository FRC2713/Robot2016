package org.usfirst.frc.team2713.robot.commands.autonomous.obstacle;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NavigateGate extends CommandGroup {
	
	Robot robot;
	
	public NavigateGate(DriveSubsystem drive, LightManager lightManager, Robot robot) {
		addParallel(new GoForward(drive, RobotMap.GATE_DISTANCE, false, robot, true));
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
