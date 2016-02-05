package org.usfirst.frc.team2713.robot.input.imu;

import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU extends ADIS16448_IMU {

	double angleOffset;
	double accelrationXOffset;
	double accelrationYOffset;
	double accelrationZOffset;
	public Double velocityX = 0.0;
	public Double velocityY = 0.0;
	public Double velocityZ = 0.0;
	public Double currentXPossition = 0.0;
	public Double currentYPossition = 0.0;
	public Double currentZPossition = 0.0;
	
	public void initImu() {
		calibrateIMU();
		SmartDashboard.putData("IMU", this);
		SmartDashboard.putNumber("IMU Angle", getAngle());
	}
	
	public void calibrateIMU() {
		System.out.println("Calibarating IMU, do NOT touch the robot...");
		this.calibrate();
		this.reset();
		System.out.println("Calibration Complete, feel free to move about the robot");
	}
	
	public void reset() {
		super.reset();
		double averageNum = 10;
		for(int i = 0; i < averageNum; i++) {
			angleOffset = super.getAngle();
			//accelrationXOffset = super.getAccelX() + -3.3333335e-12;
			accelrationXOffset += super.getAccelX();
			accelrationYOffset = super.getAccelY();
			accelrationZOffset = super.getAccelZ();
		}
		angleOffset /= averageNum;
		accelrationXOffset /= averageNum;
		accelrationYOffset /= averageNum;
		accelrationZOffset /= averageNum;
	}
	
	public double getAngle(){
		double angle = super.getAngle();
		angle -= angleOffset;
		return angle;
	}

	public double getAccelX() {
		return super.getAccelX() - accelrationXOffset;
	}
	
	public double getAccelY() {
		return super.getAccelY() - accelrationYOffset;
	}
	
	public double getAccelZ() {
		return super.getAccelZ() - accelrationZOffset;
	}
	
}
