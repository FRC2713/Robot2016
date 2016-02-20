package org.usfirst.frc.team2713.robot;

import org.usfirst.frc.team2713.robot.commands.LightManager;

import org.usfirst.frc.team2713.robot.commands.ObstacleNavigation.NavigateChevalDeFrise;
import org.usfirst.frc.team2713.robot.commands.ObstacleNavigation.NavigateGate;
import org.usfirst.frc.team2713.robot.commands.archive.ShootShot;
import org.usfirst.frc.team2713.robot.commands.armCommands.MoveHook;
import org.usfirst.frc.team2713.robot.commands.grabberCommands.ManualLoadBall;
import org.usfirst.frc.team2713.robot.commands.grabberCommands.ShootBall;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.archive.FlywheelSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	private XBoxController xbox;
	private Joystick gamepad;
	private JoystickButton loadout;
	private JoystickButton shootButton;
	private JoystickButton armup;
	private JoystickButton armdown;
	private JoystickButton loadup;
	private JoystickButton loaddown;
	private JoystickButton gateButton;
	private JoystickButton chevalDeFriseButton;

	/**
	 * Gets the XBox class
	 * @return XBox Controller
	 */
	public XBoxController getXbox() {
		return xbox;
	} 
	
	/**
	 * Gets the Gamepad class
	 * @return The Gamepad
	 */
	public Joystick getFightGamepad() {
		return gamepad;
	} 

	/**
	 * Initiates all the main OI Subsystems
	 * @param flywheel Flywheel Subsystem
	 * @param hookarm Hookarm Subsystem
	 * @param loader Loader Subsystem
	 * @param lights Lights Subsystem
	 * @param drive Drive Subsystem
	 */
	public OI(FlywheelSubsystem flywheel, HookArmSubsystem hookarm, LoaderSubsystem loader, LightManager lights, DriveSubsystem drive) {
		initController();
		if (loader != null && lights != null) {
			loaderCommands(loader, lights);
		}

		if (hookarm != null) {
			hookArmCommands(hookarm);
		}

		if (flywheel != null) {
			flywheelCommands(flywheel);
		}
		if(hookarm != null && drive != null && lights != null) {
			obstacleCommands(hookarm, drive, lights);
		}
	}

	public void initController() {
		for (int i = 0; i < 6; i++) {
			Joystick test = new Joystick(i);
			if (test.getName().equals(RobotMap.XBOX_NAME)) {
				xbox = new XBoxController(i);
			}
			if (test.getName().equals(RobotMap.GAMEPAD_NAME)) {
				gamepad = new Joystick(i);
			}
		}
	}

	public void loaderCommands(LoaderSubsystem loader, LightManager lights) {
		loadout = new JoystickButton(xbox, 1);
		loadout.whenPressed(new ShootBall(loader, lights));
		loadup = new JoystickButton(gamepad, 5);
		loadup.whileHeld(new ManualLoadBall(loader, 1));
		loadup.whenReleased(new ManualLoadBall(loader, 0));
		loaddown = new JoystickButton(gamepad, 1);
		loaddown.whileHeld(new ManualLoadBall(loader, -1));
		loaddown.whenReleased(new ManualLoadBall(loader, 0));
	}
	

	public void hookArmCommands(HookArmSubsystem hookarm) {
		armup = new JoystickButton(gamepad, 6);
		armup.whileHeld(new MoveHook(hookarm, 1));
		armup.whenReleased(new MoveHook(hookarm, 0));
		armdown = new JoystickButton(gamepad, 2);
		armdown.whileHeld(new MoveHook(hookarm, -1));
		armdown.whenReleased(new MoveHook(hookarm, 0));
	}

	public void flywheelCommands(FlywheelSubsystem flywheel) {
		System.out.println(xbox);
		shootButton = new JoystickButton(xbox, 1);
		// shootButton.whenPressed(new ShootShot(flywheel, loader));
		shootButton.whenPressed(new ShootShot(flywheel));
	}
	
	public void obstacleCommands(HookArmSubsystem hookarm, DriveSubsystem drive, LightManager lights) {
		gateButton = new JoystickButton(xbox, 2);
		gateButton.whenPressed(new NavigateGate(drive, hookarm, lights));
		chevalDeFriseButton = new JoystickButton(xbox, 3);
		chevalDeFriseButton.whenPressed(new NavigateChevalDeFrise(drive, hookarm, lights));
	}

}
