package com.piroworkz.architectcoders.app.ui.common

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import arrow.core.Either
import com.bumptech.glide.load.HttpException
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestoreException
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.Error.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.IOException

fun String.logMessage(simpleName: String = javaClass.simpleName) {
    Log.d("<-- $simpleName", this)
}

fun <T> LifecycleOwner.collectFlow(
    flow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(lifecycleState) {
            flow
                .catch { "${it.message}".logMessage("LifecycleOwner.collectFlow") }
                .collect(body)
        }
    }
}

fun circularProgressDrawable(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    return circularProgressDrawable
}

fun Fragment.setUpNavBar(toolbar: MaterialToolbar) {
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    NavigationUI.setupWithNavController(toolbar, findNavController())
}

inline fun <T> tryCatch(block: () -> T): Either<Error, T> =
    try {
        Either.Right(block())
    } catch (e: Exception) {
        Either.Left(e.toError())
    }

fun Throwable.toError(): Error = when (this) {
    is IOException -> InputOutput
    is FirebaseAuthException -> Auth
    is HttpException -> Connectivity
    is FirebaseFirestoreException -> Server
    else -> Unknown
}