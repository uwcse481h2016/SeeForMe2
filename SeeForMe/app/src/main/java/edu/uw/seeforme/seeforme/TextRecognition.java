package edu.uw.seeforme.seeforme;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class TextRecognition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_recognition);
        getSupportActionBar().setTitle("Text Recognition");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2GoogleFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
