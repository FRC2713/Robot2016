package org.usfirst.frc.team2713.robot;

import org.usfirst.frc.team2713.robot.commands.arm.ManualMoveArm;
import org.usfirst.frc.team2713.robot.commands.grabber.LoadBall;
import org.usfirst.frc.team2713.robot.commands.grabber.LoaderPID;
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
	private JoystickButton setLoader90;
	private JoystickButton setLoader45;
	private JoystickButton loadUp;
	private JoystickButton loadDown;
	private JoystickButton startLoadCommand;
	private JoystickButton shootballGamepad;
	private JoystickButton gateButton;
	private JoystickButton chevalDeFriseButton;
	private JoystickButton shootballXbox;
	private JoystickButton interuptAll;
	Robot robot;

	/**
	 * Gets the XBox class
	 * 
	 * @return XBox Controller
	 */
	public XBoxController getXbox() {
		return xbox;
	}
	
	public boolean manualMoveDrive() {
		if(Math.abs(xbox.getLeftY()) + Math.abs(xbox.getRightX()) > .4) {
			return true;
		}
		return false;
		
	}
	
	public boolean interupted() {
		return interuptAll.get();
	}
	
	public boolean manualMoveLoaderWheels() {
		return loadIn.get() || loadOut.get();
	}
	
	public boolean manualMoveLoader() {
		return loadUp.get() || loadDown.get();
	}
	
	public boolean upperLevelMoveLoader() {
		return setLoader90.get() || setLoader45.get();
	}
	
	public boolean manualMoveArm() {
		return armUp.get() || armDown.get();
	}

	public Joystick getFightGamepad() {
		return gamepad;
	}

	public OI() {
		initController();
	}
	
	public void initCommands(HookArmSubsystem hookarm, LoaderSubsystem loader, LightManager lights, DriveSubsystem drive, Robot robot) {
		loaderCommands(loader, lights, robot);
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
			interuptAll = new JoystickButton(gamepad, 10);
		}
	}

	public void loaderCommands(LoaderSubsystem loader, LightManager lights, Robot robot) {
		
		if (loader != null) {
			if (xbox != null) {
				shootballGamepad = new JoystickButton(gamepad, 4);
				shootballGamepad.whenPressed(new ShootBall(loader, lights, robot));
				shootballXbox = new JoystickButton(xbox, 3);
				shootballXbox.whenPressed(new ShootBall(loader, lights, robot));
			}
			
			if (gamepad != null) {
				setLoader45 = new JoystickButton(xbox, 6);
				setLoader45.whenPressed(new LoaderPID(loader, 45, lights, robot));
				setLoader90 = new JoystickButton(xbox, 5);
				setLoader90.whenPressed(new LoaderPID(loader, 90, lights, robot));
				loadIn = new JoystickButton(gamepad, 5);
				loadIn.whileHeld(new ManualLoadBall(loader, -1));
				loadOut = new JoystickButton(gamepad, 1);
				loadOut.whileHeld(new ManualLoadBall(loader, 1));
				loadUp = new JoystickButton(gamepad, 7);
				loadUp.whileHeld(new ManualMoveLoader(loader, 1));
				loadDown = new JoystickButton(gamepad, 3);
				loadDown.whileHeld(new ManualMoveLoader(loader, -1));
				startLoadCommand = new JoystickButton(gamepad, 9);
				startLoadCommand.whenPressed(new LoadBall(loader, lights, robot));
				
			}
		}
	}

	public void hookArmCommands(HookArmSubsystem hookarm) {
		if (hookarm != null && gamepad != null) {
			armUp = new JoystickButton(gamepad, 6);
			armUp.whileHeld(new ManualMoveArm(hookarm, -1));
			armDown = new JoystickButton(gamepad, 2);
			armDown.whileHeld(new ManualMoveArm(hookarm, 1));
		}
	}

	public void obstacleCommands(HookArmSubsystem hookarm, DriveSubsystem drive, LightManager lights, Robot robot) {
		if (drive != null && hookarm != null && xbox != null) {
			gateButton = new JoystickButton(xbox, 1);
			gateButton.whenPressed(new NavigateGate(drive, hookarm, lights, robot));
			chevalDeFriseButton = new JoystickButton(xbox, 4);
			chevalDeFriseButton.whenPressed(new NavigateChevalDeFrise(drive, hookarm, lights, robot));
		}
	}

}
