package org.usfirst.frc.team2713.robot.commands.autonomous;


import org.usfirst.frc.team2713.robot.RobotMap;

import org.usfirst.frc.team2713.robot.WayPoitMap;
import org.usfirst.frc.team2713.robot.commands.GoToWayPoit;
import org.usfirst.frc.team2713.robot.commands.ObstacleNavigation.NavigateBumpyObstacle;
import org.usfirst.frc.team2713.robot.commands.ObstacleNavigation.NavigateChevalDeFrise;
import org.usfirst.frc.team2713.robot.commands.ObstacleNavigation.NavigateGate;
import org.usfirst.frc.team2713.robot.commands.driveCommands.GoForward;
import org.usfirst.frc.team2713.robot.commands.grabberCommands.ShootBall;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomosCommand extends CommandGroup {

	public AutonomosCommand(int startPos, int defense, boolean leftGoal, DriveSubsystem drive, LoaderSubsystem loader, HookArmSubsystem hookarm, LightManager lights) {
		this.addSequential(new GoToWayPoit(drive, WayPoitMap.One));
		manageDefenses(defense, drive, hookarm, lights); 
		this.addSequential(new GoToWayPoit(drive, WayPoitMap.GoalPoit[leftGoal ? 0 : 1][startPos]));
		this.addSequential(new ShootBall(loader, lights));
	}
	
	public void manageDefenses(int defense, DriveSubsystem drive, HookArmSubsystem hookarm, LightManager lights) {
		switch (defense) {
		case 0:
			manageLowBar(drive);
			break;
		case 1:
			manageGate(drive, hookarm, lights);
			break;
		case 2:
			manageChevalDeFrise(drive, hookarm, lights);
			break;
		case 3:
			manageSmallRamps(drive);
			break;
		case 4:
			manageMoat(drive, lights);
			break;
		case 5:
			manageDrawbridge();
			break;
		case 6:
			manageSalyPort();
			break;
		case 7:
			manageRockWall(drive, lights);
			break;
		case 8:
			manageRoughTerain(drive, lights);
			break;
		}

	}
	
	public void manageLowBar(DriveSubsystem drive) {
		this.addSequential(new GoForward(drive, RobotMap.LOW_BAR_DISTANCE, 1, false)); //Needs to be Adjusted
	}
	
	public void manageGate(DriveSubsystem drive, HookArmSubsystem hookarm, LightManager lights) {
		this.addSequential(new NavigateGate(drive, hookarm, lights));
	}
	
	public void manageChevalDeFrise(DriveSubsystem drive, HookArmSubsystem hookarm, LightManager lights) {
		this.addSequential(new NavigateChevalDeFrise(drive, hookarm, lights));
	}
	
	public void manageSmallRamps(DriveSubsystem drive) {
		this.addSequential(new GoForward(drive, RobotMap.SMALL_RAMP_DISTANCE, 1, false)); //Needs to be adjusted
	}
	
	public void manageMoat(DriveSubsystem drive, LightManager lights) {
		this.addSequential(new NavigateBumpyObstacle(drive, lights));
	}
	
	public void manageDrawbridge() {
		//RIP
	}
	
	public void manageSalyPort() {
		//RIP
	}
	
	public void manageRockWall(DriveSubsystem drive, LightManager lights) {
		this.addSequential(new NavigateBumpyObstacle(drive, lights)); //Needs to be adjusted
	}
	
	public void manageRoughTerain(DriveSubsystem drive, LightManager lights) {
		this.addSequential(new NavigateBumpyObstacle(drive, lights)); //Needs to be adjusted
	}
}
