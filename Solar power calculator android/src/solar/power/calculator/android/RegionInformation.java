package solar.power.calculator.android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.*;

public class RegionInformation extends Activity {
	public final static String EXTRA_MESSAGE = "solar.power.calculator.android.DATASTORE";

	/*
	 * sorta need them here, sorta dont, not sure yet.
	 */
	Button calc;
	EditText lat, terrif, cloudcvr;
	Datastore data;
	Intent intent;
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regional_info);      
        intent = getIntent();
        Bundle b = this.getIntent().getExtras();
        if(b!=null)
        {
        	data = (Datastore) b.getSerializable(Datastore.EXTRA_MESSAGE);
        }
    }
	
	public void calculate(View view){
		//Grab the values from the input box's
		lat  		= (EditText)findViewById(R.id.entLat);
		terrif 		= (EditText)findViewById(R.id.terrif);
		cloudcvr 	= (EditText)findViewById(R.id.cloudcvr);
        
        data.setinfogroupOne(Double.parseDouble(lat.getEditableText().toString()),
        					 Double.parseDouble(terrif.getEditableText().toString()),
        					 Double.parseDouble(cloudcvr.getEditableText().toString()));
		//Create a new intent
        intent = new Intent();
        Bundle b = new Bundle();
        b.putSerializable(Datastore.EXTRA_MESSAGE, data);
		//Pass the Double as a string to the new intent.
        intent.putExtras(b);
        intent.setClass(this, Menu.class);
		//Start the activity
		startActivity(intent);
	}
	
	@Override
	public void onPause(){
		super.onPause();
		
		finish();
	}
}
