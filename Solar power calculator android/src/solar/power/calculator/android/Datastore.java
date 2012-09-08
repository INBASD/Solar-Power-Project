package solar.power.calculator.android;

import java.io.Serializable;


public class Datastore implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 723541007254860330L;

	private double lat, spa, ec, t, eu, ccp;
	
	//String Reference to this datastore
	public final static String EXTRA_MESSAGE = "solar.power.calculator.android.DATASTORE";
	
	/*Constructor Datastore
	 *Basic Constructor
	*/
	public Datastore(){
		
	}
	
	/* setinfogroupOne
	 * Sets Appropriate Variables
	 * @param double lat: Latitude
	 * @param double t  : Terrif
	 * @param double ccp: cloud Coverage Percentage
	 */
	public void setinfogroupOne(double lat, double t, double ccp){
		this.lat = lat; this.t = t; this.ccp = ccp;
	}
	
	/* setinfogroupTwo
	 * Set Appropriate Variables
	 * @param double spa: solar panel Area
	 * @param double eu : Energy Usage
	 * @param double ec : Energy Cost
	 */
	public void setinfogroupTwo(double spa, double eu, double ec){
		this.spa = spa; this.ec = ec; this.eu = eu;
	}
	
	/* getDataSetOne
	 * Returns an array of doubles
	 * @return double[] 
	 */
	public double[] getDataSetOne(){
		double dbls[] = {lat, t, ccp};
		return dbls;
						
	}
	
	/* getDataSetTwo
	 * Returns an array of doubles
	 * @return double[] 
	 */
	public double[] getDataSetTwo(){
		double dbls[] = {spa, eu, ec};
		return dbls;
	}
}
