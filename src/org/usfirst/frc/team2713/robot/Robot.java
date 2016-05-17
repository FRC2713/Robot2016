package org.usfirst.frc.team2713.robot;

import org.usfirst.frc.team2713.robot.sensors.GyroAccelWrapper;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.VisionSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.CameraServer;
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
	private LoaderSubsystem loader;
	public LightManager lights;
	private VisionSubsystem visionSubsystem;
	private CameraServer cameraServer;
	private SendableChooser myPosition;
	private SendableChooser doNothing;
	private SendableChooser doGoal;
	private GyroAccelWrapper gyro;
	public Boolean interuptArm = false;
	public Boolean interuptAllLoaderMover = false;
	public Boolean interuptUpperLevelLoaderMover = false;
	public Boolean interuptLoaderWheels = false;
	public Boolean interuptDrive = false;
	private int timesSinceCameraUpdate = 0;

	static {
		try {
			System.load("/usr/local/share/OpenCV/java/libopencv_java310.so");
		} catch (SecurityException | UnsatisfiedLinkError
				| NullPointerException e) {
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
		SmartDashboard.putData(Scheduler.getInstance());

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				// if (cameraServer != null)
				// cameraServer.releaseCamera();
			}
		}));
	}

	public void initSubsystems() {
		if (gyro == null && RobotMap.INIT_GYRO)
			gyro = new GyroAccelWrapper(); // Calibrated in ADXRS450_Gyro
											// constructor.
		if (visionSubsystem == null && RobotMap.INIT_CAMERA) {
			try {
				visionSubsystem = new VisionSubsystem();
			} catch (RuntimeException ex) {

			}
		}

		if (cameraServer == null && RobotMap.INIT_CAMERA) {
			try {
				cameraServer = CameraServer.getInstance();
				cameraServer.startAutomaticCapture("10.27.13.11");
			} catch (RuntimeException ex) {

			}
		}

		if (lights == null && RobotMap.INIT_LIGHTS) {
			lights = new LightManager();
		}
		if (drive == null && RobotMap.INIT_DRIVE)
			drive = new DriveSubsystem(this, gyro);
		if (loader == null && RobotMap.INIT_LOADER)
			loader = new LoaderSubsystem(lights, this);
		if (RobotMap.INIT_SMART_DASHBOARD) {
			System.out.println("hi");
			myPosition = new SendableChooser();
			myPosition.addDefault("Obstacle: Low Bar", 0);
			myPosition.addObject("Obstacle: Portcullis ", 1);
			myPosition.addObject("Obstacle: Cheval de Frise", 2);
			myPosition.addObject("Obstacle: Ramparts", 3);
			myPosition.addObject("Obstacle: Moat", 4);
			myPosition.addObject("Drawbridge", 5);
			myPosition.addObject("Obstacle: Sally Port", 6);
			myPosition.addObject("Obstacle: Rock Wall", 7);
			myPosition.addObject("Obstacle: Rough Terrain", 8);
			SmartDashboard.putData("Position Chooser", myPosition);
			doNothing = new SendableChooser();
			doNothing.addDefault("Do Something", false);
			doNothing.addObject("Do Nothing", true);
			SmartDashboard.putData("Do Nothing Selector", doNothing);
			doGoal = new SendableChooser();
			doGoal.addDefault("Do Goal + Obstacle", true);
			doGoal.addObject("Do Obstacle Only", false);
			SmartDashboard.putData("Do Goal Selector", doGoal);
			System.out.println("Dashboard Turned On");
		}
		oi = new OI();
		oi.initCommands(loader, lights, drive, this);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		if (drive != null)
			drive.startDisabled();
		if (loader != null)
			loader.startDisabled();
		if (lights != null)
			lights.startDisabled();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		commandsToAlwaysRun();
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
		resetSensors();
		boolean isRed = false;
		boolean leftGoal = false;
		boolean shouldDoNothing;
		// Start of debug messages
		String defenseStr;
		int defense = (Integer) myPosition.getSelected();
		int startPos = (Integer) myPosition.getSelected();
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
			if (loader != null)
				loader.startAuto(defense, startPos, isRed, leftGoal);
			if (lights != null)
				lights.startAuto(defense, startPos, isRed, leftGoal);
			
			//autonomousCommand = new AutonomousCommand(startPos, defense,
			//		leftGoal, ((Boolean) doGoal.getSelected()).booleanValue(),
			//		drive, loader, lights, this, visionSubsystem);
			//if (autonomousCommand != null)
			//	autonomousCommand.start();
		}
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		commandsToAlwaysRun();
	}

	public void teleopInit() {
		if (RobotMap.TEST)
			resetSensors();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		//if (autonomousCommand != null)
		//	autonomousCommand.cancel();
		if (drive != null)
			drive.startTeleop();
		if (loader != null)
			loader.startTeleop();
		if (lights != null)
			lights.startTeleop();
		// if (cameraSubsystem != null)
		// cameraSubsystem.startTeleop();
		// new DataCollection(drive, hookarm, loader, lights, imu).start();
		// new GoForward(drive, 72.0, false).start();
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		commandsToAlwaysRun();
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

	public void resetSensors() {
		if (drive != null)
			drive.resetPosition();
		if (loader != null)
			loader.resetPosition();
		if (gyro != null)
			gyro.reset();
	}

	public void checkLimitSwitches() {
		if (loader != null) {
			// if (!loader.moveLoader.isRevLimitSwitchClosed()) {
			// loader.moveLoader.setPosition(RobotMap.LOADER_LOWER_LIMIT);
			// }
		}
	}

	public void commandsToAlwaysRun() {
		checkLimitSwitches();
		checkTilted();
		checkInteruptions();

		if (lights != null)
			lights.managerLights();
	}

	public void checkTilted() {
		if (gyro != null) {
			double roll = gyro.getRoll();
			double pitch = gyro.getPitch();
			double tilt = Math.sqrt(roll * roll + pitch * pitch) - Math.PI;
			if (lights != null) {
				if (Math.abs(tilt) > RobotMap.IS_TILTED_CONSTANT) {
					lights.setTilted(false);
				} else {
					lights.setTilted(false);
				}
			}
		}
	}

	public GyroAccelWrapper getGyro() {
		return gyro;
	}

	public void checkInteruptions() {
		if (oi != null) {
			if (oi.manualMoveArm()) {
				interuptArm = true;
			} else {
				interuptArm = false;
			}
			if (oi.manualMoveLoader()) {
				interuptAllLoaderMover = true;
			} else {
				interuptAllLoaderMover = false;
			}
			if (oi.upperLevelMoveLoader()) {
				interuptUpperLevelLoaderMover = true;
			} else {
				interuptUpperLevelLoaderMover = false;
			}
			if (oi.manualMoveLoaderWheels()) {
				interuptLoaderWheels = true;
			} else {
				interuptLoaderWheels = false;
			}
			if (oi.manualMoveDrive()) {
				interuptDrive = true;
			} else {
				interuptDrive = false;
			}
			if (oi.interupted()) {
				interuptLoaderWheels = true;
				interuptDrive = true;
				interuptAllLoaderMover = true;
				interuptArm = true;
			}
		}
	}
}
