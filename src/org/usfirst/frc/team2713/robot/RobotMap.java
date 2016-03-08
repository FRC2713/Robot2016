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
	public static final boolean isTank = false; // Else Arcade Drive

	//Talon stuff
	public static final int LEFT_TANK = 1;
	public static final int RIGHT_TANK = 4;
	public static final int LEFT_TANK_BACK = 2;
	public static final int RIGHT_TANK_BACK = 3;
	public static final int ARM_MOTOR = 5;
	public static final int MOVE_LOAD_MOTOR = 6;
	public static final int BALL_LOADER_MOTOR = 7;

	//Joystick stuff
	public static final String XBOX_NAME = "Controller (XBOX 360 For Windows)";
	public static final String ATTACK_NAME = "Logitech Attack 3";
	public static final String GAMEPAD_NAME = "Mayflash Arcade Stick";
	public static final int BACKUP_XBOX_PORT = 2;
	public static final int BACKUP_ATTACK_PORT = 3;
	
	//Arm Stuff
	public static final double KpArm = 8D;
	public static final double KiArm = 0D;
	public static final double KdArm = 0D;
	public static final double ARM_LOWER_LIMIT = 0;
	public static final double ARM_UPPER_LIMIT = Math.PI;
	public static final double ARM_ANGLE_STOP_POINT = .2;
	public static final int ENCODER_PULSE = 1;

	//Drive Stuff
	public static final double ACCELERATION_STOP_POINT = .1;
	public static final double IS_TILTED_CONSTANT = 1;
	public static final double KpDrive = .03;
	public static final double KiDrive = .00;
	public static final double KdDrive = 2;

	//Initialization stuff
	public static final boolean INIT_DRIVE = false;
	public static final boolean INIT_HOOKARM = false;
	public static final boolean INIT_LOADER = false;
	public static final boolean INIT_LIGHTS = true;
	public static final boolean INIT_CAMERA = false;
	public static final boolean INIT_SMART_DASHBOARD = false;
	public static final boolean INIT_GYRO = false;
	
	//Limit Switches
	public static final int LOADER_LIMIT_SWITCH = 1;

	//Light stuff
	public static final int RED_DIO_PORT = 7;
	public static final int GREEN_DIO_PORT = 8;
	public static final int BLUE_DIO_PORT = 9;
	public static final boolean doMatchDance = true;
	
	//Loader Stuff
	public static final int TIME_TO_RELEASE_BALL = 1000;
	public static final int TIME_TO_LOAD_BALL = 200;
	public static final double KiLoader = 0;
	public static final double KdLoader = 0;
	public static final double KpLoader = 25;
	public static final double LOADER_LOWER_LIMIT = -10; //Might have to swap
	public static final double LOADER_UPPER_LIMIT = 90;
	
	//Camera Stuff
	public static final int BACK_CAMERA = 0;
	public static final int FRONT_CAMERA = 1;
	public static final int CAMERA_VIEW_ANGLE = 52;

	//Obstacle Settings
	public static final double CHEVAL_DE_FRISE_DISTANCE = 2;
	public static final double SPEED_TO_DO_GATE = .5;
	public static final double GATE_DISTANCE = 2;
	public static final double LOW_BAR_DISTANCE = 0;
	public static final double SMALL_RAMP_DISTANCE = 0;
	
	//Robot Dimensions
	public static final double ROBOT_WIDTH = 31.5; //From side to side
	public static final double ROBOT_LENGTH = 28.31; //Front to back
	public static final double ROBOT_HEIGHT = 13.883;

	//Ultrasonic stuff
	public static final int SIDE_ULTRASONIC_ECHO_PORT = 3;
	public static final int SIDE_ULTRASONIC_TRIGGER_PORT = 4;
	public static final int FRONT_ULTRASONIC_ECHO_PORT = 5;
	public static final int FRONT_ULTRASONIC_TRIGGER_PORT = 6;

	public static final boolean TEST = true;
	
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
