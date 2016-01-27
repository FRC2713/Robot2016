package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

public class GoForward extends Command{

	DriveSubsystem drive;
	double polarity;
	double time;
	Timer timer;
	
	public GoForward(DriveSubsystem drive, double time1) {
		this.drive = drive;
		requires(drive);
		time=time1;
		timer = new Timer();
		timer.reset(); 
	    timer.start(); 

	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if ((timer.get() < time)) {
			drive.move(1);
		} else {
			drive.move(0);
		}

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