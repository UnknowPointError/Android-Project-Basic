package cn.example.androidProject.study.xuanZhuan

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.motion.widget.MotionLayout
import cn.example.androidProject.R
import cn.example.androidProject.databinding.StudyXuanzhuanActivityBinding

class XuanZhuanActivity : AppCompatActivity() {

    class MyTextView @JvmOverloads constructor(
        context: Context,
        set: AttributeSet? = null,
        style: Int = 0
    ) : AppCompatTextView(context, set, style) {

        // 文字变成加号
        fun changeTextToAdd() {
            this.text = "+"
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50.0F)
        }

        // 文字变成M
        fun changeToM() {
            this.text = "M"
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50.0F)
        }

    }

    private val mBinding by lazy { StudyXuanzhuanActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBinding.apply {

            // 进入页面开始动画
            motionLayout.post {
                motionLayout.setTransition(R.id.start, R.id.end)
                motionLayout.transitionToEnd()
            }

            motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                    p0?.isInteractionEnabled = false
                }

                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                }

                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    p0?.isInteractionEnabled = true
                    // 跳转到第一帧
                    p0?.progress = 0.0F
                    // 开始动画
                    p0?.transitionToEnd()
                }

                override fun onTransitionTrigger(
                    p0: MotionLayout?,
                    p1: Int,
                    p2: Boolean,
                    p3: Float
                ) {
                }
            })
        }


    }
}