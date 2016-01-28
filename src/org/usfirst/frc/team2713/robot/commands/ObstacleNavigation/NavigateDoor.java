package org.usfirst.frc.team2713.robot.commands.ObstacleNavigation;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.GoForward;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class NavigateDoor extends Command {
	DigitalInput frontSwitch;
	DigitalInput leftSwitch;
	boolean frontPressed;
	boolean leftPressed;
	Timer timer;

	@Override
	protected void initialize() {
		frontSwitch = new DigitalInput(RobotMap.FRONT_LIMIT_SWITCH);
		leftSwitch = new DigitalInput(RobotMap.LEFT_LIMIT_SWITCH);
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		if (!frontSwitch.get()){
			while (!frontSwitch.get()){
				//TODO Go Forward + 3-5 seconds
			}
			frontPressed = true;
		} else {
			frontPressed = true;
		}
		
		if (!leftSwitch.get()){
			while (!leftSwitch.get()){
				//TODO Go Left
			}
			leftPressed = true;
		} else {
			leftPressed = true;
		}	
	}

	@Override
	protected boolean isFinished() {
		if (timer.get() <= 10000 || frontPressed && leftPressed){
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		timer.stop();
	}

	@Override
	protected void interrupted() {
		timer.stop();
	}

}
