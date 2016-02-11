package org.usfirst.frc.team2713.robot.input.imu;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU extends ADIS16448_IMU {

	private double angleOffset;
	private double accelerationXOffset;
	private double accelerationYOffset;
	private double accelerationZOffset;
	
	public IMU() {
		super();
		reset();
	}
	
	@Override
	public void reset() {
		super.reset();
		
		angleOffset = 0D;
		accelerationXOffset = 0D;
		accelerationYOffset = 0D;
		accelerationZOffset = 0D;
		
		int averageNum = 10;
		for(int i = 0; i < averageNum; i++) {
			angleOffset += super.getAngle();
			//accelrationXOffset = super.getAccelX() + -3.3333335e-12;
			accelerationXOffset += super.getAccelX();
			accelerationYOffset += super.getAccelY();
			accelerationZOffset += super.getAccelZ();
		}
		angleOffset /= (double) averageNum;
		accelerationXOffset /= (double) averageNum;
		accelerationYOffset /= (double) averageNum;
		accelerationZOffset /= (double) averageNum;
	}
	
	@Override
	public double getAngle(){
		return super.getAngle() - angleOffset;
	}
	
	public void refreshDashboard() {
		SmartDashboard.putData("IMU", this);
	}

	@Override
	public double getAccelX() {
		return super.getAccelX() - accelerationXOffset;
	}
	
	@Override
	public double getAccelY() {
		return super.getAccelY() - accelerationYOffset;
	}
	
	@Override
	public double getAccelZ() {
		return super.getAccelZ() - accelerationZOffset;
	}
}
