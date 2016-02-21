package org.usfirst.frc.team2713.robot;

//Yes, it is supposed to say "waypoit." This is not a typo.
public class Waypoit {
	private double ang;
	private double d;
	
	/**
	 * Lazy solution to a problem where a waypoit was being
	 * passed when it was null.
	 */
	public Waypoit() {
		d = 0D;
		ang = 0D;
	}

	/**
	 * Constructs a waypoit defined in terms of
	 * a distance and angle from a previous waypoit.
	 * 
	 * @param d The distance from the previous waypoit.
	 * @param ang The angle from the previous waypoit.
	 */
	public Waypoit(double d, double ang) {
		this.ang = ang;
		this.d = d;
	}
	
	/**
	 * Constructs a waypoit at (x2, y2) defined in terms of
	 * a distance and angle from (x1, y1).
	 * 
	 * @param x1 x coordinate of start waypoit.
	 * @param y1 y coordinate of start waypoit.
	 * @param x2 x coordinate of end waypoit.
	 * @param y2 y coordinate of end waypoit.
	 */
	public Waypoit(double x1, double y1, double x2, double y2) {
		d = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		ang = Math.atan2(y2 - y1, x2 - x1);
	}
	
	/**
	 * Lazy solution to a problem where a waypoit was being
	 * passed when it was null.
	 */
	public void makeNewXY(double x1, double y1, double x2, double y2) {
		d = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		ang = Math.atan2(y2 - y1, x2 - x1);
	}
	
	public double getDistance() {
		return d;
	}
	
	public double getAngle() {
		return ang;
	}
}
