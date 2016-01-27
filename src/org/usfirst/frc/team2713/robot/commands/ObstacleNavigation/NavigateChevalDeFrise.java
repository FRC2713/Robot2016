package org.usfirst.frc.team2713.robot.commands.ObstacleNavigation;

import org.usfirst.frc.team2713.robot.commands.GoForward;
import org.usfirst.frc.team2713.robot.commands.MoveHook;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;




public class NavigateChevalDeFrise extends CommandGroup {

	HookArmSubsystem hookarm;
	DriveSubsystem drive;
	@Override
	
	protected void initialize() {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		addSequential(new MoveHook(hookarm, 1));
		addSequential(new GoForward(drive, 4));
		
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
