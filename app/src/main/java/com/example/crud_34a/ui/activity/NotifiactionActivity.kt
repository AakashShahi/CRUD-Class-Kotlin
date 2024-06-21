package com.example.crud_34a.ui.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Build.VERSION_CODES.O
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_34a.R
import com.example.crud_34a.databinding.ActivityNotifiactionBinding

class NotifiactionActivity : AppCompatActivity() {
    lateinit var notifiactionBinding: ActivityNotifiactionBinding
    var CHANNEL_ID="1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notifiactionBinding=ActivityNotifiactionBinding.inflate(layoutInflater)
        setContentView(notifiactionBinding.root)

        notifiactionBinding.notBtn.setOnClickListener{
            showNotification()
        }
    }

    fun showNotification(){
        var builder= NotificationCompat.Builder(this@NotifiactionActivity,CHANNEL_ID)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            var channel=NotificationChannel(CHANNEL_ID,"My Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            var manager: NotificationManager=getSystemService(NOTIFICATION_SERVICE)
                    as NotificationManager
            manager.createNotificationChannel(channel)
            builder.setSmallIcon(R.drawable.baseline_add_24)
            builder.setContentTitle("My Notification")
            builder.setContentText("This is my notification")
            manager.notify(1,builder.build())
        }
        else{
            builder.setSmallIcon(R.drawable.baseline_add_24)
            builder.setContentTitle("My Notification")
            builder.setContentText("This is my notification")
        }
    }
}