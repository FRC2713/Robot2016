package org.usfirst.frc.team2713.robot.commands.autonomous;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.usfirst.frc.team2713.robot.RobotMap.ColorThreshold;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.VisionSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class VisionAlignment extends Command {
	private VisionSubsystem vision;
	private DriveSubsystem drive;
	private double correctionAngle;

	public VisionAlignment(VisionSubsystem vision, DriveSubsystem drive) {
		requires(vision);
		requires(drive);
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Mat image = vision.getImageMat();
		image = vision.applyHLSThreshold(image, ColorThreshold.HIGH_GOAL);
		List<MatOfPoint> contours = vision.getContours(image);
		if (contours.isEmpty()) {
			return;
		}
		
		vision.filterContoursByArea(contours, 250);
		if (contours.isEmpty()) {
			return;
		}
		
		MatOfPoint closestContour = vision.getClosestContour(contours, 12);
		correctionAngle = vision.approximateAngleToContourCenter(closestContour, 12);
		
		if (Math.abs(correctionAngle) > 1) {
			drive.rotate(correctionAngle, correctionAngle / 60);
		}
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(correctionAngle) <= 1;
	}

	@Override
	protected void end() {
		drive.move(0);
	}

	@Override
	protected void interrupted() {
		drive.move(0);
	}

}
