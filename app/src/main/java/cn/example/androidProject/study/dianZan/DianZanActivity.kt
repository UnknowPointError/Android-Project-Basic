package cn.example.androidProject.study.dianZan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import cn.example.androidProject.R
import cn.example.androidProject.databinding.StudyDianzanActivityBinding

class DianZanActivity : AppCompatActivity() {

    private val mBinding by lazy { StudyDianzanActivityBinding.inflate(layoutInflater) }
    private var praised = false
    private var isAnimation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {

            // 1. 点击触发动画
            imageButton1.setOnClickListener {
                if (isAnimation) return@setOnClickListener
                isAnimation = true
                if (praised) {
                    // 2. 设置动画的Transition然后开始动画
                    motionLayout.setTransition(R.id.revert)
                    motionLayout.transitionToEnd()
                } else {
                    motionLayout.setTransition(R.id.forward)
                    motionLayout.transitionToEnd()
                }
            }

            // 3. 监听动画的完成后记录状态值
            motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                }

                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                }

                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    praised = !praised
                    isAnimation = false
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