package com.dialogy.studio.shoplistv2.home.checklist.new_check_list

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.dialogy.studio.shoplistv2.databinding.NewCheckListActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewCheckListActivity : AppCompatActivity() {
    private lateinit var binding: NewCheckListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewCheckListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}