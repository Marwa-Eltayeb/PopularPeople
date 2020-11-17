package com.marwaeltayeb.popularpeople.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.marwaeltayeb.popularpeople.R
import com.marwaeltayeb.popularpeople.model.Acting
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class ActingAdapter : PagedListAdapter<Acting, ActingAdapter.ActingViewHolder>(DIFF_CALL_BACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActingViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.actor_list_item, parent, false)
        return ActingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActingViewHolder, position: Int) {
        val currentActor = getItem(position)

        val imageUrl = "https://image.tmdb.org/t/p/w500/" + currentActor!!.actorImage

        Picasso.get()
            .load(imageUrl)
            .into(holder.actorImage, object : Callback {
                override fun onSuccess() {
                    Log.d("icon", "success")
                }

                override fun onError(e: Exception?) {
                    Log.d("icon", "error")
                }
            })

        holder.actorName.text = currentActor.actorName
    }

    class ActingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var actorImage: ImageView
        var actorName: TextView


        init {
            actorImage = itemView.findViewById(R.id.actorImage)
            actorName = itemView.findViewById(R.id.actorName)
        }
    }
}

private val DIFF_CALL_BACK: DiffUtil.ItemCallback<Acting> =
    object : DiffUtil.ItemCallback<Acting>() {
        override fun areItemsTheSame(oldItem: Acting, newItem: Acting): Boolean {
            return oldItem.actorId == newItem.actorId
        }

        override fun areContentsTheSame(oldItem: Acting, newItem: Acting): Boolean {
            return oldItem.actorId == newItem.actorId
        }
    }

