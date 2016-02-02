package org.usfirst.frc.team2713.robot;

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

	public static final int LEFT_TANK = 1;
	public static final int RIGHT_TANK = 3;
	public static final int LEFT_TANK_BACK = 2;
	public static final int RIGHT_TANK_BACK = 4;
	public static final int ARM_MOTOR = 5;
	public static final int SHOOTER_WHEEL_MOTOR = 6;
	public static final int LOADER_WHEEL_MOTOR = 7;
	public static final int LOAD_MOTOR = 8;
	public static final int BALL_LOADER_MOTOR = 9;
	public static final int LIGHT_TALON = 10;
	public static final int FRONT_LIMIT_SWITCH = 1;
	public static final int LEFT_LIMIT_SWITCH = 2;


	public static final String XBOX_NAME = "Controller (Xbox 360 For Windows)";
	public static final String ATTACK_NAME = "Logitech Attack 3";
	public static final int LEFT_JOYSTICK_PORT = 2;
	public static final int RIGHT_JOYSICK_PORT = 3;
	
	public static final double KpWheel = 3.5;// * Math.random(); //5 is in my mind
	public static final double KiWheel = .04;// * Math.random();
	public static final double KdWheel = 0.4;// * Math.random();
	public static final int ENCODER_PULSE = 256;
	
	public static final double KpArm = .01;
	public static final double KiArm = .00;
	public static final double KdArm = .0;

	public static final boolean INIT_DRIVE = true;
	public static final boolean INIT_FLYWHEEL = false;
	public static final boolean INIT_HOOKARM = false;
	public static final boolean INIT_LOADER = false;
	public static final boolean INIT_LIGHTS = false;
	public static final boolean INIT_IMU = true;

	public static final int DIPSWITCHCOUNT = 1;
	public static final int DIPSWITCHSTARTPORT = 10;
	
	public static final int LOADER_LIMIT_SWITCH = 1;

	public static final boolean USE_LIGHT_JAG = true;
	public static final int RED_PWM_PORT = 1;
	public static final int GREEN_PWM_PORT = 2;
	public static final int BLUE_PWM_PORT = 3;
	
}
