package org.usfirst.frc.team2713.robot.commands;

import org.usfirst.frc.team2713.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

public class GoToAnglePID extends Command {

	double angle;
	double currentAngle = 0;
	CANTalon thisTalon;
	Robot robot;
	
	public GoToAnglePID(CANTalon thisTalon, double angle, Robot robot) {
		this.thisTalon = thisTalon;
		this.angle = angle;
		this.robot = robot;
	}
	
	@Override
	protected void initialize() {
		currentAngle = thisTalon.get();
	}

	@Override
	protected void execute() {
		if(Math.abs(currentAngle - angle) > 3) {
			thisTalon.set(currentAngle);
			if(thisTalon.get() > angle) {
				currentAngle -= 1;
			} else {
				currentAngle += 1;
			}
		}
	}

	@Override
	protected boolean isFinished() {
		if(Math.abs(thisTalon.get() - angle) < 3) {
			return true;
		}
		if(robot.interuptArm) {
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
		// TODO Auto-generated method stub
		
	}

}
