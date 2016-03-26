package org.usfirst.frc.team2713.robot.commands.autonomous;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.Waypoint;
import org.usfirst.frc.team2713.robot.WaypointMap;
import org.usfirst.frc.team2713.robot.commands.GoToWayPoint;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.commands.grabber.PutLoaderAtTopOrBotton;
import org.usfirst.frc.team2713.robot.commands.grabber.ShootBall;
import org.usfirst.frc.team2713.robot.commands.obstacle.NavigateBumpyObstacle;
import org.usfirst.frc.team2713.robot.commands.obstacle.NavigateChevalDeFrise;
import org.usfirst.frc.team2713.robot.commands.obstacle.NavigateGate;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	public AutonomousCommand(DriveSubsystem drive, LoaderSubsystem loader,
			Robot robot, int defense) {
		if (defense == 0) {
			this.addSequential(new PutLoaderAtTopOrBotton(false, loader));
			this.addSequential(new GoForward(drive, -1500, false, robot));
		}
		if (defense == 7 || defense == 8) {
			this.addSequential(new GoForward(drive, 2000, false, robot));
		} else {
			
		}
	}

	public AutonomousCommand(int startPos, int defense, boolean leftGoal,
			DriveSubsystem drive, LoaderSubsystem loader, LightManager lights,
			Robot robot, CameraSubsystem camera) {
		this.addSequential(new GoToWayPoint(drive, WaypointMap.ONE, robot));
		manageDefenses(defense, drive, lights, robot);

		Waypoint waypoit;
		if (leftGoal) {
			waypoit = WaypointMap.GOAL_POIT[0][startPos];
		} else {
			waypoit = WaypointMap.GOAL_POIT[1][startPos - 3];
		}

		this.addSequential(new GoToWayPoint(drive, waypoit, robot));
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