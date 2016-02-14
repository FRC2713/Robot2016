package org.usfirst.frc.team2713.robot.commands.autonomous;

import java.awt.Point;

import org.usfirst.frc.team2713.robot.commands.GoToWaypoint;
import org.usfirst.frc.team2713.robot.commands.LightManager;
import org.usfirst.frc.team2713.robot.commands.archive.ShootShot;
import org.usfirst.frc.team2713.robot.commands.driveCommands.GoForward;
import org.usfirst.frc.team2713.robot.commands.grabberCommands.ShootBall;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LightSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.archive.FlywheelSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomosCommand extends CommandGroup {

	public AutonomosCommand(int startPos, int defense, boolean leftGoal, DriveSubsystem drive, LoaderSubsystem loader, HookArmSubsystem hookarm, FlywheelSubsystem flywheel, LightManager lights) {
		manageDefenses(defense);
		Point myLocation = new Point(startPos * 121, 192);
		if(leftGoal) {
			Point destination = new Point(10, 192);
			this.addSequential(new GoToWaypoint(drive, destination, myLocation));
			destination = new Point(10, 86);
			this.addSequential(new GoToWaypoint(drive, destination, myLocation));
			destination = new Point(160, 50); //Adjust Espcially
			this.addSequential(new GoToWaypoint(drive, destination, myLocation));
			this.addSequential(new ShootBall(loader, lights));
		} else {
			Point destination = new Point(309, 192);
			this.addSequential(new GoToWaypoint(drive, destination, myLocation));
			destination = new Point(309, 86);
			this.addSequential(new GoToWaypoint(drive, destination, myLocation));
			destination = new Point(160, 50); //Adjust Espcially
			this.addSequential(new GoToWaypoint(drive, destination, myLocation));
			this.addSequential(new ShootBall(loader, lights));
		}
	}
	
	public void manageDefenses(int defense) {
		switch (defense) {
		case 0:
			manageLowBar();
			break;
		case 1:
			manageGate();
			break;
		case 2:
			manageChevalDeFrise();
			break;
		case 3:
			manageSmallRamps();
			break;
		case 4:
			manageMoat();
			break;
		case 5:
			manageDrawbridge();
			break;
		case 6:
			manageSalyPort();
			break;
		case 7:
			manageRockWall();
			break;
		case 8:
			manageRoughTerain();
			break;
		}

	}
	
	public void manageLowBar() {
		
	}
	
	public void manageGate() {
		
	}
	
	public void manageChevalDeFrise() {
		
	}
	
	public void manageSmallRamps() {
		
	}
	
	public void manageMoat() {
	
	}
	
	public void manageDrawbridge() {
		//RIP
	}
	
	public void manageSalyPort() {
		//RIP
	}
	
	public void manageRockWall() {
		
	}
	
	public void manageRoughTerain() {
		
	}
}
