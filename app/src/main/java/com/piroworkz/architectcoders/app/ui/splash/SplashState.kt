package com.piroworkz.architectcoders.app.ui.splash

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.common.logMessage

class SplashState(
    private val navController: NavController,
) {
    val listener: (String?) -> MotionLayout.TransitionListener =
        { state: String? ->
            object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(ml: MotionLayout?, sId: Int, eId: Int) = Unit

                override fun onTransitionChange(ml: MotionLayout?, sId: Int, eId: Int, p: Float) =
                    Unit

                override fun onTransitionTrigger(
                    ml: MotionLayout?, tri: Int, pos: Boolean, pro: Float
                ) = Unit

                override fun onTransitionCompleted(ml: MotionLayout?, id: Int) = startApp(state)
            }
        }

    fun startApp(user: String?) {
        if (user != null) {
            navController.apply {
                popBackStack(R.id.splashAnimationFragment, true)
                navigate(R.id.mainStoreFragment)
            }
        } else {
            navController.apply {
                popBackStack(R.id.splashAnimationFragment, true)
                navigate(R.id.loginFragment)
            }
        }
    }
}

fun SplashAnimationFragment.buildState(
    navController: NavController = findNavController(),
) = SplashState(navController)