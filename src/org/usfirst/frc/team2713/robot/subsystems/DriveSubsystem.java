package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.OI;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.driveCommands.ArcadeDrive;
import org.usfirst.frc.team2713.robot.commands.driveCommands.TankDrive;
import org.usfirst.frc.team2713.robot.input.imu.IMU;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.can.CANMessageNotFoundException;

public class DriveSubsystem extends BaseSubsystem {

	public Encoder rightFrontWheelEncoder;
	public Double distanceTraveled;
	public RobotDrive roboDrive;
	public CANTalon right;
	public CANTalon left;
	public CANTalon leftback;
	public CANTalon rightback;
	OI oi;
	public IMU imu;
	LightSubsystem lights = new LightSubsystem();

	public DriveSubsystem(OI oi, IMU imu) {
		this.imu = imu;
		this.oi = oi;
		
		try {
			left = new CANTalon(RobotMap.LEFT_TANK);
			right = new CANTalon(RobotMap.RIGHT_TANK);
			
			leftback = new CANTalon(RobotMap.LEFT_TANK_BACK);
			leftback.setControlMode(TalonControlMode.Follower.value);
			leftback.set(RobotMap.LEFT_TANK);
			
			rightback = new CANTalon(RobotMap.RIGHT_TANK_BACK);
			rightback.setControlMode(TalonControlMode.Follower.value);
			rightback.set(RobotMap.RIGHT_TANK);
		} catch(CANMessageNotFoundException ex) {
			ex.printStackTrace();
			return;
		}
		
		roboDrive = new RobotDrive(left, right);
	}

	@Override
	public void startTeleop() {
		if (oi.getXbox() != null) {
			if (RobotMap.isTank) {
				new TankDrive(this, oi.getXbox(), imu).start();
			} else {
				new ArcadeDrive(this, oi.getXbox(), imu).start();
			}
		}
	}

	private static double calcDeadband(double value, double deadband) {
		int sign = (value > 0 ? 1 : -1); // checks the sign of the value
		value *= sign; // changes the value to positive
		if (value <= deadband) {
			return 0.0; // returns 0 if it is less than deadband
		} else {
			return (value - deadband) * sign; // returns vale minus deadband
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void move(double polarity) {
		left.set(polarity);
		right.set(polarity);
	}

	public void rotate(double polarity) {
		left.set(polarity);
		right.set(-polarity);		
	}
	
	public void arcadeDrive(double d, double rightY, double deadband) {
		roboDrive.arcadeDrive(calcDeadband(d, deadband), calcDeadband(rightY, deadband));
	}

	public void tankDrive(double left, double right, double deadband) {
		double ban = deadband;
		roboDrive.tankDrive(calcDeadband(left, ban), calcDeadband(right, ban));
	}

}
