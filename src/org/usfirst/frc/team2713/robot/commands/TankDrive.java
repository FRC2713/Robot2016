package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.OI;
 
import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TankDrive extends Command {
	
	DriveSubsystem drive;
	
	public TankDrive(DriveSubsystem drive){
		this.drive = drive;
		requires(drive);
	}
	
	double scaler;
	double deadband;
	double polarity;

	protected void execute() {
		scaler =1.0;
		deadband = 0.1;
		polarity = 1;

		DriveSubsystem.TankDrive(OI.xbox.getLeftY()*scaler, OI.xbox.getRightY()*scaler, deadband);
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
