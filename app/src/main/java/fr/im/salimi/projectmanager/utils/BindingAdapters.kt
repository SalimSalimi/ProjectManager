package fr.im.salimi.projectmanager.utils

import android.util.Log
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.google.android.material.textfield.TextInputEditText
import fr.im.salimi.projectmanager.data.helpers.Post

object BindingAdapters {

    @BindingAdapter("android:text")
    @JvmStatic
    fun fromLongToText(view: TextInputEditText, phoneNumber: Long) {
        val currentValue = view.text.toString().toLongOrNull()
        if (currentValue != phoneNumber) {
            view.setText("$phoneNumber")
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    @JvmStatic
    fun fromTextToLong(view: TextInputEditText): Long =
            view.text.toString().toLongOrNull() ?: 0

    @BindingAdapter("android:text")
    @JvmStatic
    fun fromIntToText(view: TextInputEditText, phoneNumber: Int) {
        val currentValue = view.text.toString().toIntOrNull()
        if (currentValue != phoneNumber) {
            view.setText("$phoneNumber")
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    @JvmStatic
    fun fromTextToInt(view: TextInputEditText): Int =
            view.text.toString().toIntOrNull() ?: 0

    //Post bin
    @BindingAdapter("android:text")
    @JvmStatic
    fun setPost(view: AutoCompleteTextView, post: Post?) {
        val currentValue = view.listSelection
        if (currentValue != post?.ordinal) {
            Log.d("BindingAdapters", "$currentValue ${post?.ordinal}")
            val value = (post ?: Post.NONE).ordinal
            view.setText(view.adapter.getItem(value).toString(), false)
            view.listSelection = value

        }

    }

    @InverseBindingAdapter(attribute = "android:text")
    @JvmStatic
    fun getPost(view: AutoCompleteTextView): Post =
            Post.values().find {
                it.toString() == view.text.toString()
            } ?: Post.NONE
}