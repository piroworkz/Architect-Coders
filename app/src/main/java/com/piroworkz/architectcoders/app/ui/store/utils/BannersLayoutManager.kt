package com.piroworkz.architectcoders.app.ui.store.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.concurrent.schedule

class BannersLayoutManager(context: Context, recyclerView: RecyclerView) :
    LinearLayoutManager(context, HORIZONTAL, false) {
    private var reverse = false
    private var timer = Timer()

    private val timerTask: TimerTask = object : TimerTask() {
        override fun run() {
            if (!reverse) {
                recyclerView.forwardScroll { reverse = it }
            } else if (reverse) {
                recyclerView.reverseScroll { reverse = it }
            }
        }
    }

    fun autoScroll(period: Long) =
        try {
            timer.schedule(0, period) {
                timerTask.run()
            }
        } catch (e: Exception) {
            timerTask.cancel()
        }

    private fun RecyclerView.forwardScroll(setReverse: (Boolean) -> Unit) {
        val currentPosition = findFirstVisibleItemPosition()
        val size = itemCount - 1

        val targetPosition: Int = if (currentPosition != size) {
            currentPosition + 1
        } else {
            size
        }

        if (targetPosition in 0..size) {
            smoothScrollToPosition(targetPosition)
        }
        setReverse(currentPosition + 1 == size)
    }

    private fun RecyclerView.reverseScroll(setReverse: (Boolean) -> Unit) {
        val currentPosition = findFirstVisibleItemPosition()

        val targetPosition: Int = if (currentPosition != 0) {
            currentPosition - 1
        } else {
            0
        }
        smoothScrollToPosition(targetPosition)
        setReverse((currentPosition - 1) != 0)
    }
}


