package fr.im.salimi.projectmanager.ui.uiUtils

import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat.getColor
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import fr.im.salimi.projectmanager.R
import fr.im.salimi.projectmanager.utils.calculateProgress
import fr.im.salimi.projectmanager.utils.sumASCIIChars

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

fun ProgressBar.progressDone(startingDate: Long, currentDate: Long, endingDate: Long) {
    progress = calculateProgress(startingDate, currentDate, endingDate)
    if (progress >= 90)
        progressTintList = ColorStateList.valueOf(Color.RED)
}

fun createColorsArray(context: Context):MutableList<Int> {
    val colorsList = mutableListOf<Int>()

    colorsList.apply {
        add(getColor(context, R.color.red_500))
        add(getColor(context, R.color.pink_500))
        add(getColor(context, R.color.blue_400))
        add(getColor(context, R.color.teal_500))
        add(getColor(context, R.color.orange_500))
        add(getColor(context, R.color.deep_orange_500))
        add(getColor(context, R.color.blue_gray_500))
        add(getColor(context, R.color.purple_300))
        add(getColor(context, R.color.deep_purple_300))
        add(getColor(context, R.color.indigo_300))
    }
    return colorsList
}

fun TextView.setBackgroundColorText(string: String) {
    val colors = createColorsArray(context)
    val ascii = string.sumASCIIChars()
    val intColor = ascii % colors.size

    backgroundTintList = ColorStateList.valueOf(colors[intColor])
}