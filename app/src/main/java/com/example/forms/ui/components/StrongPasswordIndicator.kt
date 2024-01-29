package com.example.forms.ui.components

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.provider.CalendarContract.Colors
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.forms.R
import com.example.forms.databinding.ResStrongPasswordIndicatorBinding

class StrongPasswordIndicator(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val binding: ResStrongPasswordIndicatorBinding =
        ResStrongPasswordIndicatorBinding.inflate(
            LayoutInflater.from(context), this, true
        )


    fun checkPasswordStrong(password: String) {
        var strongPercentage = 0
        val lowerCaseRegex = "(?=.*[a-z])"
        val upperCaseRegex = "(?=.*[A-Z])"
        val hasNumberRegex = "(?=.*\\d)"
        val hasSpecialCharacter = "(?=.*[@#\$%!^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])"

        if (Regex(lowerCaseRegex).find(password) != null) {
            strongPercentage += 20
        }
        if (Regex(upperCaseRegex).find(password) != null) {
            strongPercentage += 20
        }
        if (Regex(hasNumberRegex).find(password) != null) {
            strongPercentage += 20
        }
        if (Regex(hasSpecialCharacter).find(password) != null) {
            strongPercentage += 20
        }
        if (password.length >= 8) {
            strongPercentage += 20
        }

        indicatorsProgress(strongPercentage)
        binding.strongPasswordProgress.setProgress(strongPercentage, true)
    }

    private fun indicatorsProgress(value: Int) {
        val textIndicator = binding.strongPasswordText
        val drawable = binding.strongPasswordProgress.progressDrawable as LayerDrawable
        val indicator = drawable.getDrawable(1).mutate();
        if (value in 0..40) {
            textIndicator.text = "Weak password"
            indicator.setTint(ContextCompat.getColor(context, R.color.error))
        }

        if (value in 40..80) {
            textIndicator.text = "Medium password"
            indicator.setTint(ContextCompat.getColor(context, R.color.warn))
        }

        if (value in 81..100) {
            textIndicator.text = "Strong password"
            indicator.setTint(ContextCompat.getColor(context, R.color.success))
        }
    }
}