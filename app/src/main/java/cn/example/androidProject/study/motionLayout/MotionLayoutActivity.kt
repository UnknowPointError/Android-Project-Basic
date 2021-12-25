package cn.example.androidProject.study.motionLayout

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.motion.widget.MotionLayout
import cn.example.androidProject.R
import cn.example.androidProject.databinding.StudyMotionlayoutActivityBinding

class MotionLayoutActivity : AppCompatActivity() {


    private val mBinding by lazy { StudyMotionlayoutActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.motionLayout.setTransitionDuration(3000)
        mBinding.motionLayout.transitionToState(R.id.end)

        mBinding.motionLayout.setTransitionListener(
            object : MotionLayout.TransitionListener {

                override fun onTransitionTrigger(
                    motionLayout: MotionLayout?,
                    triggerId: Int, positive: Boolean, progress: Float
                ) {
                    // Called when a trigger keyframe threshold is crossed
                }

                override fun onTransitionStarted(
                    motionLayout: MotionLayout?,
                    startId: Int, endId: Int
                ) {
                    // Called when the transition starts
                }

                override fun onTransitionChange(
                    motionLayout: MotionLayout?,
                    startId: Int, endId: Int, progress: Float
                ) {
                    // Called each time a property changes. Track progress value to find
                    // current position
                }

                override fun onTransitionCompleted(
                    motionLayout: MotionLayout?,
                    currentId: Int
                ) {
                    // Called when the transition is complete
                }
            }
        )
    }
}