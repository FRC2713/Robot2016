
package org.usfirst.frc.team2713.robot;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team2713.robot.commands.ExampleCommand;
import org.usfirst.frc.team2713.robot.input.imu.IMU;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LightSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.archive.FlywheelSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
	public DigitalInput[] autonomousSwitches;
	DriveSubsystem drive;
	FlywheelSubsystem flywheel;
	HookArmSubsystem hookarm;
	LoaderSubsystem loader;
	LightSubsystem lights;
	IMU imu = new IMU();

	Command autonomousCommand;
	
	static {
		try {
			System.load("/usr/local/share/OpenCV/java/libopencv_java310.so");
		} catch (Throwable t) {
			t.printStackTrace();
			System.out.println("OpenCV could not be loaded. Is it installed?");
			System.exit(1);
		}
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		initSubsystems();
		//imu.initImu();
		autonomousSwitches = new DigitalInput[RobotMap.DIPSWITCHCOUNT];
		for (int i = 0; i < RobotMap.DIPSWITCHCOUNT; i++) {
			autonomousSwitches[i] = new DigitalInput(i + RobotMap.DIPSWITCHSTARTPORT);
		}
		oi = new OI(flywheel, hookarm, loader);
		SmartDashboard.putData(Scheduler.getInstance());
		
		VideoCapture capture = new VideoCapture(RobotMap.CAMERA);
		if(!capture.isOpened()) {
			System.out.println("Camera not opened.");
		} else {
			Mat image = new Mat();
			capture.read(image);
			Imgcodecs.imwrite(String.format("%s/%d.jpg", System.getProperty("user.home"), System.currentTimeMillis()), image);
		}
	}
	

	public void initSubsystems() {
		if (flywheel == null && RobotMap.INIT_FLYWHEEL)
			flywheel = new FlywheelSubsystem();
		if (drive == null && RobotMap.INIT_DRIVE)
			drive = new DriveSubsystem();
		if (loader == null && RobotMap.INIT_LOADER)
			loader = new LoaderSubsystem();
		if (hookarm == null && RobotMap.INIT_HOOKARM)
			hookarm = new HookArmSubsystem();
		if (lights == null && RobotMap.INIT_LIGHTS)
			lights = new LightSubsystem();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		if (flywheel != null)
			flywheel.startDisabled();
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
		int chosen = 0;
		for (int i = 0; i < autonomousSwitches.length; i++) {
			if (autonomousSwitches[i].get()) {
				chosen = i;
				break;
			}
		}
		if (flywheel != null)
			flywheel.startAuto(chosen);

		if (drive != null)
			drive.startAuto(chosen);

		if (hookarm != null)
			hookarm.startAuto(chosen);

		if (loader != null)
			loader.startAuto(chosen);
		
		if (lights != null)
			lights.startAuto(chosen);

		switch (chosen) {
		case 0:
			autonomousCommand = new ExampleCommand();
		}

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		Scheduler.getInstance().run();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		if (flywheel != null)
			flywheel.startTeleop();
		if (drive != null)
			drive.startTeleop();
		if (hookarm != null)
			hookarm.startTeleop();
		if (loader != null)
			loader.startTeleop();
		if (lights != null)
			lights.startTeleop();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
