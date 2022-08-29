package com.piroworkz.architectcoders.app.ui.detail.dialog

import androidx.databinding.BindingAdapter
import com.google.android.material.chip.ChipGroup
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.detail.dialog.DetailDialogViewModel
import com.piroworkz.architectcoders.domain.Size.*

@BindingAdapter("onChecked")
fun ChipGroup.onChecked(viewModel: DetailDialogViewModel?) {
    setOnCheckedStateChangeListener { group, _ ->
        when (checkedChipId) {
            R.id.smallSizeChip -> viewModel?.onSizeChipSelected(SMALL)
            R.id.mediumSizeChip -> viewModel?.onSizeChipSelected(MEDIUM)
            R.id.largeSizeChip -> viewModel?.onSizeChipSelected(LARGE)
        }
        group.clearCheck()
    }
}