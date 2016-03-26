package org.usfirst.frc.team2713.robot.commands.drive;

import org.usfirst.frc.team2713.robot.Robot;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class GoToAngle extends Command{

	DriveSubsystem drive;
	double angle;
	boolean isFinished = false;
	XBoxController xbox;
	
	public GoToAngle(DriveSubsystem drive, double angle, XBoxController xbox) {
		this.drive = drive;
		this.angle = angle - drive.gyro.getAngle();
		this.xbox = xbox;
		requires(drive);
	}
	
	@Override
	protected void initialize() {
		drive.resetPosition();
		drive.rotate(angle, false);
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		if(drive.getAngleRotated() >= angle) {
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		drive.move(0);
	}
	

}
