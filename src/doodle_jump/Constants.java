package DoodleJump;
/**
 * This is your Constants class. It defines some constants you will need
 * in DoodleJump, using the default values from the demo-- you shouldn’t
 * need to change any of these values unless you want to experiment. You
 * will, however, need to use them.
 *
 * A NOTE ON THE GRAVITY CONSTANT:
 * Because our y-position is in pixels rather than meters, we’ll need our
 * gravity to be in units of pixels/sec 2 rather than the usual 9.8m/sec2.
 * There’s not an exact conversion from pixels to meters since different
 * monitors have varying numbers of pixels per inch, but assuming a fairly
 * standard 72 pixels per inch, that means that one meter is roughly 2800
 * pixels. However, a gravity of 2800 pixels/sec2 might feel a bit fast. We
 * suggest you use a gravity of about 1000 pixels/sec2. Feel free to change
 * this value, but make sure your game is playable with the value you choose.
 */

public class Constants {

	public static final int GRAVITY = 1000;
	public static final int REBOUND_VELOCITY = -600; 
	public static final int DURATION = 16; 
	
	public static final int PLATFORM_WIDTH = 40; 
	public static final int PLATFORM_HEIGHT = 10; 
	public static final int DOODLE_WIDTH = 20; 
	public static final int DOODLE_HEIGHT = 40;

}