package com.berktavli.widgetsusage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.berktavli.widgetsusage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonRead.setOnClickListener {
           val receivedInfo = binding.editTextInput.text.toString()
            binding.textView.text = receivedInfo

        }

        binding.buttonImage1.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.image1)
        }


        binding.buttonImage2.setOnClickListener {
            //binding.imageView.setImageResource(R.drawable.image2)
            resources.getIdentifier("resim2","drawable",packageName)
        }

        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                    Log.e("widgets","Switch : On")
            }else{
                    Log.e("widgets","Switch : Off")
            }
        }

        binding.buttonGoster.setOnClickListener {
            Log.e("widgets","Switch Durum : ${binding.switch1.isChecked} ")

            val secilenButton = findViewById<Button>(binding.toggleButton.checkedButtonId)
            val buttonYazi = secilenButton.text.toString()
            Log.e("widgets","Toggle Durum : $buttonYazi")
        }

        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            val secilenButton = findViewById<Button>(binding.toggleButton.checkedButtonId)
            val buttonYazi = secilenButton.text.toString()
            Log.e("widgets",buttonYazi)
        }
    }
}