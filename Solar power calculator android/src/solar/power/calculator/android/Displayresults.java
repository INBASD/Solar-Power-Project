package solar.power.calculator.android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

public class Displayresults extends Activity {

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * This function is overwridden to Display the results of the previous
	 * Activities Calculations
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayresults);
        
        //get the intent
        Intent intent = getIntent();
        //Grab the message
        String msg = intent.getStringExtra(RegionInformation.EXTRA_MESSAGE);
        //Get the Text Field statically placed by the XML
        TextView txtv = (TextView) findViewById(R.id.disprslt);
        //Assign the sting to the TextField
        txtv.setText("Your total solar Insolation is " + msg);
    }
}
