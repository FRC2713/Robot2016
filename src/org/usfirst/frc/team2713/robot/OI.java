package org.usfirst.frc.team2713.robot;

import org.usfirst.frc.team2713.robot.commands.MoveHook;

import org.usfirst.frc.team2713.robot.commands.lights.SetColor;
import org.usfirst.frc.team2713.robot.exceptions.ControllerNotFound;
import org.usfirst.frc.team2713.robot.commands.ShootBall;
import org.usfirst.frc.team2713.robot.commands.archive.ShootShot;
import org.usfirst.frc.team2713.robot.commands.lights.Color;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LightSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.archive.FlywheelSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public static XBoxController xbox;
	public static Joystick attack;
	private JoystickButton loadout;
	private JoystickButton shootButton;
	private JoystickButton armup;
	private JoystickButton armdown;

	public XBoxController getXbox() throws ControllerNotFound {
		if (xbox != null){
			return xbox;
		} else {
			throw new ControllerNotFound("XBox Controller not Found");
		}
	} 

	public Joystick getJoystick() throws ControllerNotFound {
		if (attack != null){
			return attack;
		} else {
			throw new ControllerNotFound("Attack Controller not Found");
		}
	} 
	
	
	public OI(HookArmSubsystem hookarm, LoaderSubsystem loader) {
		initController();
		if (RobotMap.INIT_LOADER) {
			loaderCommands(loader);
		}

		if (RobotMap.INIT_HOOKARM) {
			hookArmCommands(hookarm);
		}
	}

	public OI(FlywheelSubsystem flywheel, HookArmSubsystem hookarm, LoaderSubsystem loader) {
		initController();
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

	public void initController() {
		for (int i = 0; i < 6; i++) {
			Joystick test = new Joystick(i);
			if (test.getName().equals(RobotMap.XBOX_NAME)) {
				xbox = new XBoxController(i);
			}
			if (test.getName().equals(RobotMap.ATTACK_NAME)) {
				attack = new Joystick(i);
			}
		}
		if (xbox == null) {
			xbox = new XBoxController(RobotMap.BACKUP_XBOX_PORT); // Joystick not present exception?
		}
		if (attack == null) {
			attack = new Joystick(RobotMap.BACKUP_ATTACK_PORT);
		}
	}

	public void loaderCommands(LoaderSubsystem loader) {
		loadout = new JoystickButton(xbox, 6);
		loadout.whenPressed(new ShootBall(loader));

	}

	public void hookArmCommands(HookArmSubsystem hookarm) {
		armup = new JoystickButton(xbox, 4);
		armup.whileHeld(new MoveHook(hookarm, 1));
		armup.whenReleased(new MoveHook(hookarm, 0));
		armdown = new JoystickButton(xbox, 1);
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
