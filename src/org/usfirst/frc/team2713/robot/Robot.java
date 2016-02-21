package org.usfirst.frc.team2713.robot;

import org.usfirst.frc.team2713.robot.commands.LightUpdater;
import org.usfirst.frc.team2713.robot.commands.autonomous.AutonomousCommand;
import org.usfirst.frc.team2713.robot.sensors.IMU;
import org.usfirst.frc.team2713.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public OI oi;
	private DriveSubsystem drive;
	private HookArmSubsystem hookarm;
	private LoaderSubsystem loader;
	private LightManager lights;
	private LightUpdater lightUpdater;
	private CameraSubsystem camera;
	private IMU imu;
	private SendableChooser myPossition;
	private SendableChooser myObstacle;
	private SendableChooser doNothing;

	AutonomousCommand autonomousCommand;

	static {
		try {
			System.load("/usr/local/share/OpenCV/java/libopencv_java310.so");
		} catch (SecurityException | UnsatisfiedLinkError | NullPointerException e) {
			e.printStackTrace();
			System.out.println("OpenCV could not be loaded. Is it installed?");
			System.exit(8);
		}
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		initSubsystems();

		oi = new OI(hookarm, loader, lights, drive);

		SmartDashboard.putData(Scheduler.getInstance());

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				if (camera != null)
					camera.releaseCamera();
			}
		}));
	}

	public void initSubsystems() {
		if (imu == null && RobotMap.INIT_IMU)
			imu = new IMU();
		if (camera == null && RobotMap.INIT_CAMERA)
			camera = new CameraSubsystem();
		if (lights == null && RobotMap.INIT_LIGHTS) {
			lights = new LightManager();
			lightUpdater = new LightUpdater(lights);
		}
		if (drive == null && RobotMap.INIT_DRIVE) {
			try {
				drive = new DriveSubsystem(this, imu);
			} catch (RuntimeException ex) {
				drive = null;
			}
		}
		if (loader == null && RobotMap.INIT_LOADER)
			loader = new LoaderSubsystem(lights, oi);
		if (hookarm == null && RobotMap.INIT_HOOKARM)
			hookarm = new HookArmSubsystem();
		if (RobotMap.INIT_SMART_DASHBOARD) {
			myPossition = new SendableChooser();
			myPossition.addDefault("Possition Low Bar", 1);
			myPossition.addObject("Possition 2", 2);
			myPossition.addObject("Possition 3", 3);
			myPossition.addObject("Possition 4", 4);
			myPossition.addObject("Possition 5", 5);
			SmartDashboard.putData("Possition Chooser", myPossition);
			myObstacle = new SendableChooser();
			myObstacle.addDefault("Obstacle: Low Bar", 0);
			myObstacle.addObject("Obstacle: Portcullis ", 1);
			myObstacle.addObject("Obstacle: Cheval de Frise", 2);
			myObstacle.addObject("Obstacle: Ramparts", 3);
			myObstacle.addObject("Obstacle: Moat", 4);
			myObstacle.addObject("Drawbridge", 5);
			myObstacle.addObject("Obstacle: Sally Port", 6);
			myObstacle.addObject("Obstacle: Rock Wall", 7);
			myObstacle.addObject("Obstacle: Rough Terrain", 8);
			SmartDashboard.putData("Obstacle Chooser", myObstacle);
			doNothing = new SendableChooser();
			doNothing.addDefault("Do Something", false);
			doNothing.addObject("Do Nothing", true);
			SmartDashboard.putData("Do Nothing Selector", doNothing);
			System.out.println("Dashboard Turned On");
		}
	
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		if (drive != null)
			drive.startDisabled();
		if (hookarm != null)
			hookarm.startDisabled();
		if (loader != null)
			loader.startDisabled();
		if (lights != null)
			lights.startDisabled();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		if(lights != null) {
			lights.managerLights();
			lightUpdater.updateLights();
		}
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		Scheduler.getInstance().run();
		hookarm.resetPostition();
		drive.resetPosition();
		loader.resetPossition();
		boolean isRed = false;
		boolean leftGoal = false;
		boolean shouldDoNothing;
		// Start of debug messages
		String defenseStr;
		int defense = (Integer) myObstacle.getSelected();
		int startPos = (Integer) myPossition.getSelected();
		shouldDoNothing = (Boolean) doNothing.getSelected();
		switch (defense) {
		case 0:
			defenseStr = "Low bar";
			break;
		case 1:
			defenseStr = "Portcullis";
			break;
		case 2:
			defenseStr = "Cheval de Frise";
			break;
		case 3:
			defenseStr = "Ramparts";
			break;
		case 4:
			defenseStr = "Moat";
			break;
		case 5:
			defenseStr = "Drawbridge";
			break;
		case 6:
			defenseStr = "Sally Port";
			break;
		case 7:
			defenseStr = "Rock Wall";
			break;
		case 8:
			defenseStr = "Rough Terrain";
			break;
		default:
			defenseStr = "";
			break;
		}
		System.out.printf("Defense: %s\nPosition: %d", defenseStr, startPos);
		// End of debug messages
		if (!shouldDoNothing) {

			if (drive != null)
				drive.startAuto(defense, startPos, isRed, leftGoal);

			if (hookarm != null)
				hookarm.startAuto(defense, startPos, isRed, leftGoal);
			if (loader != null)
				loader.startAuto(defense, startPos, isRed, leftGoal);

			if (lights != null)
				lights.startAuto(defense, startPos, isRed, leftGoal);
			autonomousCommand = new AutonomousCommand(defense, startPos, leftGoal, drive, loader, hookarm,
					lights, oi, camera);
			if (autonomousCommand != null)
				autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		if (lights != null) {
			lights.managerLights();
			lightUpdater.updateLights();
		}
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		Scheduler.getInstance().run();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		System.out.println(drive);
		if (drive != null)
			drive.startTeleop();
		if (hookarm != null)
			hookarm.startTeleop();
		if (loader != null)
			loader.startTeleop();
		if (lights != null)
			lights.startTeleop();
		//new DataCollection(drive, hookarm, loader, lights, imu).start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if (lights != null) {
			lights.managerLights();
			lightUpdater.updateLights();
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	public OI getOI() {
		return oi;
	}
}
