package org.usfirst.frc.team2713.robot.input.imu;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU {
	ADIS16448_IMU imu;
	
	public IMU() {
		imu = new ADIS16448_IMU();
	}

	public void initImu() {
		calibrateIMU();
		SmartDashboard.putData("IMU", imu); // Just incase we ever use SmartDashboard
	}
	
	public void calibrateIMU() {
		System.out.println("Calibarating IMU, do NOT touch the robot...");
		imu.calibrate();
		System.out.println("Calibration Complete, feel free to move about the robot");
	}
	
	public double getAngle(){
		double angle = imu.getAngle();
		//if (angle > 360){
		//	angle = angle - 360; // The gyro by default goes over 360 (ex. 360 -> 361 -> 362 etc.)
		//}
		return angle;
	}

}
