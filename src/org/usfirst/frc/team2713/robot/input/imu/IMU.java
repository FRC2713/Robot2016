package org.usfirst.frc.team2713.robot.input.imu;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU {
	ADIS16448_IMU imu;

	public void initImu() {
		imu = new ADIS16448_IMU();
		while (true) {
            SmartDashboard.putData("IMU", imu);
            Timer.delay(0.005);		// wait for a motor update time
        }
	}

}
