package org.usfirst.frc.team2713.robot.subsystems;

import org.usfirst.frc.team2713.robot.OI;

import org.usfirst.frc.team2713.robot.commands.ArcadeDrive;
import org.usfirst.frc.team2713.robot.commands.TankDrive;
import org.usfirst.frc.team2713.robot.input.imu.IMU;
import org.usfirst.frc.team2713.robot.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive; 
import edu.wpi.first.wpilibj.command.Subsystem; 


public class DriveSubsystem extends Subsystem{

	public RobotDrive roboDrive;
	public CANTalon right;
	public CANTalon left;
	public CANTalon leftback;
	public CANTalon rightback;
	OI oi;
	public IMU imu;
	
	public DriveSubsystem(OI oi, IMU imu){
		if(RobotMap.INIT_DRIVE){
			this.imu = imu;
			this.oi = oi;
			left= new CANTalon(RobotMap.LEFT_TANK);
			right= new CANTalon(RobotMap.RIGHT_TANK);
			leftback = new CANTalon(RobotMap.LEFT_TANK_BACK);
			rightback = new CANTalon(RobotMap.RIGHT_TANK_BACK);
			roboDrive = new RobotDrive(left,leftback,right,rightback);
		}
	}
	
	public DriveSubsystem(OI oi){
		if(RobotMap.INIT_DRIVE){
			this.oi = oi;
			left= new CANTalon(RobotMap.LEFT_TANK);
			right= new CANTalon(RobotMap.RIGHT_TANK);
			leftback = new CANTalon(RobotMap.LEFT_TANK_BACK);
			rightback = new CANTalon(RobotMap.RIGHT_TANK_BACK);
			roboDrive = new RobotDrive(left,leftback,right,rightback);
		}
	}
	
	public void startTeleop() {
		if(imu != null) {
			new ArcadeDrive(this, OI.xbox, imu).start();
		} else {
			new ArcadeDrive(this, OI.xbox).start();
		}
	}
	
	public void startAuto(int chosen) {

	}
	
	public void startDisabled() {
		
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
	
	public void goTo(double x, double y, double z) { //In CM, 0,0,0 is the start point
		//TODO Use the IMU
	}

	public void ArcadeDrive(double d, double rightY, double deadband) {
		roboDrive.arcadeDrive(calcDeadband(d, deadband), calcDeadband(rightY, deadband));
	}
	
	public void TankDrive(double left, double right, double deadband){
		double ban = deadband; 
		roboDrive.tankDrive(calcDeadband(left, ban), calcDeadband(right, ban)); 
	}
	
	

}
