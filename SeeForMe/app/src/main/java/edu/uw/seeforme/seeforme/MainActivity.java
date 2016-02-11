package edu.uw.seeforme.seeforme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCR(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ColorRecognition.class);
        startActivity(intent);
    }

    public void openOR(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ObjectRecognition.class);
        startActivity(intent);
    }

    public void openTR(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, TextRecognition.class);
        startActivity(intent);
    }
}
