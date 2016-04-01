package org.usfirst.frc.team2713.robot.commands.autonomous;

import org.opencv.core.MatOfPoint;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.VisionSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class VisionAlign extends Command {
	private VisionSubsystem vision;
	private DriveSubsystem drive;
	private static final double HIGH_GOAL_VISION_ERROR_MARGIN = 0.5D;
	
	private double errorAngle;
	private boolean isLeft;
	
	public VisionAlign(VisionSubsystem vision, DriveSubsystem drive, boolean isLeft) {
		this.vision = vision;
		this.drive = drive;
		this.isLeft = isLeft;
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		MatOfPoint goal = vision.findGoalContour(20);
		if (goal != null) {
			errorAngle = vision.approximateAngleToContourCenter(goal, 20);
		
			if (Math.abs(errorAngle) > HIGH_GOAL_VISION_ERROR_MARGIN) {
				drive.resetPosition();
				drive.rotate(((isLeft ? -1 : 1) * 60) + errorAngle);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(errorAngle) <= HIGH_GOAL_VISION_ERROR_MARGIN;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
	
}