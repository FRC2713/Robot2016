package org.usfirst.frc.team2713.robot;

/**
 * The WaypoitMap keeps track of all of the various different waypoit positions
 * used for the autonomous.
 */
public class WaypoitMap {

	/** //path is same for A-E 0-2 since it is all relative, only differences come with goals
	public static final WayPoit[] Path = {
			new WayPoit(0,0), //0
			new WayPoit(0,0) //1
			};
	**/
	
	//First Waypoit, path is same for A-E 0-2 since it is all relative, only differences come with goals
	public static final Waypoit ONE = new Waypoit(57.845, 0);
	
	//Goal Waypoits
	//Left is 0, Right is 1
	//A is 0, E is 4
	//For example, Left C is GoalPoit[0][2]
	//Distance in Inches, Angle
	public static final Waypoit[][] GOAL_POIT = {
		{//Left		A						B						C
			new Waypoit(144.667, -21.22), new Waypoit(134.861, 0), new Waypoit(144.703, 21.25) },
		{//Right	D						E
			new Waypoit(150.804, -26.58), new Waypoit(136.313, -8.371) }
	};
	
	public static final double	LEFT_END_X =	 43.347D,
								RIGHT_END_X =	 43.347D,
								LEFT_END_Y =	 80.938D,
								RIGHT_END_Y =	258.047D;
}
