package fr.im.salimi.projectmanager.ui.uiUtils

import android.content.DialogInterface
import android.view.View
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker

fun Fragment.chooseDatePicker(selection: Pair<Long, Long>?,
                              positiveButtonClickListener: ((Pair<Long, Long>) -> Unit)?,
                              negativeButtonClickListener: ((View) -> Unit)?,
                              cancelClickListener: ((DialogInterface) -> Unit)?) {
    val datePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setSelection(selection)
            .build()
    datePicker.show(parentFragmentManager, datePicker.toString())

    datePicker.addOnPositiveButtonClickListener {
        positiveButtonClickListener?.invoke(it)
    }

    datePicker.addOnNegativeButtonClickListener {
        negativeButtonClickListener?.invoke(it)
    }

    datePicker.addOnCancelListener {
        cancelClickListener?.invoke(it)
    }
}