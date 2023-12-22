
package com.plantherbs.app.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.plantherbs.app.databinding.ActivityDetailHerbsBinding

class DetailHerbsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailHerbsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHerbsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
