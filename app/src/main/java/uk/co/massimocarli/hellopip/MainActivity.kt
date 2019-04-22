package uk.co.massimocarli.hellopip

import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Rational
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goPip(button: View) = enablePip()


    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration?
    ) {
        super.onPictureInPictureModeChanged(
            isInPictureInPictureMode,
            newConfig
        )
        if (isInPictureInPictureMode) {
            pipButton.visibility = View.INVISIBLE
            supportActionBar?.hide()
        } else {
            pipButton.visibility = View.VISIBLE
            supportActionBar?.show()
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        enablePip()
    }

    private fun enablePip() {
        val aspectRatio = Rational(4, 3)
        val remoteAction = RemoteAction(
            Icon.createWithResource(this, R.drawable.abc_btn_check_to_on_mtrl_000),
            "Act",
            "Act Descr",
            PendingIntent.getActivity(this, 37, Intent(), 0)
        )
        val params = PictureInPictureParams.Builder()
            .setAspectRatio(aspectRatio)
            .setActions(listOf(remoteAction))
            .build()
        enterPictureInPictureMode(params)
    }
}
