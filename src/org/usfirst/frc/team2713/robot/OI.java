package org.usfirst.frc.team2713.robot;

import org.usfirst.frc.team2713.robot.commands.MoveHook;
import org.usfirst.frc.team2713.robot.commands.ShootShot;
import org.usfirst.frc.team2713.robot.commands.LoadBall;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.FlywheelSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.HookArmSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.exceptions.ControllerNotFoundException;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {

	public static XBoxController xbox;
	public static Joystick attack;
	private JoystickButton loadin;
	private JoystickButton loadout;
	private JoystickButton shootButton;
	private JoystickButton armup;
	private JoystickButton armdown;

	public XBoxController getXbox() {
		return xbox;
	}

	public OI(FlywheelSubsystem flywheel, HookArmSubsystem hookarm) {
		for (int i = 0; i < 6; i++) {
			try {
				Joystick test = new Joystick(i);
				if (test.getName().equals(RobotMap.XBOX_NAME)) {
					xbox = new XBoxController(i);
				}
				if (test.getName().equals(RobotMap.ATTACK_NAME)) {
					attack = new Joystick(i);
				}
			} catch (ControllerNotFoundException ex) {

			}
			// loadin = new JoystickButton(xbox, 4);
			// loadin.whileHeld(new LoadBall(1));
			// loadin.whenReleased(new LoadBall(0));

			// loadout = new JoystickButton(xbox, 1);
			// loadout.whileHeld(new LoadBall(-1));
			// loadout.whenReleased(new LoadBall(0));

			armup = new JoystickButton(xbox, 2);
			armup.whileHeld(new MoveHook(hookarm, 1.0));
			armup.whenReleased(new MoveHook(hookarm, 0));

			armdown = new JoystickButton(xbox, 3);
			armdown.whileHeld(new MoveHook(hookarm, -1.0));
			armdown.whenReleased(new MoveHook(hookarm, 0));
			
			shootButton = new JoystickButton(xbox, 4);
			shootButton.whenPressed(new ShootShot(flywheel));
		}

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
