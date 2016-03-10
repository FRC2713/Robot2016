package org.usfirst.frc.team2713.robot.commands.autonomous;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.usfirst.frc.team2713.robot.OI;
import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.RobotMap.ColorThreshold;
import org.usfirst.frc.team2713.robot.Waypoit;
import org.usfirst.frc.team2713.robot.WaypoitMap;
import org.usfirst.frc.team2713.robot.commands.GoToWayPoit;
import org.usfirst.frc.team2713.robot.commands.drive.GoForward;
import org.usfirst.frc.team2713.robot.commands.drive.GoToAngle;
import org.usfirst.frc.team2713.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AlignCommand extends CommandGroup {
	private static final double HIGH_GOAL_WIDTH = 20; //inches
	private static final double HIGH_GOAL_CONTOUR_MINIMUM_AREA = 400;
	private static final double HIGH_GOAL_VISION_ERROR_MARGIN = 3; //degrees
	private static final double DISTANCE_TO_FRONT_OF_GOAL = 61.619; //inches, from the correctional point
	
	private Waypoit correctionWaypoit;
	
	private boolean isLeft;
	
	private Ultrasonic ultrasonicFront;
	private Ultrasonic ultrasonicSide;
	
	private CameraSubsystem camera;
	private DriveSubsystem drive;
	
	public AlignCommand(boolean isLeft, DriveSubsystem drive, CameraSubsystem camera, Robot robot) {
		this.drive = drive;
		this.camera = camera;
		this.isLeft = isLeft;
		
		//Some lazy things happen to make this work.
		this.correctionWaypoit = new Waypoit();

		ultrasonicFront = createUltrasonic(RobotMap.FRONT_ULTRASONIC_TRIGGER_PORT, RobotMap.FRONT_ULTRASONIC_ECHO_PORT);
		ultrasonicSide = createUltrasonic(RobotMap.SIDE_ULTRASONIC_TRIGGER_PORT, RobotMap.SIDE_ULTRASONIC_ECHO_PORT);
		
		this.addSequential(new CorrectDistance(drive.gyro.getAngle())); //<-- Lazy stuff happens here
		this.addSequential(new GoToWayPoit(drive, correctionWaypoit, robot));
		this.addSequential(new GoToAngle(drive, 60 * (isLeft ? -1 : 1), robot.oi.getXbox()));
		
		if (camera != null) {
			this.addSequential(new VisionAlign());
		}
		
		this.addSequential(new GoForward(drive, DISTANCE_TO_FRONT_OF_GOAL, false, robot));
	}
	
	private Ultrasonic createUltrasonic(int triggerPort, int echoPort) {
		Ultrasonic ultrasonic = new Ultrasonic(triggerPort, echoPort);
		ultrasonic.setEnabled(true);
		ultrasonic.setAutomaticMode(true);
		ultrasonic.setDistanceUnits(Unit.kInches);
		return ultrasonic;
	}

	public class CorrectDistance extends Command {
		private double angle;
		
		public CorrectDistance(double angle) {
			this.angle = angle;
		}

		@Override
		protected void initialize() {
			double cos = Math.cos(angle);
			double distanceX = ultrasonicFront.getRangeInches() * cos;
			double distanceY = ultrasonicSide.getRangeInches() * cos;
			if (isLeft) { //If you didn't guess yet, makeNewXY is the lazy solution.
				correctionWaypoit.makeNewXY(distanceX, distanceY,
						WaypoitMap.LEFT_END_X, WaypoitMap.LEFT_END_Y);
			} else {
				correctionWaypoit.makeNewXY(distanceX, 319.72 - distanceY,
						WaypoitMap.RIGHT_END_X, WaypoitMap.RIGHT_END_Y);
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
