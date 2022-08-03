package com.example.cartaodevisitas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.widget.Toast
import androidx.activity.viewModels
import com.example.cartaodevisitas.App
import com.example.cartaodevisitas.R
import com.example.cartaodevisitas.data.BusinessCard
import com.example.cartaodevisitas.databinding.ActivityAddCard2Binding
import com.example.cartaodevisitas.databinding.ItemCardBinding.inflate

class AddCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddCard2Binding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoDiferente = binding.tilCor.editText?.text.toString()
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}
