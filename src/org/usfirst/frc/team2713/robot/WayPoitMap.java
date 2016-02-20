package org.usfirst.frc.team2713.robot;


/**
 * The WaypoitMap keeps track of all of the various different waypoit positions
 * used for the autonomous.
 */
public class WayPoitMap {

	/** //path is same for A-E 0-2 since it is all relative, only differences come with goals
	public static final WayPoit[] Path = {
			new WayPoit(0,0), //0
			new WayPoit(0,0) //1
			};
	**/
	
	//First Waypoit, path is same for A-E 0-2 since it is all relative, only differences come with goals
	public static final WayPoit One = new WayPoit(57.845,0); 
	//Goal Waypoits
	//Left is 0, Right is 1
	//A is 0, E is 4
	//For example, Left C is GoalPoit[0][2]
	//Distance in Inches, Angle
	public static final WayPoit[][] GoalPoit = {
			{//Left		A			B				C				D				E       
			new WayPoit(0,0),new WayPoit(0,0),new WayPoit(0,0),new WayPoit(0,0),new WayPoit(0,0)},
			{//Right	A			B				C				D				E
			new WayPoit(0,0),new WayPoit(0,0),new WayPoit(0,0),new WayPoit(0,0),new WayPoit(0,0)}
			};
	
	public static WayPoit[] firstHalf(int startPoint) {
		return null;	
	}
	
	public static WayPoit[] secondHalf(int startPoint, boolean leftOrRight) {
		return null;	
	}
}
