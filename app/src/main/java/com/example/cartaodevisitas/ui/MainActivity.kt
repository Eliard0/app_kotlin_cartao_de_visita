package com.example.cartaodevisitas.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cartaodevisitas.App
import com.example.cartaodevisitas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    private val mainViewModel : MainViewModel by viewModels{
        MainViewModelFactory((application as App).repository)
    }

    private  val adapter by lazy {BusinessCardAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCards.adapter = adapter
        getAllBusinessCard()
        insertListener()
    }

     fun insertListener() {
        binding.btnFlutuante.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getAllBusinessCard(){
        mainViewModel.getAll().observe(this,{ businessCard ->
            adapter.submitList(businessCard)
        })
    }
}