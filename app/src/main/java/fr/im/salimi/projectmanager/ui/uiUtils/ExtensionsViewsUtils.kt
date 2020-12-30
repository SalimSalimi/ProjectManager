package fr.im.salimi.projectmanager.ui.uiUtils

import android.content.DialogInterface
import android.view.View
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.im.salimi.projectmanager.R

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

fun FloatingActionButton.changeFabState(states: FabButtonStates, bottomBar: BottomAppBar) {
    this.apply {
        when (states) {
            FabButtonStates.PRIMARY_STATE -> {
                setImageResource(R.drawable.ic_baseline_add_24)
                bottomBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            }
            FabButtonStates.SECONDARY_STATE -> {
                setImageResource(R.drawable.ic_outline_done_24)
                bottomBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            }
        }
    }
}