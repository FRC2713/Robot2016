package org.usfirst.frc.team2713.robot.commands.archive;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.archive.physics.BallPhysics;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.archive.FlywheelSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootShot extends CommandGroup { //Not used
	
	public ShootShot(FlywheelSubsystem flywheel, LoaderSubsystem loader) {
		//Get vision and physics data for speed of rotation
		BallPhysics physics = new BallPhysics(flywheel);
		double speed = physics.getWheelRPS();
		addSequential(new SpinWheelControls(speed, flywheel.flywheelShooter));
		//addSequential(new MoveBallToShooter(loader.ballLoader));
		//addSequential(new FinishShot(loader.ballLoader, flywheel));
		
	}
	
	public ShootShot(FlywheelSubsystem flywheel) {
		//Get vision and physics data for speed of rotation
		double speed = RobotMap.ENCODER_PULSE * 1; //counts per rotation * 4 = rotations per second
		addSequential(new SpinWheelControls(speed, flywheel.flywheelShooter));
		//addSequential(new MoveFromLoadToShoot(loader));
	}
	
}
