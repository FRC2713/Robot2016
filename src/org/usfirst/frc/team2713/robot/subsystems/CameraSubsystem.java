package org.usfirst.frc.team2713.robot.subsystems;

import static org.opencv.core.Core.inRange;
import static org.opencv.imgproc.Imgproc.CHAIN_APPROX_SIMPLE;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2HLS;
import static org.opencv.imgproc.Imgproc.RETR_LIST;
import static org.opencv.imgproc.Imgproc.boundingRect;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.findContours;
import static org.opencv.imgproc.Imgproc.resize;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.RobotMap.ColorThreshold;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraSubsystem extends Subsystem {
	private static final Size IMAGE_SIZE = new Size(320, 240);
	private VideoCapture capture;
	
	public CameraSubsystem() {
		capture = new VideoCapture(RobotMap.CAMERA);
		if (!capture.isOpened()) {
			throw new RuntimeException("Camera capture couldn't be started.");
		}
	}

	@Override
	protected void initDefaultCommand() {
	}
	
	/**
	 * Reads an image from the camera.
	 * 
	 * @return The image.
	 */
	public Mat getImageMat() {
		Mat mat = new Mat();
		capture.read(mat);
		resize(mat, mat, IMAGE_SIZE);
		return mat;
	}
	
	/**
	 * Applies the passed HLS threshold to the passed image.
	 * 
	 * @param image Image in BGR format.
	 * @param threshold Threshold as defined in the enum in RobotMap
	 * @return Image with the threshold applied.
	 */
	public Mat applyHLSThreshold(Mat image, ColorThreshold threshold) {
		Mat thresholded = new Mat();
		cvtColor(image, image, COLOR_BGR2HLS);
		inRange(image, threshold.getLowValues(), threshold.getHighValues(), thresholded);
		return thresholded;
	}
	
	/**
	 * Locates contours in an image.
	 * 
	 * @param image Image (with threshold applied?)
	 * @return List of contours.
	 */
	public List<MatOfPoint> getContours(Mat image) {
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		findContours(image, contours, null, RETR_LIST, CHAIN_APPROX_SIMPLE);
		return contours;
	}

	/**
	 * Approximates distance to an object using its contour's width.
	 * 
	 * @param contour Contour to find the distance to.
	 * @param targetWidth Real width of the object.
	 * @return The distance to the object in the same units as targetWidth.
	 */
	public double findDistanceToContour(MatOfPoint contour, double targetWidth) {
		double apparentWidth = boundingRect(contour).width;
		return (targetWidth * 160) / (2 * apparentWidth * Math.tan(RobotMap.CAMERA_VIEW_ANGLE));
	}
}
