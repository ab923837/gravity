package dal.gravity;

import java.text.NumberFormat;

/** 
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {

	private final static double EARTH_GRAVITY = 9.80665;
	private final static double JUPITAR_GRAVITY = 25;
	
    public static void main (String [] args) {
	NumberFormat nf = NumberFormat.getInstance ();
	nf.setMaximumFractionDigits (3);
	GravityModel gEarth = new GravityConstant(EARTH_GRAVITY);
	GravityModel gJupitar = new GravityConstant(JUPITAR_GRAVITY);
	double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
	double sLen = 10, pMass = 10, theta0 = Math.PI/30;
	RegularPendulum rp = new RegularPendulum (sLen, pMass, theta0, delta,gEarth);
	SimplePendulum sp = new SimplePendulum (sLen, pMass, theta0,gEarth);
	RegularPendulum rpCoarse = new RegularPendulum (sLen, pMass, theta0, .1,gEarth);

	// print out difference in displacement in 1 second intervals
	// for 20 seconds
	int iterations = (int) (1/delta);
	System.out.println("Earth");
	System.out.println ("analytical vs. numerical displacement (fine, coarse)");
	for (int second = 1; second <= 20; second++) {
	    for (int i = 0; i < iterations; i++) 
	    System.out.println ("t=" + second + "s: \t" + 
				nf.format (Math.toDegrees (sp.getTheta (second))) 
				+ "\t" +
				nf.format (Math.toDegrees (rp.getLastTheta ()))
				+ "\t" + 
				nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
	}
	    
	
		rp.setGravitationalField(gJupitar);
		sp.setGravitationalField(gJupitar);
	    System.out.println("\nJupitar");
	    System.out.println ("analytical vs. numerical displacement (fine, coarse)");
		for (int second = 1; second <= 20; second++) {
		    for (int i = 0; i < iterations; i++) 
		    System.out.println ("t=" + second + "s: \t" + 
					nf.format (Math.toDegrees (sp.getTheta (second))) 
					+ "\t" +
					nf.format (Math.toDegrees (rp.getLastTheta ()))
					+ "\t" + 
					nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
		}
    }
}

