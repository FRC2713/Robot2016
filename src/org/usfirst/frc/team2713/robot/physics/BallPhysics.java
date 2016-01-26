package org.usfirst.frc.team2713.robot.physics;

import org.usfirst.frc.team2713.robot.subsystems.FlywheelSubsystem;

public class BallPhysics {

	double pheta = 32 * Math.PI / 180;
	double mass = 1;
	double g = 9.8;
	double initialVelocity = 0;
	double boardLength = 2;
	double frictionBetweenBallAndGround = 0;
	double initHeight = 1;
	FlywheelSubsystem flywheel;
	
	public BallPhysics(FlywheelSubsystem flywheel) {
		this.flywheel = flywheel;
	}
	
	public double getHeightAfterTime(double velocity, double time) {
		double heightFromVelocity = Math.sin(pheta) * velocity * time;
		double heightFromGravity = .5 * g * time * time;
		return heightFromVelocity - heightFromGravity;
	}
	
	
	public double getForwardForce() {
		double forceDueToLoadWheels = getForceDueToLoadWheels();
		double friction =  mass * g * Math.cos(pheta) * frictionBetweenBallAndGround;
		double forceOfGravity = mass * g * Math.sin(pheta);
		double forceForward = (forceDueToLoadWheels - forceOfGravity);
		if(forceForward > friction) {
			forceForward -= friction;
		} else {
			forceForward = 0;
		}
		double velocityIntoLaunch = Math.sqrt(initialVelocity * initialVelocity + 2 * boardLength * forceForward);
		return velocityIntoLaunch;
	}

	public double getForceDueToLaunchWheels() {
		return 14;
	}
	
	public double getForceDueToLoadWheels() {
		return 14;
	}
	
}
