package org.usfirst.frc.team2713.robot.input.imu;

import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU extends ADIS16448_IMU {

	private double angleOffset;
	private double accelerationXOffset;
	private double accelerationYOffset;
	private double accelerationZOffset;
	private double velocityX;
	private double velocityY;
	private double velocityZ;
	private double positionX;
	private double positionY;
	private double positionZ;
	private double lastCalcTime;
	
	public IMU() {
		super();
		reset();
		calculatePosition();
	}
	
	public void calculatePosition() {
		long currentTime = System.currentTimeMillis() / 1000;
		double dt = currentTime - lastCalcTime;
		
		velocityX += getAccelX() * dt;
		velocityY += getAccelY() * dt;
		velocityZ += getAccelZ() * dt;
		positionX += velocityX * dt;
		positionY += velocityY * dt;
		positionZ += velocityZ * dt;
		
		lastCalcTime = currentTime; 
	}
	
	@Override
	public void reset() {
		super.reset();
		
		angleOffset = 0D;
		accelerationXOffset = 0D;
		accelerationYOffset = 0D;
		accelerationZOffset = 0D;
		velocityX = 0D;
		velocityY = 0D;
		velocityZ = 0D;
		positionX = 0D;
		positionY = 0D;
		positionZ = 0D;
		lastCalcTime = System.currentTimeMillis();
		
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
	
	public double getAngle(){
		return super.getAngle() - angleOffset;
	}
	
	public void refreshDashboard() {
		SmartDashboard.putData("IMU", this);
		SmartDashboard.putNumber("IMU Angle", getAngle());
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
	
	public double getVelocityX() {
		return velocityX;
	}
	
	public double getVelocityY() {
		return velocityY;
	}
	
	public double getVelocityZ() {
		return velocityZ;
	}
	
	public double getPositionX() {
		return positionX;
	}
	
	public double getPositionY() {
		return positionY;
	}
	
	public double getPositionZ() {
		return positionZ;
	}
}
