package fr.im.salimi.projectmanager.ui.uiUtils

import android.content.DialogInterface
import android.view.View
import androidx.annotation.StringRes
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
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

fun FloatingActionButton.changeFabState(state: FabButtonStates, bottomBar: BottomAppBar) {
    this.apply {
        when (state) {
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

fun Fragment.setFabBtnBehaviour(state: FabButtonStates, clickListener: ((View) -> Unit?)?) {
    val fabBtn: FloatingActionButton = this.requireActivity().findViewById(R.id.fab_main)
    val bottomBar: BottomAppBar = this.requireActivity().findViewById(R.id.bottom_app_bar)

    fabBtn.changeFabState(state, bottomBar)
    fabBtn.setOnClickListener {
        clickListener?.invoke(it)
    }
}

fun Fragment.createSnackbar(anchorView: View? = requireActivity().findViewById(R.id.fab_main), @StringRes text: Int) :Snackbar =
        Snackbar.make(requireView(), getString(text), Snackbar.LENGTH_LONG)
            .setAnchorView(anchorView)