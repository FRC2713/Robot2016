package org.usfirst.frc.team2713.robot;

/**
 * The waypointMap keeps track of all of the various different waypoint positions
 * used for the autonomous.
 */
public class WaypointMap {

	/* //path is same for A-E 0-2 since it is all relative, only differences come with goals
	public static final waypoint[] Path = {
			new waypoint(0,0), //0
			new waypoint(0,0) //1
			};
	*/
	
	//First waypoint, path is same for A-E 0-2 since it is all relative, only differences come with goals
	public static final Waypoint ONE = new Waypoint(57.845, 0);
	
	//Left is 0, Right is 1
	//A is 0, E is 4
	//For example, Left C is GoalPoit[0][2]
	//Distance in Inches, Angle
	public static final Waypoint[][] GOAL_POIT = {
		{//Left		A						B						C
			new Waypoint(144.667, -21.22), new Waypoint(134.861, 0), new Waypoint(144.703, 21.25) },
		{//Right	D						E
			new Waypoint(150.804, -26.58), new Waypoint(136.313, -8.371) }
	};
	
	public static final double	LEFT_END_X =	 43.347D,
								RIGHT_END_X =	 43.347D,
								LEFT_END_Y =	 80.938D,
								RIGHT_END_Y =	258.047D;
}
