package org.usfirst.frc.team2713.robot.commands.autonomous;


import org.usfirst.frc.team2713.robot.OI;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.Waypoit;
import org.usfirst.frc.team2713.robot.WaypoitMap;
import org.usfirst.frc.team2713.robot.commands.GoToWayPoit;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.commands.grabber.ShootBall;
import org.usfirst.frc.team2713.robot.commands.obstacle.NavigateBumpyObstacle;
import org.usfirst.frc.team2713.robot.commands.obstacle.NavigateChevalDeFrise;
import org.usfirst.frc.team2713.robot.commands.obstacle.NavigateGate;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	public AutonomousCommand(int startPos, int defense, boolean leftGoal, DriveSubsystem drive, LoaderSubsystem loader, HookArmSubsystem hookarm, LightManager lights, OI oi, CameraSubsystem camera) {
		this.addSequential(new GoToWayPoit(drive, WaypoitMap.ONE, oi));
		manageDefenses(defense, drive, hookarm, lights, oi);
		
		Waypoit waypoit;
		if (leftGoal) {
			waypoit = WaypoitMap.GOAL_POIT[0][startPos];
		} else {
			waypoit = WaypoitMap.GOAL_POIT[1][startPos - 3];
		}
		
		this.addSequential(new GoToWayPoit(drive, waypoit, oi));
		this.addSequential(new AlignCommand(leftGoal, drive, camera, oi));
		this.addSequential(new ShootBall(loader, lights));
	}
	
	public void manageDefenses(int defense, DriveSubsystem drive, HookArmSubsystem hookarm, LightManager lights, OI oi) {
		switch (defense) {
		case 0:
			manageLowBar(drive, oi.getXbox());
			break;
		case 1:
			manageGate(drive, hookarm, lights, oi);
			break;
		case 2:
			manageChevalDeFrise(drive, hookarm, lights, oi);
			break;
		case 3:
			manageSmallRamps(drive, oi.getXbox());
			break;
		case 4:
			manageMoat(drive, lights, oi.getXbox());
			break;
		case 5:
			manageDrawbridge();
			break;
		case 6:
			manageSalyPort();
			break;
		case 7:
			manageRockWall(drive, lights, oi.getXbox());
			break;
		case 8:
			manageRoughTerain(drive, lights, oi.getXbox());
			break;
		}

	}
	
	public void manageLowBar(DriveSubsystem drive, XBoxController xbox) {
		this.addSequential(new GoForward(drive, RobotMap.LOW_BAR_DISTANCE, false, xbox)); //Needs to be Adjusted
	}
	
	public void manageGate(DriveSubsystem drive, HookArmSubsystem hookarm, LightManager lights, OI oi) {
		this.addSequential(new NavigateGate(drive, hookarm, lights, oi));
	}
	
	public void manageChevalDeFrise(DriveSubsystem drive, HookArmSubsystem hookarm, LightManager lights, OI oi) {
		this.addSequential(new NavigateChevalDeFrise(drive, hookarm, lights, oi));
	}
	
	public void manageSmallRamps(DriveSubsystem drive, XBoxController xbox) {
		this.addSequential(new GoForward(drive, RobotMap.SMALL_RAMP_DISTANCE, false, xbox)); //Needs to be adjusted
	}
	
	public void manageMoat(DriveSubsystem drive, LightManager lights, XBoxController xbox) {
		this.addSequential(new NavigateBumpyObstacle(drive, lights, xbox));
	}
	
	public void manageDrawbridge() {
		//RIP
	}
	
	public void manageSalyPort() {
		//RIP
	}
	
	public void manageRockWall(DriveSubsystem drive, LightManager lights, XBoxController xbox) {
		this.addSequential(new NavigateBumpyObstacle(drive, lights, xbox)); //Needs to be adjusted
	}
	
	public void manageRoughTerain(DriveSubsystem drive, LightManager lights, XBoxController xbox) {
		this.addSequential(new NavigateBumpyObstacle(drive, lights, xbox)); //Needs to be adjusted
	}
}