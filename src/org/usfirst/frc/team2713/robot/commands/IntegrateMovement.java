package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.input.imu.IMU;

public class IntegrateMovement {

	IMU imu;
	double started = 0;
	double lastTime = System.currentTimeMillis();
	
	public IntegrateMovement(IMU imu) {
		this.imu = imu;
	}

	public void execute() {
		double dt = (System.currentTimeMillis() - lastTime) / 1000;
		if(started >= 1) {
			imu.currentXPossition += imu.velocityX * dt + 0.5 * imu.getAccelX() * dt * dt;
			imu.currentYPossition += imu.velocityY * dt + 0.5 * imu.getAccelY() * dt * dt;
			imu.currentZPossition += imu.velocityZ * dt + 0.5 * imu.getAccelZ() * dt * dt;
			imu.velocityX += imu.getAccelX() * dt;
			imu.velocityY += imu.getAccelY() * dt;
			imu.velocityZ += imu.getAccelZ() * dt;
			lastTime = System.currentTimeMillis();
		} else {
			lastTime = System.currentTimeMillis();
			started++;
		}
	}

}
