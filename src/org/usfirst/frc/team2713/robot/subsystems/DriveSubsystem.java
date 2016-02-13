package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.OI;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.driveCommands.ArcadeDrive;
import org.usfirst.frc.team2713.robot.commands.driveCommands.TankDrive;
import org.usfirst.frc.team2713.robot.input.imu.IMU;

import edu.wpi.first.wpilibj.CANTalon;
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
			rightback = new CANTalon(RobotMap.RIGHT_TANK_BACK);
		} catch(CANMessageNotFoundException ex) {
			return;
		}
		roboDrive = new RobotDrive(left, leftback, right, rightback);
		rightFrontWheelEncoder = new Encoder(RobotMap.RIGHT_FRONT_WHEEL_ENCODER, RobotMap.RIGHT_FRONT_WHEEL_ENCODER+1);
		rightFrontWheelEncoder.setDistancePerPulse(RobotMap.FRONT_RIGHT_WHEEL_DIAMETER);
	}

	@Override
	public void startTeleop() {
		if (OI.xbox != null) {
			if (RobotMap.isTank) { // No need to see if imu is null here. This
									// is
									// checked in the classes themselves
			} else {
				new ArcadeDrive(this, OI.xbox, imu).start();
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
		leftback.set(polarity);
		right.set(polarity);
		rightback.set(polarity);
	}

	public void rotate(double polarity) {
		left.set(polarity);
		leftback.set(polarity);
		right.set(-polarity);		
		rightback.set(-polarity);		
	}
	
	public void ArcadeDrive(double d, double rightY, double deadband) {
		roboDrive.arcadeDrive(calcDeadband(d, deadband), calcDeadband(rightY, deadband));
	}

	public void TankDrive(double left, double right, double deadband) {
		double ban = deadband;
		roboDrive.tankDrive(calcDeadband(left, ban), calcDeadband(right, ban));
	}

}
