package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.physics.BallPhysics;
import org.usfirst.frc.team2713.robot.subsystems.FlywheelSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootShot extends CommandGroup {
	
	public ShootShot(FlywheelSubsystem flywheel, LoaderSubsystem loader) {
		//Get vision and physics data for speed of rotation
		BallPhysics physics = new BallPhysics(flywheel);
		double speed = physics.getWheelRPS();
		addSequential(new SpinWheelControls(speed, flywheel.flywheelShooter));
		addSequential(new MoveBallToShooter(loader.swap));
		addSequential(new FinishShot(loader.swap, flywheel));
		
	}
	
	public ShootShot(FlywheelSubsystem flywheel) {
		//Get vision and physics data for speed of rotation
		double speed = RobotMap.ENCODER_PULSE * 1; //counts per rotation * 4 = rotations per second
		addSequential(new SpinWheelControls(speed, flywheel.flywheelShooter));
		//addSequential(new MoveFromLoadToShoot(loader));
	}
	
}
