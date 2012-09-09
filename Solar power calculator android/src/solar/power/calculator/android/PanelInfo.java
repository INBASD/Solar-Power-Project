package solar.power.calculator.android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class PanelInfo extends Activity {

	
	Datastore data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_info);
        

        Bundle b = this.getIntent().getExtras();
        if(b!=null){
        	data = (Datastore) b.getSerializable(Datastore.EXTRA_MESSAGE);
        }
        
    }
    
    public void done(View v){
    	EditText spa, eu, ec;
    	
    	spa = (EditText) findViewById(R.id.ent_spa);
    	eu  = (EditText) findViewById(R.id.ent_eu);
    	ec  = (EditText) findViewById(R.id.ent_ec);
    	
    	data.setinfogroupTwo(Double.parseDouble(spa.getEditableText().toString()),
    						 Double.parseDouble(eu.getEditableText().toString()), 
    						 Double.parseDouble(ec.getEditableText().toString()));
    	
    	Intent intent = new Intent();
    	Bundle b = new Bundle();
    	b.putSerializable(Datastore.EXTRA_MESSAGE, this.data);
    	intent.putExtras(b);
    	intent.setClass(this, Menu.class);
    	
    	startActivity(intent);
    }
    
    @Override
	public void onPause(){
		super.onPause();
		
		finish();
	}


}
