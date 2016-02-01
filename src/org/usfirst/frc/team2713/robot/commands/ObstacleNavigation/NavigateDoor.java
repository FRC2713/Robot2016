package org.usfirst.frc.team2713.robot.commands.ObstacleNavigation;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.GoForward;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
@Deprecated
public class NavigateDoor extends CommandGroup {
	DigitalInput frontSwitch;
	DigitalInput leftSwitch;
	boolean frontPressed = false;
	boolean leftPressed = false;
	Timer timer = new Timer(); // Used as a limit, to make sure the robot doesn't get stuck in this command
	DriveSubsystem drive;
	
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
				addSequential(new GoForward(drive, 1));
			}
			addSequential(new GoForward(drive, 5)); //For a few more seconds to clear gate
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
