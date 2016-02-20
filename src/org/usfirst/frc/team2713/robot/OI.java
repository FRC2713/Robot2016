package org.usfirst.frc.team2713.robot;

import org.usfirst.frc.team2713.robot.commands.LightManager;
import org.usfirst.frc.team2713.robot.exceptions.ControllerNotFound;
import org.usfirst.frc.team2713.robot.commands.archive.ShootShot;
import org.usfirst.frc.team2713.robot.commands.armCommands.MoveHook;
import org.usfirst.frc.team2713.robot.commands.grabberCommands.ManualLoadBall;
import org.usfirst.frc.team2713.robot.commands.grabberCommands.ShootBall;
import org.usfirst.frc.team2713.robot.input.XBoxController;
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
	private LightManager lights;

	public XBoxController getXbox() {
		return xbox;
	} 

	public Joystick getFightGamepad() {
		return gamepad;
	} 

	public OI(FlywheelSubsystem flywheel, HookArmSubsystem hookarm, LoaderSubsystem loader, LightManager lights) {
		initController();
		this.lights = lights;
		if (RobotMap.INIT_LOADER) {
			loaderCommands(loader);
		}

		if (RobotMap.INIT_HOOKARM) {
			hookArmCommands(hookarm);
		}

		if (RobotMap.INIT_FLYWHEEL) {
			flywheelCommands(flywheel);
		}
	}

	public void initController() throws ControllerNotFound {
		for (int i = 0; i < 6; i++) {
			Joystick test = new Joystick(i);
			if (test.getName().equals(RobotMap.XBOX_NAME)) {
				xbox = new XBoxController(i);
				return;
			}
			
			if (test.getName().equals(RobotMap.GAMEPAD_NAME)) {
				gamepad = new Joystick(i);
				return;
			}
		}
		
		throw new ControllerNotFound("No controller found. Is one attached?");
	}

	public void loaderCommands(LoaderSubsystem loader) {
		loadout = new JoystickButton(xbox, 6);
		loadout.whenPressed(new ShootBall(loader, lights));
		loadup = new JoystickButton(gamepad, 6);
		loadup.whileHeld(new ManualLoadBall(loader, 1));
		loadup.whenReleased(new ManualLoadBall(loader, 0));
		loaddown = new JoystickButton(gamepad, 6);
		loaddown.whileHeld(new ManualLoadBall(loader, -1));
		loaddown.whenReleased(new ManualLoadBall(loader, 0));
	}

	public void hookArmCommands(HookArmSubsystem hookarm) {
		armup = new JoystickButton(gamepad, 5);
		armup.whileHeld(new MoveHook(hookarm, 1));
		armup.whenReleased(new MoveHook(hookarm, 0));
		armdown = new JoystickButton(gamepad, 1);
		armdown.whileHeld(new MoveHook(hookarm, 1));
		armdown.whenReleased(new MoveHook(hookarm, 0));
	}

	public void flywheelCommands(FlywheelSubsystem flywheel) {
		System.out.println(xbox);
		shootButton = new JoystickButton(xbox, 3);
		// shootButton.whenPressed(new ShootShot(flywheel, loader));
		shootButton.whenPressed(new ShootShot(flywheel));
	}
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

}
