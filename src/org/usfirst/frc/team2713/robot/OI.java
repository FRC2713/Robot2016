package org.usfirst.frc.team2713.robot;

import org.usfirst.frc.team2713.robot.commands.arm.MoveHook;
import org.usfirst.frc.team2713.robot.commands.grabber.ManualLoadBall;
import org.usfirst.frc.team2713.robot.commands.grabber.ManualMoveLoader;
import org.usfirst.frc.team2713.robot.commands.grabber.ShootBall;
import org.usfirst.frc.team2713.robot.commands.obstacle.NavigateChevalDeFrise;
import org.usfirst.frc.team2713.robot.commands.obstacle.NavigateGate;
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
	private JoystickButton armUp;
	private JoystickButton armDown;
	private JoystickButton loadIn;
	private JoystickButton loadOut;
	private JoystickButton loadUp;
	private JoystickButton loadDown;
	private JoystickButton shootball;
	private JoystickButton gateButton;
	private JoystickButton chevalDeFriseButton;
	Robot robot;

	/**
	 * Gets the XBox class
	 * 
	 * @return XBox Controller
	 */
	public XBoxController getXbox() {
		return xbox;
	}
	
	public boolean manualMoveLoaderWheels() {
		if(loadIn.get() || loadOut.get()) {
			return true;
		}
		return false;
	}
	
	public boolean manualMoveLoader() {
		if(loadUp.get() || loadDown.get()) {
			return true;
		}
		return false;
	}

	public Joystick getFightGamepad() {
		return gamepad;
	}

	public OI() {
		initController();
	}
	
	public void initCommands(HookArmSubsystem hookarm, LoaderSubsystem loader, LightManager lights, DriveSubsystem drive, Robot robot) {
		loaderCommands(loader, lights);
		hookArmCommands(hookarm);
		obstacleCommands(hookarm, drive, lights, robot);
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
				loadIn = new JoystickButton(gamepad, 5);
				loadIn.whileHeld(new ManualLoadBall(loader, -1));
				loadIn.whenReleased(new ManualLoadBall(loader, 0));
				loadOut = new JoystickButton(gamepad, 1);
				loadOut.whileHeld(new ManualLoadBall(loader, 1));
				loadOut.whenReleased(new ManualLoadBall(loader, 0));
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
			armUp = new JoystickButton(gamepad, 6);
			armUp.whileHeld(new MoveHook(hookarm, -10));
			armUp.whenReleased(new MoveHook(hookarm, 0));
			armDown = new JoystickButton(gamepad, 2);
			armDown.whileHeld(new MoveHook(hookarm, 10));
			armDown.whenReleased(new MoveHook(hookarm, 0));
		}
	}

	public void obstacleCommands(HookArmSubsystem hookarm, DriveSubsystem drive, LightManager lights, Robot robot) {
		if (drive != null && hookarm != null && xbox != null) {
			gateButton = new JoystickButton(xbox, 2);
			gateButton.whenPressed(new NavigateGate(drive, hookarm, lights, robot));
			chevalDeFriseButton = new JoystickButton(xbox, 3);
			chevalDeFriseButton.whenPressed(new NavigateChevalDeFrise(drive, hookarm, lights, robot));
		}
	}

}
