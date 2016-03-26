package org.usfirst.frc.team2713.robot.commands.camera;

import org.usfirst.frc.team2713.robot.subsystems.CameraSubsystem;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class CameraFlip extends Command {
	private CameraSubsystem cameraSubsystem;
	
	public CameraFlip(CameraSubsystem cameraSubsystem, String cameraName) {
		this.cameraSubsystem = cameraSubsystem;
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		//camera.stopCapture();
	}

	@Override
	protected void interrupted() {
		//camera.stopCapture();

	}

}
