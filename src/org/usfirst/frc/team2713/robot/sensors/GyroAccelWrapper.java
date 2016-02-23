package org.usfirst.frc.team2713.robot.sensors;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

public class GyroAccelWrapper extends ADXRS450_Gyro {
	private ADXL362 accel;
	
	public GyroAccelWrapper() {
		super();
		accel = new ADXL362(Range.k4G);
	}
	
	public double getYaw() {
		return getAngle();
	}
	
	public double getRoll() {
		double z = accel.getZ();
		double y = accel.getY();
		return Math.atan(y / z) * 180.0 / Math.PI;
	}
	
	public double getPitch() {
		double x = accel.getX();
		double z = accel.getZ();
		return Math.atan(-x / Math.sqrt(x * x + z * z)) * 180.0 / Math.PI;
	}
	
	public double getAccelX() {
		return accel.getX();
	}
	
	public double getAccelY() {
		return accel.getY();
	}
	
	public double getAccelZ() {
		return accel.getZ();
	}
}
