package solar.power.calculator.android;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class Displayresults extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayresults);
        
        Intent intent = getIntent();
        String msg = intent.getStringExtra(RegionInformation.EXTRA_MESSAGE);
        
        TextView txtv = (TextView) findViewById(R.id.disprslt);
        txtv.setText("Your total solar Insolation is " + msg);
    }
}
