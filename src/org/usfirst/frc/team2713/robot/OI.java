package org.usfirst.frc.team2713.robot;

import org.usfirst.frc.team2713.robot.commands.ObstacleNavigation.NavigateChevalDeFrise;
import org.usfirst.frc.team2713.robot.commands.ObstacleNavigation.NavigateGate;
import org.usfirst.frc.team2713.robot.commands.armCommands.MoveHook;
import org.usfirst.frc.team2713.robot.commands.grabberCommands.ManualLoadBall;
import org.usfirst.frc.team2713.robot.commands.grabberCommands.ManualMoveLoader;
import org.usfirst.frc.team2713.robot.commands.grabberCommands.ShootBall;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	private XBoxController xbox;
	private Joystick gamepad;
	private JoystickButton armup;
	private JoystickButton armdown;
	private JoystickButton loadin;
	private JoystickButton loadout;
	private JoystickButton loadUp;
	private JoystickButton loadDown;
	private JoystickButton shootball;
	private JoystickButton gateButton;
	private JoystickButton chevalDeFriseButton;

	/**
	 * Gets the XBox class
	 * 
	 * @return XBox Controller
	 */
	public XBoxController getXbox() {
		return xbox;
	}

	public Joystick getFightGamepad() {
		return gamepad;
	}

	public OI(HookArmSubsystem hookarm, LoaderSubsystem loader, LightManager lights, DriveSubsystem drive) {
		initController();
		loaderCommands(loader, lights);
		hookArmCommands(hookarm);
		obstacleCommands(hookarm, drive, lights);
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
		if (xbox == null) {
			xbox = new XBoxController(RobotMap.BACKUP_XBOX_PORT);
		}
		if (gamepad == null) {
			gamepad = new XBoxController(RobotMap.BACKUP_ATTACK_PORT);
		}
	}

	public void loaderCommands(LoaderSubsystem loader, LightManager lights) {
		if (loader != null) {
			if (xbox != null) {
				shootball = new JoystickButton(xbox, 1);
				shootball.whenPressed(new ShootBall(loader, lights));
			}
			if (gamepad != null) {
				loadin = new JoystickButton(gamepad, 5);
				loadin.whileHeld(new ManualLoadBall(loader, -1));
				loadin.whenReleased(new ManualLoadBall(loader, 0));
				loadout = new JoystickButton(gamepad, 1);
				loadout.whileHeld(new ManualLoadBall(loader, 1));
				loadout.whenReleased(new ManualLoadBall(loader, 0));
				loadUp = new JoystickButton(gamepad, 7);
				loadUp.whileHeld(new ManualMoveLoader(loader, 2));
				loadUp.whenReleased(new ManualMoveLoader(loader, 0));
				loadDown = new JoystickButton(gamepad, 3);
				loadDown.whileHeld(new ManualMoveLoader(loader, -2));
				loadDown.whenReleased(new ManualMoveLoader(loader, 0));
			}
		}
	}

	public void hookArmCommands(HookArmSubsystem hookarm) {
		if (hookarm != null && gamepad != null) {
			armup = new JoystickButton(gamepad, 6);
			armup.whileHeld(new MoveHook(hookarm, -10));
			armup.whenReleased(new MoveHook(hookarm, 0));
			armdown = new JoystickButton(gamepad, 2);
			armdown.whileHeld(new MoveHook(hookarm, 10));
			armdown.whenReleased(new MoveHook(hookarm, 0));
		}
	}

	public void obstacleCommands(HookArmSubsystem hookarm, DriveSubsystem drive, LightManager lights) {
		if (drive != null && hookarm != null && xbox != null) {
			gateButton = new JoystickButton(xbox, 2);
			gateButton.whenPressed(new NavigateGate(drive, hookarm, lights));
			chevalDeFriseButton = new JoystickButton(xbox, 3);
			chevalDeFriseButton.whenPressed(new NavigateChevalDeFrise(drive, hookarm, lights));
		}
	}

}
