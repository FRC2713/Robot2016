package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.OI;
import org.usfirst.frc.team2713.robot.commands.TankDrive;
import org.usfirst.frc.team2713.robot.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive; 
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem; 


public class DriveSubsystem extends Subsystem{

	public static RobotDrive roboDrive;
	CANTalon right;
	CANTalon left;
	CANTalon leftback;
	CANTalon rightback;
	
	public DriveSubsystem(){
		if(RobotMap.INIT_DRIVE){
			left= new CANTalon(RobotMap.LEFT_TANK);
			right= new CANTalon(RobotMap.RIGHT_TANK);
			leftback = new CANTalon(RobotMap.LEFT_TANK_BACK);
			rightback = new CANTalon(RobotMap.RIGHT_TANK_BACK);
			roboDrive = new RobotDrive(left,leftback,right,rightback);
		}
	}
	
	public void startTeleop() {
		new TankDrive(this, OI.xbox).start();
	}
	
	public void startAuto(int chosen) {

	}
	
	public void startDisabled() {
		
	}
	
	public static void TankDrive(double left, double right, double deadband){
		double ban = deadband; 
		roboDrive.tankDrive(calcDeadband(left, ban), calcDeadband(right, ban)); 
	}
	
	
	private static double calcDeadband(double value, double deadband) { 
		 	int sign = (value > 0 ? 1 : -1); // checks the sign of the value 
		 	value *= sign; // changes the value to positive 
		 if (value <= deadband) { 
			return 0.0; // returns 0 if it is less than deadband
	 		}
		 else { 
	 			return (value - deadband) * sign; // returns vale minus deadband 
	 		} 
	} 
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public void move(double polarity) {
		left.set(polarity);
		right.set(polarity);
	}
	

}
