package cn.example.androidproject.recyclertalk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.example.androidproject.R
import cn.example.androidproject.databinding.MsgLeftBinding
import cn.example.androidproject.databinding.MsgRightBinding
import cn.example.androidproject.databinding.TalkActivityBinding

class TalkActivity : AppCompatActivity() {

    inner class TalkAdapter(private val msgList: List<Msg>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        inner class LeftViewHolder(mBinding: MsgLeftBinding) :
            RecyclerView.ViewHolder(mBinding.root) {
            val leftMsg: TextView = mBinding.leftMsg
        }

        inner class RightViewHolder(mBinding: MsgRightBinding) :
            RecyclerView.ViewHolder(mBinding.root) {
            val rightMsg: TextView = mBinding.rightMsg
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            if (viewType == Msg.TYPE_RECEIVED) {
                val mBinding = MsgLeftBinding.bind(LayoutInflater.from(parent.context)
                    .inflate(R.layout.msg_left, parent, false))
                LeftViewHolder(mBinding)
            } else {
                val mBinding = MsgRightBinding.inflate(LayoutInflater.from(parent.context))
                RightViewHolder(mBinding)
            }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val msg = msgList[position]
            when (holder) {
                is LeftViewHolder -> holder.leftMsg.text = msg.content
                is RightViewHolder -> holder.rightMsg.text = msg.content
            }
        }

        override fun getItemViewType(position: Int): Int {
            val msg = msgList[position]
            return msg.type
        }

        override fun getItemCount(): Int = msgList.size

    }

    private val mBinding by lazy { TalkActivityBinding.inflate(layoutInflater) }
    private var msgList = ArrayList<Msg>()
    private val adapter by lazy { TalkAdapter(msgList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initMsg()
        initComponent()
    }

    private fun initMsg() {
        msgList.add(Msg("Hello,Who are you?", Msg.TYPE_RECEIVED))
        msgList.add(Msg("I am a developer", Msg.TYPE_SEND))
        msgList.add(Msg("what type of the developer", Msg.TYPE_RECEIVED))
    }

    private fun initComponent() {
        mBinding.apply {
            val layoutManager = GridLayoutManager(this@TalkActivity, 1)
            recyclerViewTalk.layoutManager = layoutManager
            recyclerViewTalk.adapter = adapter
            send.setOnClickListener {
                val content = inputText.text.toString()
                if (content.isNotEmpty()) {
                    val msg = Msg(content, Msg.TYPE_SEND)
                    msgList.add(msg)
                    adapter.notifyItemInserted(msgList.size - 1)
                    recyclerViewTalk.scrollToPosition(msgList.size - 1)
                    inputText.setText("")
                }
            }
        }
    }
}