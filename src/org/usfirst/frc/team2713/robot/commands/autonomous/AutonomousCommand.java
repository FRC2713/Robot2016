package org.usfirst.frc.team2713.robot.commands.autonomous;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.commands.drive.GoToAngle;
import org.usfirst.frc.team2713.robot.commands.grabber.PutLoaderAtTopOrBotton;
import org.usfirst.frc.team2713.robot.commands.grabber.ShootBall;
import org.usfirst.frc.team2713.robot.commands.obstacle.NavigateBumpyObstacle;
import org.usfirst.frc.team2713.robot.commands.obstacle.NavigateChevalDeFrise;
import org.usfirst.frc.team2713.robot.commands.obstacle.NavigateGate;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.VisionSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	public AutonomousCommand(DriveSubsystem drive, LoaderSubsystem loader,
			Robot robot, int defense) {
		/*
		if (defense == 0) {
			this.addSequential(new PutLoaderAtTopOrBotton(false, loader));
			this.addSequential(new GoForward(drive, -150, false, robot));
		}
		if (defense == 7 || defense == 8) {
			this.addSequential(new GoForward(drive, 100, false, robot));
		} else {
			
		} 
		this.addSequential(new GoForward(drive, 2, false, robot));
				this.addSequential(new GoToAngle(drive, -70, robot.getOI().getXbox()));
		*/
		this.addSequential(new GoForward(drive, 50, false, robot));
	}

	public AutonomousCommand(int startPos, int defense, boolean leftGoal,
			DriveSubsystem drive, LoaderSubsystem loader, LightManager lights,
			Robot robot, VisionSubsystem camera) {
		manageDefenses(defense, drive, lights, robot);

		if (leftGoal) {
			this.addSequential(new GoToAngle(drive, 90, null));
		} else {
			this.addSequential(new GoToAngle(drive, -90, null));
		}
		this.addSequential(new GoDistanceFromWall(18.56, drive));
		if (leftGoal) {
			this.addSequential(new GoToAngle(drive, -90, null));
		} else {
			this.addSequential(new GoToAngle(drive, 90, null));
		}
		this.addSequential(new GoDistanceFromWall(28.35, drive));
		this.addSequential(new AlignCommand(leftGoal, drive, camera, robot));
		this.addSequential(new ShootBall(loader, lights, robot));
	}

	public void manageDefenses(int defense, DriveSubsystem drive,
			LightManager lights, Robot robot) {
		switch (defense) {
		case 0:
			manageLowBar(drive, robot);
			break;
		case 1:
			manageGate(drive, lights, robot);
			break;
		case 2:
			manageChevalDeFrise(drive, lights, robot);
			break;
		case 3:
			manageSmallRamps(drive, robot);
			break;
		case 4:
			manageMoat(drive, lights, robot.oi.getXbox());
			break;
		case 5:
			manageDrawbridge();
			break;
		case 6:
			manageSalyPort();
			break;
		case 7:
			manageRockWall(drive, lights, robot.oi.getXbox());
			break;
		case 8:
			manageRoughTerain(drive, lights, robot.oi.getXbox());
			break;
		}

	}

	public void manageLowBar(DriveSubsystem drive, Robot robot) {
		this.addSequential(new GoForward(drive, RobotMap.LOW_BAR_DISTANCE,
				false, robot)); // Needs to be Adjusted
	}

	public void manageGate(DriveSubsystem drive, LightManager lights,
			Robot robot) {
		this.addSequential(new NavigateGate(drive, lights, robot));
	}

	public void manageChevalDeFrise(DriveSubsystem drive, LightManager lights,
			Robot robot) {
		this.addSequential(new NavigateChevalDeFrise(drive, lights, robot));
	}

	public void manageSmallRamps(DriveSubsystem drive, Robot robot) {
		this.addSequential(new GoForward(drive, RobotMap.SMALL_RAMP_DISTANCE,
				false, robot)); // Needs to be adjusted
	}

	public void manageMoat(DriveSubsystem drive, LightManager lights,
			XBoxController xbox) {
		this.addSequential(new NavigateBumpyObstacle(drive, lights, xbox));
	}

	public void manageDrawbridge() {
		// RIP
	}

	public void manageSalyPort() {
		// RIP
	}

	public void manageRockWall(DriveSubsystem drive, LightManager lights,
			XBoxController xbox) {
		this.addSequential(new NavigateBumpyObstacle(drive, lights, xbox)); // Needs
																			// to
																			// be
																			// adjusted
	}

	public void manageRoughTerain(DriveSubsystem drive, LightManager lights,
			XBoxController xbox) {
		this.addSequential(new NavigateBumpyObstacle(drive, lights, xbox)); // Needs
																			// to
																			// be
																			// adjusted
	}
}