package com.berktavli.widgetsusage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import com.berktavli.widgetsusage.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var control = false
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
        //Expose Drop Down Menu
        val ulkeler = ArrayList<String>()
        ulkeler.add("Türkiye")
        ulkeler.add("İtalya")
        ulkeler.add("İngiltere")
        ulkeler.add("Japonya")
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,ulkeler)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        //Expose Drop Down Menu

        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
           control = isChecked
            //burada sadece secim oldugunda calıs dedigimiz için artık uygulamamız çökmüyor
            if (control){
                val secilenButton = findViewById<Button>(binding.toggleButton.checkedButtonId)
                val buttonYazi = secilenButton.text.toString()
                Log.e("widgets",buttonYazi)
            }
        }
        binding.buttonGoster.setOnClickListener {
            Log.e("widgets","Switch Durum : ${binding.switch1.isChecked} ")
            //burada sadece secim oldugunda calıs dedigimiz için artık uygulamamız çökmüyor
            if (control){
                val secilenButton = findViewById<Button>(binding.toggleButton.checkedButtonId)
                val buttonYazi = secilenButton.text.toString()
                Log.e("widgets","Toggle Durum : $buttonYazi")
            }
            val ulke = binding.autoCompleteTextView.text.toString()
            Log.e("widgets","Seçilen Ülke : $ulke")

            Log.e("widgets","Slider Durum : ${binding.slider.progress}")
        }
        //Progress bar
        binding.buttonBasla.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
        }

        binding.buttonDur.setOnClickListener {
            binding.progressBar.visibility = View.INVISIBLE
        }
        //Progress bar

        //seekBar-SeekBar

        binding.textViewSlider.text = binding.slider.progress.toString()
        binding.slider.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //degişim oldugu zamanda onProggressChanged bize bunları getirecek
                binding.textViewSlider.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //Hareketlendirmeyi başlattıgımızı anlıyoruz
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
               //Dokunmayı bıraktığımızı anlıyoruz
            }
        })
        //seekBar-SeekBar

        //time-picker
        binding.buttonSaat.setOnClickListener {
            val tp = MaterialTimePicker.Builder()
                .setTitleText("Saat Seç")
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            tp.show(supportFragmentManager,"saat")
            tp.addOnPositiveButtonClickListener {
                binding.editTextSaat.setText("${tp.hour} : ${tp.minute}")
            }
        }
        //time-picker

        //date-picker
        binding.buttonTarih.setOnClickListener {
            val dp = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Tarih Seç")
                .build()
            dp.show(supportFragmentManager,"tarih")
            dp.addOnPositiveButtonClickListener {
                val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val tarih = df.format(it)
                binding.editTextTarih.setText(tarih)
                //df = dateFormatter
            }
        }
        //date-picker
        //toast-dialog-snackbar
        //TOAST
        binding.buttonToast.setOnClickListener {
            Toast.makeText(this@MainActivity,"Merhaba",Toast.LENGTH_LONG).show()
        }
        //TOAST
        //SNACKBAR
        binding.buttonSnackbar.setOnClickListener {
           Snackbar.make(it /* binding.buttonSnackbar'da yazabilirdik activity degil butonu göstermemizi istiyor*/,"Silmek İstiyor musunuz ?",Snackbar.LENGTH_LONG)
               .setAction("Evet"){
                   Snackbar.make(it,"Silindi",Snackbar.LENGTH_LONG)
                       .setBackgroundTint(Color.WHITE)
                       .setTextColor(Color.BLUE)
                       .show()

               }
               .setBackgroundTint(Color.WHITE)
               .setTextColor(Color.BLUE)
               .setActionTextColor(Color.RED)
               .show()
        }
        //SNACKBAR
        //TOAST
        binding.buttonDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this@MainActivity)
                .setTitle("Başlık")
                .setMessage("Mesaj")
                .setPositiveButton("Tamam"){ d,i ->
                    //tamam textini i'de tıklanma ile ilgili işlemleri temsil ediyor.
                    Toast.makeText(this@MainActivity,"Tamam Seçildi",Toast.LENGTH_LONG).show()
                }.setNegativeButton("İptal"){d,i ->
                    Toast.makeText(this@MainActivity,"İptal Seçildi",Toast.LENGTH_LONG).show()

                }
                .show()


        }
        //SNACKBAR
    //toast-dialog-snackbar

    }
}