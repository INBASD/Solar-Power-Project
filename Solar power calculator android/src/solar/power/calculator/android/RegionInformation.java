package solar.power.calculator.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.*;

public class RegionInformation extends Activity {

	int counter;
	
	Button add, sub;
	TextView display;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        counter = 0;
        add = (Button) findViewById(R.id.bAdd);
        sub = (Button) findViewById(R.id.bsub);
        display = (TextView) findViewById(R.id.tvDisplay);
        
        add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				counter++;
				setTextdisp(counter);
			}
        });
        
        sub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				counter--;
				setTextdisp(counter);
			}
		});
        
    }
	
	public void setTextdisp(int i)
	{
		display.setText("Your Total is " + i);
	}
}
