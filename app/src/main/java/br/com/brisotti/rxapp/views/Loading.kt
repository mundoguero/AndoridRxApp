package br.com.brisotti.rxapp.views

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.fragment.app.DialogFragment
import br.com.brisotti.rxapp.R
import kotlinx.android.synthetic.main.loading_view_dialog.*


class Loading : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogFullscreenTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.loading_view_dialog, container, false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.argb(229, 255, 255, 255)))
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setOnKeyListener { dialog, keyCode, event -> keyCode === KeyEvent.KEYCODE_BACK }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animate()
    }

    private fun animate() {
        val rotate = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 900
        rotate.repeatCount = Animation.INFINITE;
        rotate.interpolator = LinearInterpolator()

        loadingViewLogoImage.startAnimation(rotate)
    }

    override fun onStart() {
        super.onStart()
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
    }

}