package com.example.latticeinovationpvtltd.UI.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.latticeinovationpvtltd.DATA.models.Article
import com.example.latticeinovationpvtltd.R
import com.example.latticeinovationpvtltd.databinding.ItemLayoutBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ResponseAdapter(val context: Context, private val responseList : List<Article>) : RecyclerView.Adapter<ResponseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseViewHolder {
        val itemLayoutBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ResponseViewHolder(itemLayoutBinding)
    }
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder:ResponseViewHolder, position: Int) {
        val data = responseList[position]
        holder.itemLayoutBinding.apply {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            sdf.timeZone = TimeZone.getTimeZone("GMT")
            try {
                val time: Long = sdf.parse(data.publishedAt).time
                val now = System.currentTimeMillis()
                val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
             //   tvHour.text = ago
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            tvChannel.text = data.source.name
            tvTitle.text = data.title
            tvDescription.text = data.description
            Glide.with(context).load(data.urlToImage).placeholder(R.drawable.ic_image_not_supported).into(ivImage)
        }
    }
    override fun getItemCount(): Int {
        return responseList.size
    }
}
class ResponseViewHolder(val itemLayoutBinding: ItemLayoutBinding) : RecyclerView.ViewHolder(itemLayoutBinding.root)