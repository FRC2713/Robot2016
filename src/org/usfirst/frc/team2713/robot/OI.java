package org.usfirst.frc.team2713.robot;

import org.usfirst.frc.team2713.robot.commands.grabber.ManualLoadBall;
import org.usfirst.frc.team2713.robot.commands.grabber.ManualMoveLoader;
import org.usfirst.frc.team2713.robot.commands.grabber.PutLoaderAtTopOrBotton;
import org.usfirst.frc.team2713.robot.commands.grabber.ShootBall;
import org.usfirst.frc.team2713.robot.commands.obstacle.NavigateChevalDeFrise;
import org.usfirst.frc.team2713.robot.commands.obstacle.NavigateGate;
import org.usfirst.frc.team2713.robot.input.XBoxController;
import org.usfirst.frc.team2713.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.LoaderSubsystem;
import org.usfirst.frc.team2713.robot.subsystems.lights.LightManager;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	private XBoxController xbox;
	private XBoxController gamepad;
	private JoystickButton loadIn;
	private JoystickButton loadOut;
	private JoystickButton putLoaderAtBottom;
	private JoystickButton putLoaderAtTop;
	private JoystickButton loadUp;
	private JoystickButton loadDown;
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
		//if(Math.abs(xbox.getLeftY()) + Math.abs(xbox.getRightX()) > .4) {
		//	return true;
		//}
		return false;
		
	}
	
	public boolean interupted() {
		if(interuptAll != null) {
		return interuptAll.get();
		}
		return false;
	}
	
	public boolean manualMoveLoaderWheels() {
		//if(loadIn != null && loadOut != null) {
		//	return loadIn.get() || loadOut.get();
		//S}
		return false;
	}
	
	public boolean manualMoveLoader() {
		//if(loadUp != null && loadDown != null) {
		//	return loadUp.get() || loadDown.get();
		//}
		return false;
	}
	
	public boolean upperLevelMoveLoader() {
		//if(putLoaderAtBottom != null && putLoaderAtTop != null) {
		//	return putLoaderAtBottom.get() || putLoaderAtTop.get();
		//}
		return false;
	}
	
	public boolean manualMoveArm() {
		//if(armUp != null && armDown != null) {
		//	return armUp.get() || armDown.get();
		//s} 
		return false;
	}

	public Joystick getFightGamepad() {
		return gamepad;
	}

	public OI() {
		initController();
	}
	
	public void initCommands(LoaderSubsystem loader, LightManager lights, DriveSubsystem drive, Robot robot) {
		loaderCommands(loader, lights, robot);
	}

	public void initController() {
		for (int i = 0; i < 6; i++) {
			Joystick test = new Joystick(i);
			if (test.getName().equals(RobotMap.XBOX_NAME)) {
				xbox = new XBoxController(i);
			}
			if (test.getName().equals(RobotMap.GAMEPAD_NAME)) {
				gamepad = new XBoxController(i);
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
				//shootballGamepad = new JoystickButton(gamepad, 4);
				//shootballGamepad.whenPressed(new ShootBall(loader, lights, robot));
				//shootballXbox = new JoystickButton(xbox, 3);
				//shootballXbox.whenPressed(new ShootBall(loader, lights, robot));
			}
			
			if (gamepad != null) {
				loadIn = new JoystickButton(gamepad, 5);
				loadIn.whileHeld(new ManualLoadBall(loader, 1));
				loadOut = new JoystickButton(gamepad, 1);
				loadOut.whileHeld(new ManualLoadBall(loader, -1));
				loadUp = new JoystickButton(xbox, 6);
				loadUp.whileHeld(new ManualMoveLoader(loader, -1));
				loadDown = new JoystickButton(xbox, 5);
				loadDown.whileHeld(new ManualMoveLoader(loader, 1));
				putLoaderAtTop = new JoystickButton(xbox, 4);
				putLoaderAtTop.whenPressed(new PutLoaderAtTopOrBotton(true, loader));
				putLoaderAtBottom = new JoystickButton(xbox, 1);
				putLoaderAtBottom.whileHeld(new PutLoaderAtTopOrBotton(false, loader));
				//startLoadCommand = new JoystickButton(gamepad, 8);
				//startLoadCommand.whenPressed(new LoadBall(loader, lights, robot));
				
			}
		}
	}

}
