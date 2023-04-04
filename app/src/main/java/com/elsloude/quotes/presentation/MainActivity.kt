package com.elsloude.quotes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elsloude.quotes.R
import com.elsloude.quotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, QuotesFragment())
            .commit()
    }
}