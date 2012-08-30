package solarPowerPackage;

public class InsolationCalculation {

	// insolation is the measure of the ammount of solar radiation striking
	// the ground.  This class gives methods that allow for the calculation (by simulation)
	// of the insolation at any time, allows calculation of the total for
	// any day of the year, and the sum total for any number of years.
	
	//At the lowest level of calculation, degrees are converted to radians using
	// DegreesToRadians();
	private int solarradiation = 1000;
	private int dailySimulationSteps = 10;
	
	//given a location latitude, hour of day, and day of year (jan 1 = 1), 
	// calculate the solar insolation at that instant.
	
	public double HourInsolaton(double latitude, double hour, int day){
		
		double declination;
		double hourangle;
		double zenithangle;
		double insolation;
				
		hourangle = HourToHourAngle(hour);
		
		declination = SolarDeclinaton(day);
		
		zenithangle = ZenithAngle(latitude, declination, hourangle);
		
		insolation = solarradiation * Math.cos(zenithangle);
		
		return insolation;
				
	}
	
	//given a location and a day, calculate the total insolation of the location for that day
	// ignoring the hours before and after sunset.
	// The total insolation can be approximated first by simulating a set of hours.
	//  then averaging the result to calculate average insolation per hour.
	//  then multiplying this by the number of hours in the day.
	
	public double DailyInsolation(double latitude, int day){
		double sunriseHourAngle;
		double declination;
		double sunriseHour;
		
		// calculate declination
		declination = SolarDeclinaton(day);
		//calculate sunrise
		
		//sunrise hour angle
		sunriseHourAngle = SunriseHourAngle(latitude, declination);
		
		//the solar hour, based on the angle
		sunriseHour = -(HourAngleToHour(sunriseHourAngle) - 12);
		
		double averageHourlyInsolation = 0;
		
		//the number of hours per simulation step, given number of hours between sunrise and midday.
		double simulationStepLength = (12 + sunriseHour) / dailySimulationSteps;
		
		for (int i = 1; i <= dailySimulationSteps; i++){
			
			averageHourlyInsolation = (averageHourlyInsolation + this.HourInsolaton(latitude, (i * simulationStepLength), day)) / 2;
			//System.out.println(averageHourlyInsolation);
			
		}
		
		//total daylight hours, time before midday + time after midday is the same as time between sunup and sundown, 
		// which is the same as between sunup and midday * 2.
		double totalDaylightHours = -sunriseHour * 2;
		
		double totalDailyInsolation = totalDaylightHours * averageHourlyInsolation;
		
		//System.out.println("Total Daylight Hours: " + totalDaylightHours);
		//System.out.println("Average insolation: " + averageHourlyInsolation);
		
		return totalDailyInsolation;
	}
	
	
	
	// given a location latitude, and a calculate envelope in years, calculate the total insolation
	// for the number of years specified by the calculation envelope.
	public double TotalSolarInsolation(double latitude, int simulationLength){
		double yearlyInsolation = 0;
		for (int i = 1; i <= 365; i++){
			yearlyInsolation = yearlyInsolation + DailyInsolation(latitude, i);
			//System.out.println(yearlyInsolation);
		}
		
		double simulationInsolation = yearlyInsolation * simulationLength;
		
		return simulationInsolation;
	}
	
	//HOUR TO HOUR ANGLE
	// given an hour (midnight = 0, 12 = midday), calculate the hour angle at 15 degrees per hour.
	// returns radians rotated at this hour
	public double HourToHourAngle(double hourFromMidnight){
		double hourAngle = DegreesToRadians(15*(hourFromMidnight - 12));
		return hourAngle;
	}
	
	//HOUR ANGLE TO HOUR
	// given an hour angle (radians), determine the current hour.
	public double HourAngleToHour(double hourAngle){
		double hourFromMidnight = (hourAngle / DegreesToRadians(15)) + 12;
		return hourFromMidnight;
	}
	
	//DEGREES TO RADIANS helper function
	// given a degrees, convert to radians.
	public double DegreesToRadians(double degrees){
		double radians = (degrees * Math.PI) / 180;
		
		return radians;
	}
	
	//RADIANS TO DEGREES helper function
	// given some radians, convert to degrees.
	
	public double RadiansToDegrees(double radians){
		double degrees = (radians * 180) / Math.PI;
		
		return degrees;
	}
	
	//DETERMINE SOLAR DECLILANATION
	// given a day of the year, determine the solar declination.
	public double SolarDeclinaton(int dayOfYear){
		double declination = DegreesToRadians(23.45) * (Math.sin((DegreesToRadians(360)/365)*(dayOfYear)));
		
		return declination;
	}
	
	//DETERMINE ZENITH ANGLE
	// given a latitude, declination, and Hour Angle, detemine the zenith angle  
	//  which is the sun's angle from 0 (straight up) on that day.
	
	public double ZenithAngle(double latitude, double declination, double hourAngle){
		double latitudeRadians = DegreesToRadians(latitude);
		double zenithangle = (Math.acos( Math.sin(latitudeRadians) * Math.sin(declination)) + ( Math.cos(latitudeRadians) * Math.cos(declination) * Math.cos(hourAngle)));
		
		return zenithangle;
	}
	
	//CALCULATE SUNRISE HOUR ANGLE
	//  Given a latitude and a declination, determine the hour angle at sunrise for that location.
	
	public double SunriseHourAngle(double latitude, double declination){
		double latitudeRadians = DegreesToRadians(latitude);
		double sunriseHourAngle = (Math.acos(-Math.tan(latitudeRadians) * Math.tan(declination)));
		
		return sunriseHourAngle;
	}
	
	//CALCULATE YEARLY SAVINGS
	/*
	 * Given
	 * 1) latitude
	 * 2) solar panel area
	 * 3) energy cost ($/KWhour)
	 * 4) Energy usage(watt hours)
	 * 5) tarrif for energy sold to grid ($/KWhour)
	 * 6) cloud cover percentage
	 * calculate: money saved every year by using solar panel.
	 * 
	 * This function does not calculate when the user breaks even.
	 * 
	 * 1) solar insolation (latitude) * cloud cover * solar panel area = total W hours produced.
	 * 2) energy cost = usage * energy rate.
	 * 3) Daylight energy gross = produced * tarrif.
	 * 4) yearly profit = daylight gross - cost.
	 * 
	 * Sorry about the monolithic method with 7 arguments.
	 */
	
	public double TotalYearlyProfit(double latitude, double solarPanelArea, double energyCost, double tariff, double energyUsage, double cloudCoverPercentage){
		double actualWattHours = (TotalSolarInsolation(latitude, 1) * cloudCoverPercentage * solarPanelArea)/1000; //convert to kilowatt hours
		double totalEnergyCost = energyUsage * energyCost;
		double moneyEarnedFromProduction = actualWattHours * tariff;
		double profit = moneyEarnedFromProduction - totalEnergyCost;
		
		return profit;
	}
	
	
}
