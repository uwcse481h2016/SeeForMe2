package edu.uw.seeforme.seeforme;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.nio.ByteBuffer;
import java.util.HashSet;

/**
 * Created by keithyeungk on 2/19/16.
 */
public class ParsePicture extends AsyncTask<Void, Void, Void> {

    Image img;
    Activity act;
    String result;


    public ParsePicture(Activity act, Image img) {
        super();
        this.img = img;
        this.act = act;
    }

    @Override
    protected void onPreExecute() {
        // Runs on UI thread
    }



    @Override
    protected Void doInBackground(Void... params) {

        Log.v("CRLOG", "height: " + img.getHeight());
        Log.v("CRLOG", "width: " + img.getWidth());
        Log.v("CRLOG", "format: " + img.getFormat());



        ByteBuffer buffer = img.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);

        // Runs on the background thread
        Bitmap a = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        long avgred = 0;
        long avgblue = 0;
        long avggreen = 0;

        HashSet<String> colorsFound = new HashSet<String>();

        for (int n = 0; n < 3; n++) {
            for (int m = 0; m < 3; m++) {
                int wid = a.getWidth() / 3;
                int hei = a.getHeight() / 3;


                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        int x = i * 10 + n*wid;
                        int y = j * 10 + m*hei;
                        int pixel = a.getPixel(x, y);

                        avgblue += Color.blue(pixel);
                        avgred += Color.red(pixel);
                        avggreen += Color.green(pixel);
                    }
                }

                avgblue = avgblue / 2500;
                avggreen = avggreen / 2500;
                avgred = avgred / 2500;

                Log.v("CRLOG", "red: " + avgred);
                Log.v("CRLOG", "blue: " + avgblue);
                Log.v("CRLOG", "green: " + avggreen);
                ColorDecoded cd = new ColorDecoded();
                String s = cd.getColorNameFromRgb((int) avgred, (int) avggreen, (int) avgblue);

                colorsFound.add(s);
            }
        }
        String r = "";

        if (colorsFound.size() == 0) {
            r = "No color found";
        } else {
            for (String b : colorsFound) {
                r += b + ", ";
            }
            r = r.substring(0, r.length() - 2);
        }

        result = r;
        return null;
    }

    @Override
    protected void onPostExecute(Void res) {
        // Runs on the UI thread
        final Activity activity = act;
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, result, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}