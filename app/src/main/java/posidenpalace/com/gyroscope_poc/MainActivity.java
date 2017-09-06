package posidenpalace.com.gyroscope_poc;

import android.annotation.TargetApi;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "test";
    private SensorManager sensors;
    private SensorEventListener eventListener;
    private Sensor gravitySensor;
    private Sensor stepDetectorSensor;
    private Sensor stepCounterSensor;
    private Sensor acelrometerSensor;
    private Sensor rotationSensor;
    private Sensor gyroscopeSensor;
    private Sensor thermometer;

    @BindView(R.id.tvTemp)
    TextView temp;
    @BindView(R.id.tvSteps)
    TextView step;
    @BindView(R.id.tvGravityX)
    TextView gravX;
    @BindView(R.id.tvGravityY)
    TextView gravY;
    @BindView(R.id.tvGravityZ)
    TextView gravZ;
    @BindView(R.id.tvAccelX)
    TextView accelX;
    @BindView(R.id.tvAccelY)
    TextView accelY;
    @BindView(R.id.tvAccelZ)
    TextView accelZ;
    @BindView(R.id.tvRotationX)
    TextView rotX;
    @BindView(R.id.tvRotationY)
    TextView rotY;
    @BindView(R.id.tvRotationZ)
    TextView rotZ;
    @BindView(R.id.tvGyroX)
    TextView gyroX;
    @BindView(R.id.tvGyroY)
    TextView gyroY;
    @BindView(R.id.tvGyroZ)
    TextView gyroZ;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sensors = (SensorManager) getSystemService(SENSOR_SERVICE);
        gravitySensor = sensors.getDefaultSensor(Sensor.TYPE_GRAVITY,true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            stepDetectorSensor = sensors.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            stepCounterSensor = sensors.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }
        thermometer = sensors.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        acelrometerSensor = sensors.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        rotationSensor = sensors.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        gyroscopeSensor = sensors.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        eventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                Log.d(TAG, "onSensorChanged: " + sensorEvent.sensor.getName());
                switch ( sensorEvent.sensor.getName()){
                    case "Rotation Vector":
                        rotX.setText("X: "+sensorEvent.values[0]);
                        rotY.setText("Y: "+sensorEvent.values[1]);
                        rotZ.setText("Z: "+sensorEvent.values[2]);
                        break;

                    case "ICM20610 Acceleration Sensor":
                        accelX.setText("X: "+sensorEvent.values[0]);
                        accelY.setText("Y: "+sensorEvent.values[1]);
                        accelZ.setText("Z: "+sensorEvent.values[2]);
                        break;


                    case "ICM20610 Gyroscope Sensor":
                        break;

                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensors.registerListener(eventListener, thermometer,SensorManager.SENSOR_DELAY_UI);
        sensors.registerListener(eventListener, gravitySensor,SensorManager.SENSOR_DELAY_UI);
        sensors.registerListener(eventListener, gyroscopeSensor,SensorManager.SENSOR_DELAY_UI);
        sensors.registerListener(eventListener, stepCounterSensor,SensorManager.SENSOR_DELAY_UI);
        sensors.registerListener(eventListener, rotationSensor,SensorManager.SENSOR_DELAY_UI);
        sensors.registerListener(eventListener, acelrometerSensor,SensorManager.SENSOR_DELAY_UI);
        sensors.registerListener(eventListener, stepDetectorSensor,SensorManager.SENSOR_DELAY_UI);



    }



}
