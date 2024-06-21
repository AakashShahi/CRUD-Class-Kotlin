package com.example.crud_34a.ui.activity

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_34a.R
import com.example.crud_34a.databinding.ActivityLightSensorBinding

class LightSensorActivity : AppCompatActivity(), SensorEventListener {
    lateinit var lightBinding: ActivityLightSensorBinding
    lateinit var sensor: Sensor
    lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lightBinding=ActivityLightSensorBinding.inflate(layoutInflater)
        setContentView(lightBinding.root)
        sensorManager=getSystemService(SENSOR_SERVICE) as SensorManager

        if (!checkSensor()){
            Toast.makeText(this,"Sensor not found",Toast.LENGTH_SHORT).show()
            return
        }
        else{
            Toast.makeText(this,"Sensor found",Toast.LENGTH_SHORT).show()
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)!!
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values=event!!.values[0]
       if(values<30){
           lightBinding.textView.text="Dark"
           lightBinding.imageView.setImageResource(R.drawable.onoff)
       }
       else{
           lightBinding.textView.text="Light"
           lightBinding.imageView.setImageResource(R.drawable.onoff2)
       }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

     private fun checkSensor():Boolean {
        var sensor=true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)==null){
            sensor=false
            return sensor
        }
        else{
            return sensor
        }
    }

    override fun onPause(){
            super.onPause()
            sensorManager.unregisterListener(this)
    }

    override fun onResume(){
        super.onResume()
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }
}