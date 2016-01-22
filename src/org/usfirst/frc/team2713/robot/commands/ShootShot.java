package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.subsystems.FlywheelSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootShot extends CommandGroup {
	
	public ShootShot(FlywheelSubsystem flywheel) {
		//Get vision and physics data for speed of rotation
		double polarity = 1;
		addSequential(new SpinWheelControls(polarity, flywheel));
		//Shoot ball?
		addSequential(new SpinWheelControls(0, flywheel));
	}
	
}
