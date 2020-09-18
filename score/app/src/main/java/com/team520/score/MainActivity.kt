package com.team520.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.team520.score.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        myViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MyViewModel::class.java)
        binding.data = myViewModel
        binding.lifecycleOwner = this

    }
}
