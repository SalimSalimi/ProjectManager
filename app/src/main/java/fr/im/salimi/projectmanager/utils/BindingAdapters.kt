package fr.im.salimi.projectmanager.utils

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.google.android.material.textfield.TextInputEditText

object BindingAdapters {

    @BindingAdapter("android:text")
    @JvmStatic
    fun setPhoneNumber(view: TextInputEditText, phoneNumber: Long?) {
        val currentValue = view.text.toString().toLongOrNull()
        if (currentValue != phoneNumber) {
            val value = phoneNumber?: 0
            view.setText("$value")
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    @JvmStatic
    fun getPhoneNumber(view: TextInputEditText): Long =
            view.text.toString().toLongOrNull() ?: 0

}