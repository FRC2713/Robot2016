package org.usfirst.frc.team2713.robot.subsystems;

import static org.opencv.core.Core.inRange;

import static org.opencv.imgproc.Imgproc.CHAIN_APPROX_SIMPLE;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2HLS;
import static org.opencv.imgproc.Imgproc.RETR_LIST;
import static org.opencv.imgproc.Imgproc.boundingRect;
import static org.opencv.imgproc.Imgproc.contourArea;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.findContours;
import static org.opencv.imgproc.Imgproc.resize;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.RobotMap.ColorThreshold;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.FlipAxis;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;

public class VisionSubsystem extends BaseSubsystem {
	private static final Size IMAGE_SIZE = new Size(320, 240);
	private VideoCapture processingCapture;
	private CameraServer cameraServer;
	
	public VisionSubsystem() {
		processingCapture = new VideoCapture("http://10.27.13.11/mjpg/video.mjpg");
		
		if (!processingCapture.isOpened()) {
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
		processingCapture.read(mat);
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
	 * Filter contours using a given minimum area.
	 * 
	 * @param contours List of contours to filter.
	 * @param minArea Minimum area.
	 * @return List of contours which have an area greater than or equal to the minimum area.
	 */
	public List<MatOfPoint> filterContoursByArea(List<MatOfPoint> contours, double minArea) {
		List<MatOfPoint> mats = new ArrayList<MatOfPoint>();
		for (MatOfPoint contour : contours) {
			if (minArea <= contourArea(contour)) {
				mats.add(contour);
			}
		}
		return mats;
	}
	
	/**
	 * Returns the contour that appears closest.
	 * It is useful for when there can be multiple contours of the same shape.
	 * 
	 * @param contours List of contours found
	 * @param targetWidth Width of the target contour shape.
	 * @return Closest contour, by approximation.
	 * If two contours appear to be the same distance away,
	 * the first contour accessed will be returned.
	 */
	public MatOfPoint getClosestContour(List<MatOfPoint> contours, double targetWidth) {
		MatOfPoint closest = null;
		double smallestDistance = Double.POSITIVE_INFINITY;
		for (MatOfPoint contour : contours) {
			double distance = findDistanceToContour(contour, targetWidth);
			if (distance < smallestDistance) {
				closest = contour;
				smallestDistance = distance;
			}
		}
		return closest;
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
	
	/**
	 * Approximates angle to the center of a contour using its contour's width.
	 * <p><p>
	 * This function probably won't be very accurate, as the function
	 * used to approximate distance relies on the contour being in the center
	 * of view. This function will be used when it is very close, but not exact.
	 * 
	 * @param contour Contour to find the angle to the center of.
	 * @param targetWidth Real width of the object.
	 * @return The approximate angle from the contour's center.
	 */
	public double approximateAngleToContourCenter(MatOfPoint contour, double targetWidth) {
		Rect rect = boundingRect(contour);
		double apparentWidth = rect.width;
		double apparentDistance = IMAGE_SIZE.width/2 - (rect.x + apparentWidth/2);
		return Math.atan((apparentDistance * (targetWidth / apparentWidth))/findDistanceToContour(contour, targetWidth));
	}
	
	public void setTeleopImage(Image image, boolean flip) {
		if (flip) {
			NIVision.imaqFlip(image, image, FlipAxis.CENTER_AXIS);
		}
		cameraServer.setImage(image);
	}
	
	public void releaseCamera() {
		processingCapture.release();
	}
	
	public Image matToImage(Mat mat) {
		 Image image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		 Imgcodecs.imwrite("/home/lvuser/camout.jpg", mat);
		 NIVision.imaqReadFile(image, "/home/lvuser/camout.jpg");
		 return image;
	}

}
