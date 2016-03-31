package org.usfirst.frc.team2713.robot.commands.autonomous;

import java.util.List;


import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap.ColorThreshold;
import org.usfirst.frc.team2713.robot.Waypoint;
import org.usfirst.frc.team2713.robot.WaypointMap;
import org.usfirst.frc.team2713.robot.commands.GoToWayPoint;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.commands.drive.GoToAngle;
import org.usfirst.frc.team2713.robot.subsystems.VisionSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AlignCommand extends CommandGroup {
	private static final double HIGH_GOAL_WIDTH = 20; //inches
	private static final double HIGH_GOAL_CONTOUR_MINIMUM_AREA = 400;
	private static final double HIGH_GOAL_VISION_ERROR_MARGIN = 1; //degrees
	private static final double DISTANCE_TO_FRONT_OF_GOAL = 61.619; //inches, from the correctional point
	
	private Waypoint correctionwaypoint;
	
	private boolean isLeft;
	
	private VisionSubsystem camera;
	private DriveSubsystem drive;
	
	public AlignCommand(boolean isLeft, DriveSubsystem drive, VisionSubsystem camera, Robot robot) {
		this.drive = drive;
		this.camera = camera;
		this.isLeft = isLeft;
		
		//Some lazy things happen to make this work.
		this.correctionwaypoint = new Waypoint();
		
		this.addSequential(new CorrectDistance(drive.gyro.getAngle())); //<-- Lazy stuff happens here
		this.addSequential(new GoToWayPoint(drive, correctionwaypoint, robot));
		this.addSequential(new GoToAngle(drive, 60 * (isLeft ? -1 : 1), robot.oi.getXbox()));
		
		if (camera != null) {
			this.addSequential(new VisionAlign());
		}
		
		this.addSequential(new GoForward(drive, DISTANCE_TO_FRONT_OF_GOAL, false, robot));
	}

	public class CorrectDistance extends Command {
		private double angle;
		
		public CorrectDistance(double angle) {
			this.angle = angle;
		}

		@Override
		protected void initialize() {
			double cos = Math.cos(angle);
			double distanceX = drive.ultrasonicFront.getRangeInches() * cos;
			double distanceY = drive.ultrasonicSide.getRangeInches() * cos;
			if (isLeft) { //If you didn't guess yet, makeNewXY is the lazy solution.
				correctionwaypoint.makeNewXY(distanceX, distanceY,
						WaypointMap.LEFT_END_X, WaypointMap.LEFT_END_Y);
			} else {
				correctionwaypoint.makeNewXY(distanceX, 319.72 - distanceY,
						WaypointMap.RIGHT_END_X, WaypointMap.RIGHT_END_Y);
			}
		}

		@Override
		protected void execute() {
		}

		@Override
		protected boolean isFinished() {
			return true;
		}

		@Override
		protected void end() {
		}

		@Override
		protected void interrupted() {
		}
		
	}
	
	public class VisionAlign extends Command {
		private double errorAngle;
		
		@Override
		protected void initialize() {
		}

		@Override
		protected void execute() {
			Mat thresholdedImage = camera.applyHLSThreshold(camera.getImageMat(), ColorThreshold.HIGH_GOAL);
			List<MatOfPoint> probableHighGoals = camera.filterContoursByArea(camera.getContours(thresholdedImage), HIGH_GOAL_CONTOUR_MINIMUM_AREA);
			MatOfPoint closestContour = camera.getClosestContour(probableHighGoals, HIGH_GOAL_WIDTH);
			errorAngle = camera.approximateAngleToContourCenter(closestContour, HIGH_GOAL_WIDTH);
			
			if (Math.abs(errorAngle) > HIGH_GOAL_VISION_ERROR_MARGIN) {
				drive.resetPosition();
				drive.rotate(((isLeft ? -1 : 1) * 60) + errorAngle, false);
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
}
