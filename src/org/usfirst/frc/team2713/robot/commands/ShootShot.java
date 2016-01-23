package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.subsystems.FlywheelSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootShot extends CommandGroup {
	
	public ShootShot(FlywheelSubsystem flywheel, LoaderSubsystem loader) {
		//Get vision and physics data for speed of rotation
		double polarity = 1;
		addSequential(new SpinWheelControls(polarity, flywheel));
		addSequential(new MoveFromLoadToShoot(loader, flywheel));
	}
	
	public ShootShot(FlywheelSubsystem flywheel) {
		//Get vision and physics data for speed of rotation
		double polarity = RobotMap.ENCODER_PULSE * 1; //counts per rotation * 4 = rotations per second
		addSequential(new SpinWheelControls(polarity, flywheel));
		//addSequential(new MoveFromLoadToShoot(loader));
	}
	
}
