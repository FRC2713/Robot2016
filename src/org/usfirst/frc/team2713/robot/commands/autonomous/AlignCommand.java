package org.usfirst.frc.team2713.robot.commands.autonomous;

import org.usfirst.frc.team2713.robot.RobotMap;
import org.usfirst.frc.team2713.robot.commands.driveCommands.GoForward;
import org.usfirst.frc.team2713.robot.commands.driveCommands.GoToAngle;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AlignCommand extends CommandGroup {
	private double distanceY;
	private double distanceX;
	
	private Ultrasonic ultrasonic;
	
	public AlignCommand(boolean isLeft, DriveSubsystem drive) {
		ultrasonic = new Ultrasonic(RobotMap.ULTRASONIC_TRIGGER_PORT, RobotMap.ULTRASONIC_ECHO_PORT);
		ultrasonic.setEnabled(true);
		ultrasonic.setAutomaticMode(true);
		ultrasonic.setDistanceUnits(Unit.kInches);
		
		this.addSequential(new GoToAngle(drive, 0));
		this.addSequential(new MeasureDistance(this, false));
		this.addSequential(new GoToAngle(drive, 90 * (isLeft ? 1 : -1)));
		this.addSequential(new MeasureDistance(this, true));
		this.addSequential(new GoForward(drive, 169.55 * (distanceX / 96.33 - 1) + distanceY, false));
	}

	public class MeasureDistance extends Command {
		private AlignCommand main;
		private boolean distanceY;
		
		public MeasureDistance(AlignCommand main, boolean distanceY) {
			this.main = main;
			this.distanceY = distanceY;
		}

		@Override
		protected void initialize() {
			if (distanceY) {
				main.distanceY = ultrasonic.getRangeInches() + (RobotMap.ROBOT_LENGTH / 2 - 2);
			} else {
				main.distanceX = ultrasonic.getRangeInches() + (RobotMap.ROBOT_LENGTH / 2 - 2);
			}
		}

		@Override
		protected void execute() {
		}

		@Override
		protected boolean isFinished() {
			return true;
		}

		@Override
		protected void end() {
		}

		@Override
		protected void interrupted() {
		}
		
	}
}
