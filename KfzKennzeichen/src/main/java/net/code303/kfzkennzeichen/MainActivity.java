package net.code303.kfzkennzeichen;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private LicensePlateManager manager;
    private String currentLicensePlateTag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = LicensePlateManager.getInstance();
        if( savedInstanceState != null ) {
            String lastDisplayedLicensePlate = savedInstanceState.getString("licensePlateTag");
            LicensePlate lastLicensePlate = manager.getLicensePlate(lastDisplayedLicensePlate);
            if(lastLicensePlate != null) {
                drawResult(lastLicensePlate);
                currentLicensePlateTag = lastLicensePlate.getTag();
                setTitle(lastLicensePlate.getTag());
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // This is the handler for the
    public void runSearch(View view) {
        Log.i("MyFilterTag", "Run search.");
        EditText editText = (EditText) findViewById(R.id.editTextInput);
        String input = editText.getText().toString();
        input = input.trim().toUpperCase();
        LicensePlate licensePlate = manager.getLicensePlate(input);

        // clear input
        editText.setText("");
        setTitle(input);

        if(licensePlate != null)
        {
            currentLicensePlateTag = input;
            drawResult(licensePlate);
        }
        else
        {
            currentLicensePlateTag ="";
            TextView derivedFrom = (TextView)findViewById(R.id.textViewDerivedFrom);
            derivedFrom.setText(R.string.unknown);

            TextView county = (TextView)findViewById(R.id.textViewCounty);
            county.setText(R.string.unknown);

            TextView federalState = (TextView)findViewById(R.id.textViewFederalState);
            federalState.setText(R.string.unknown);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("licensePlateTag", currentLicensePlateTag);
    }

    private void drawResult(LicensePlate licensePlate)
    {
        TextView derivedFrom = (TextView)findViewById(R.id.textViewDerivedFrom);
        derivedFrom.setText(licensePlate.getDerivedFrom());

        TextView county = (TextView)findViewById(R.id.textViewCounty);
        county.setText(licensePlate.getCounty());

        TextView federalState = (TextView)findViewById(R.id.textViewFederalState);
        federalState.setText(licensePlate.getFederalState());
    }
}
