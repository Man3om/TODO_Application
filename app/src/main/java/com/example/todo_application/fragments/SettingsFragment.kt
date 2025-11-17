package com.example.todo_application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import com.example.todo_application.R
import com.example.todo_application.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private val TAG = "SettingsFragment"
    private var languageAdapter: ArrayAdapter<String>? = null
    private var modeAdapter: ArrayAdapter<String>? = null
    lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        languageAdapter = ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.Lang)
        )

        modeAdapter = ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.Mode)
        )

        binding.LanguageHintTv.setAdapter(languageAdapter)
        binding.ModeHintTv.setAdapter(modeAdapter)

        binding.LanguageHintTv.setOnItemClickListener { adapterView, view, i, l ->
            changeAppLanguage(i)
        }
        binding.ModeHintTv.setOnItemClickListener { adapterView, view, i, l ->
            changeAppMode(i)
        }

    }

    private fun changeAppLanguage(id: Int) {
        when (id) {
            0 -> {
                //English
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"))
            }

            1 -> {
                //Arabic
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("ar"))
            }
        }
        binding.LanguageHintTv.setText(binding.LanguageHintTv.adapter.getItem(id).toString())

    }

    private fun changeAppMode(i: Int) {
        when (i) {
            0 -> {
                //Light
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            1 -> {
                //Night
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        binding.ModeHintTv.setText(binding.ModeHintTv.adapter.getItem(i).toString())
    }
}

