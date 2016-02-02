package org.usfirst.frc.team2713.robot.input.imu;

import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU extends ADIS16448_IMU {

	public void initImu() {
		calibrateIMU();
		SmartDashboard.putData("IMU", this);
		SmartDashboard.putNumber("IMU Angle", getAngle());
	}
	
	public void calibrateIMU() {
		System.out.println("Calibarating IMU, do NOT touch the robot...");
		this.reset();
		this.calibrate();
		System.out.println("Calibration Complete, feel free to move about the robot");
	}
	
	public double getAngle(){
		double angle = super.getAngle();
		//if (angle > 360){
		//	angle = angle - 360; // The gyro by default goes over 360 (ex. 360 -> 361 -> 362 etc.)
		//}
		return angle;
	}

}
