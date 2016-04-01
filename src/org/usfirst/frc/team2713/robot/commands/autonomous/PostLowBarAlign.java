package org.usfirst.frc.team2713.robot.commands.autonomous;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class PostLowBarAlign extends Command {
	private AutonomousCommand autocommand;
	private DriveSubsystem drive;
	private Robot robot;
	private boolean isLeft;
	
	private GoDistanceFromWall command;
	
	public PostLowBarAlign(AutonomousCommand autocommand, DriveSubsystem drive, Robot robot, boolean isLeft) {
		this.autocommand = autocommand;
		this.drive = drive;
		this.robot = robot;
		this.isLeft = isLeft;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double wallDistance = drive.ultrasonicSide.getRangeInches();
		
		double x;
		if (isLeft) {
			x = 171.86D - (wallDistance + RobotMap.ROBOT_WIDTH / 2);
		} else {
			x = 146.86D - (wallDistance + RobotMap.ROBOT_WIDTH / 2);
		}
		double y = x * Math.tan(Math.PI / 6);
		
		autocommand.finalDistance = Math.sqrt(x * x + y * y);
		
		command = new GoDistanceFromWall(y - RobotMap.ROBOT_LENGTH / 2, drive);
		command.start();
	}

	@Override
	protected boolean isFinished() {
		return command.isStarted() && !command.isRunning();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
