package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.drive.ArcadeDrive;
import org.usfirst.frc.team2713.robot.commands.drive.TankDrive;
import org.usfirst.frc.team2713.robot.sensors.GyroAccelWrapper;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;

public class DriveSubsystem extends BaseSubsystem {

	public Encoder rightFrontWheelEncoder;
	public Double distanceTraveled;
	public RobotDrive roboDrive;
	public CANTalon right;
	public CANTalon left;
	public CANTalon leftback;
	public CANTalon rightback;
	private Robot robot;
	public GyroAccelWrapper gyro;
	public double powerTotal;

	public DriveSubsystem(Robot robot, GyroAccelWrapper gyro) {
		this.gyro = gyro;
		this.robot = robot;
		
		rightback = new CANTalon(RobotMap.RIGHT_TANK_BACK);
		rightback.configEncoderCodesPerRev(RobotMap.ENCODER_PULSE);
		rightback.setPID(RobotMap.KpDrive, RobotMap.KiDrive, RobotMap.KiDrive);
		rightback.setPIDSourceType(PIDSourceType.kRate);
		
		leftback = new CANTalon(RobotMap.LEFT_TANK_BACK);
		leftback.configEncoderCodesPerRev(RobotMap.ENCODER_PULSE);
		leftback.setPID(RobotMap.KpDrive, RobotMap.KiDrive, RobotMap.KiDrive);
		leftback.setPIDSourceType(PIDSourceType.kRate);
		
		left = new CANTalon(RobotMap.LEFT_TANK);
		left.changeControlMode(TalonControlMode.Follower);
		left.set(RobotMap.LEFT_TANK_BACK);

		right = new CANTalon(RobotMap.RIGHT_TANK);
		right.changeControlMode(TalonControlMode.Follower);
		right.set(RobotMap.RIGHT_TANK_BACK);

		roboDrive = new RobotDrive(leftback, rightback);
	}

	@Override
	public void startTeleop() {
		rightback.changeControlMode(TalonControlMode.PercentVbus);
		leftback.changeControlMode(TalonControlMode.PercentVbus);
		if (robot.getOI().getXbox() != null) {
			if (RobotMap.isTank) {
				new TankDrive(this, robot.getOI().getXbox(), gyro).start();
			} else {
				new ArcadeDrive(this, robot.getOI().getXbox(), gyro).start();
			}
		}
	}

	@Override
	public void startAuto(int defense, int startPos, boolean isRed, boolean leftGoal) {

	}
	
	/**
	 * Deadband is the area of where the controller is meant to be at 0,
	 * but is slightly higher (usually around 1-10)
	 * @param value Value controller reports
	 * @param deadband Deadband area
	 * @return Value compensated for deadband
	 */
	private static double calcDeadband(double value, double deadband) {
		int sign = (value > 0 ? 1 : -1); // checks the sign of the value
		value *= sign; // changes the value to positive
		if (value <= deadband) {
			return 0.0; // returns 0 if it is less than deadband
		} else {
			return (value - deadband) * sign; // returns value minus deadband
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void resetPosition() {
		leftback.setPosition(0);
		rightback.setPosition(0);
		leftback.set(0);
		rightback.set(0);
	}
	
	/**
	 * Moves the robot forward
	 * @param polarity Speed at which to move the robot
	 */
	public void move(double polarity) {
		leftback.set(polarity);
		rightback.set(polarity);
	}

	public void rotate(double angle, boolean radians) {
		if (!radians)
			angle *= Math.PI / 180;
		double pos = angle * (RobotMap.ROBOT_WIDTH / 2);
		leftback.set(-pos);
		rightback.set(pos);
	}
	
	public double getDistanceTraveled() {
		return rightback.getPosition();
	}
	
	public double getAngleRotated() {
		return rightback.getPosition() / (RobotMap.ROBOT_WIDTH / 2);
	}

	public void arcadeDrive(double d, double rightY, double deadband) {
		roboDrive.arcadeDrive(calcDeadband(d, deadband), calcDeadband(rightY, deadband));
	}

	public void tankDrive(double left, double right, double deadband) {
		double ban = deadband;
		roboDrive.tankDrive(calcDeadband(left, ban), calcDeadband(right, ban));
	}

	public double getDriveTotal() {
		return powerTotal;
	}

}
