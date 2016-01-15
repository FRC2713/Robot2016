package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.RobotMap;

import edu.wpi.first.wpilibj.CANJaguar;  
import edu.wpi.first.wpilibj.Encoder; 
import edu.wpi.first.wpilibj.Joystick; 
import edu.wpi.first.wpilibj.RobotDrive; 
import edu.wpi.first.wpilibj.RobotDrive.MotorType; 
import edu.wpi.first.wpilibj.command.Subsystem; 


public class DriveSubsystem extends Subsystem{

	public static RobotDrive roboDrive;
	CANJaguar right;
	CANJaguar left;
	
	public DriveSubsystem(){
		if(RobotMap.INIT_DRIVE){
			left= new CANJaguar(RobotMap.LEFT_TANK);
			right= new CANJaguar(RobotMap.RIGHT_TANK);
			roboDrive.setInvertedMotor(MotorType.kFrontLeft, false); // invert the left side motors 
			roboDrive.setInvertedMotor(MotorType.kFrontRight, false);  

		}
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
	

}
