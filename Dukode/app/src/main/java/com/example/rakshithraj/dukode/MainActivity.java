package com.example.rakshithraj.dukode;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;
import android.widget.TextView;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    TextView ProximitySensor, ProximityMax, ProximityReading;
    float max;
    int ctr=0;
    SensorManager mySensorManager;
    Sensor myProximitySensor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProximitySensor = (TextView) findViewById(R.id.proximitySensor);
        ProximityMax = (TextView) findViewById(R.id.proximityMax);
        ProximityReading = (TextView) findViewById(R.id.proximityReading);

        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (myProximitySensor == null) {
            ProximitySensor.setText("No Proximity Sensor!");
        } else {
            max=myProximitySensor.getMaximumRange();
            mySensorManager.registerListener(proximitySensorEventListener, myProximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void off(View view) {

        boolean on = ((ToggleButton) view).isChecked();
        if (!on) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    SensorEventListener proximitySensorEventListener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub


            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if ((event.values[0] == max)&&(ctr!=0)) {
                    {
                        ToneGenerator t = new ToneGenerator(8, 100);
                        t.startTone(3);

                    }

                }
            }
            ctr++;
        }


    };
}


