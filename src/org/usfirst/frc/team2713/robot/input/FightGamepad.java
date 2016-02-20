package org.usfirst.frc.team2713.robot.input;

import edu.wpi.first.wpilibj.Joystick;

public class FightGamepad extends Joystick {

	public FightGamepad(int port) {
		super(port);
	}
	
	public String getName(){
		return getName();
	}

	public double getXGamepad() {
	        return getRawAxis(1);
	}
	
	public double getYGamepad() {
	        return getRawAxis(2);
	}  
	
	 public boolean getButton1() {
	        return getRawButton(1);
	}

	    public boolean getButton2() {
	        return getRawButton(2);
	}

	    public boolean getButton3() {
	        return getRawButton(3);
	}

	    public boolean getButton4() {
	        return getRawButton(4);
	}
	    
	    public boolean getButton5() {
	        return getRawButton(5);
	}
	    
	    public boolean getButton6() {
	        return getRawButton(6);
	}
	    
	    public boolean getButton7() {
	    	return getRawButton(7);
	}
	    public boolean getButton8()	{
	    	return getRawButton(8);
	}
	    }
