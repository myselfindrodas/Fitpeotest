package com.example.fitpeotest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fitpeotest.R
import com.example.fitpeotest.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso

class Details : AppCompatActivity() {

    var title = ""
    var url = ""
    lateinit var binding: ActivityDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        val intent = intent
        title = intent.getStringExtra("title").toString()
        url = intent.getStringExtra("url").toString()

        with(binding) {
            tvTitle.text = title

            Picasso.get()
                .load(url)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(ImgDetails)


            ivBack.setOnClickListener {

                onBackPressedDispatcher.onBackPressed()
            }

        }

    }
}