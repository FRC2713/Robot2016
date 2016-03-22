package org.usfirst.frc.team2713.robot.commands.obstacle;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NavigateChevalDeFrise extends CommandGroup {

	Robot robot;
	
	public NavigateChevalDeFrise(DriveSubsystem drive, LightManager lightManager, Robot robot) {
		this.robot = robot;
		this.addSequential(new GoForward(drive, RobotMap.CHEVAL_DE_FRISE_DISTANCE, false, robot));
	}
	
	@Override
	protected boolean isFinished() {
		if(robot.interuptArm || robot.interuptLoaderWheels) {
			return true;
		}
		return false;
	}

}
