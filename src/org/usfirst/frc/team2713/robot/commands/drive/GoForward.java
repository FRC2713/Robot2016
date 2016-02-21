package org.usfirst.frc.team2713.robot.commands.drive;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class GoForward extends Command {

	DriveSubsystem drive;
	double polarity;
	double distance;
	double timesRun = 0;
	boolean isFinished = false;
	boolean shouldStopIfStuck;
	XBoxController xbox;

	public GoForward(DriveSubsystem drive, double distance, double polarity, boolean shouldStopIfStuck, XBoxController xbox) {
		this.drive = drive;
		this.distance = distance;
		this.polarity = polarity;
		this.shouldStopIfStuck = shouldStopIfStuck;
		this.xbox = xbox;
		requires(drive);
	}

	@Override
	protected void initialize() {
		drive.rightback.changeControlMode(TalonControlMode.Position);
		drive.leftback.changeControlMode(TalonControlMode.Position);
		drive.rightFrontWheelEncoder.reset();
	}

	@Override
	protected void execute() {
		if ((drive.rightFrontWheelEncoder.get() < distance)) {
			drive.move((distance - drive.rightFrontWheelEncoder.get() / distance) * polarity);
			if (shouldStopIfStuck) {
				timesRun++;
				if (timesRun > 10 && isStuck()) {
					isFinished = true;
				}
			}
		} else {
			isFinished = true;
		}

	}

	@Override
	protected boolean isFinished() {
		double xboxTotal = Math.abs(xbox.getRightY()) + Math.abs(xbox.getLeftY());
		if(xboxTotal > .1) {
			isFinished = true;
		}
 		if(isFinished) {
			drive.rightback.changeControlMode(TalonControlMode.PercentVbus);
			drive.leftback.changeControlMode(TalonControlMode.PercentVbus);
			drive.move(0);
		}
		return isFinished;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	public boolean isStuck() {
		if (drive.imu != null) {
			double acceleration = Math.sqrt(
					drive.imu.getAccelX() * drive.imu.getAccelX() + drive.imu.getAccelY() * drive.imu.getAccelY());
			if (acceleration - RobotMap.ACCELERATION_STOP_POINT < 0
					&& acceleration + RobotMap.ACCELERATION_STOP_POINT > 0) {
				//return false;
			}
		}
		return false;
	}

}
