package org.usfirst.frc.team2713.robot;

import org.opencv.core.Scalar;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	
	// Operator Options
	public static final boolean isTank = true; // Else Arcade Drive

	//Talon stuff
	public static final int LEFT_TANK = 1;
	public static final int RIGHT_TANK = 3;
	public static final int LEFT_TANK_BACK = 2;
	public static final int RIGHT_TANK_BACK = 4;
	public static final int ARM_MOTOR = 5;
	public static final int SHOOTER_WHEEL_MOTOR = 6;
	public static final int MOVE_LOAD_MOTOR = 8;
	public static final int BALL_LOADER_MOTOR = 9;
	public static final int LIGHT_TALON = 10;

	//Joystick stuff
	public static final String XBOX_NAME = "Controller (XBOX 360 For Windows)";
	public static final String ATTACK_NAME = "Logitech Attack 3";
	public static final int BACKUP_XBOX_PORT = 2;
	public static final int BACKUP_ATTACK_PORT = 3;
	
	//Flywheel Stuff
	public static final double KpWheel = 3.5;// * Math.random(); //5 is in my mind
	public static final double KiWheel = .04;// * Math.random();
	public static final double KdWheel = 0.4;// * Math.random();
	public static final int ENCODER_PULSE = 256;
	
	//Arm Stuff
	public static final double KpArm = 1;
	public static final double KiArm = .00;
	public static final double KdArm = .0;
	public static final double ARM_LOWER_LIMIT = -0.17453;
	public static final double ARM_UPPER_LIMIT = Math.PI;

	//Drive Stuff
	public static final int RIGHT_FRONT_WHEEL_ENCODER = 4;
	public static final double FRONT_RIGHT_WHEEL_DIAMETER = 1;

	//Initilization stuff
	public static final boolean INIT_DRIVE = true;
	public static final boolean INIT_FLYWHEEL = false;
	public static final boolean INIT_HOOKARM = false;
	public static final boolean INIT_LOADER = false;
	public static final boolean INIT_LIGHTS = false;
	public static final boolean INIT_IMU = true;
	public static final boolean INIT_CAMERA = true;

	//Dip Switch Stuff
	public static final int DIPSWITCHCOUNT = 1;
	public static final int DIPSWITCHSTARTPORT = 0;
	
	//Limit Switches
	public static final int LOADER_LIMIT_SWITCH = 1;
	public static final int LOCK_TO_SHOOT__LIMIT_SWITCH = 0;
	public static final int FRONT_LIMIT_SWITCH = 1;
	public static final int LEFT_LIMIT_SWITCH = 2;

	//Light stuff
	public static final int RED_DIO_PORT = 0;
	public static final int GREEN_DIO_PORT = 1;
	public static final int BLUE_DIO_PORT = 2;
	public static final boolean doMatchDance = true;
	
	//Loader Timings
	public static final int TIME_TO_LOWER_LOADER = 200;
	public static final int TIME_TO_RELEASE_BALL = 200;
	public static final int TIME_TO_LOAD_BALL = 200;
	
	//Camera Stuff
	public static final int CAMERA = 0;
	public static final int CAMERA_VIEW_ANGLE = 52;

	
	public enum ColorThreshold {
		HIGH_GOAL(new Scalar(35D, 64D, 48D, 0D), new Scalar(93D, 144D, 255D, 0D));
		
		private Scalar lowValues, highValues;
		
		private ColorThreshold(Scalar lowValues, Scalar highValues) {
			this.lowValues = lowValues;
			this.highValues = highValues;
		}
		
		public Scalar getLowValues() {
			return lowValues;
		}
		
		public Scalar getHighValues() {
			return highValues;
		}
	}
}
