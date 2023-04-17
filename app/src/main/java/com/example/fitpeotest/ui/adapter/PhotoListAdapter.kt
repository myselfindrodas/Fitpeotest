package com.example.fitpeotest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpeotest.R
import com.example.fitpeotest.data.model.PhotoResponseModelItem
import com.squareup.picasso.Picasso

class PhotoListAdapter(
    ctx: Context,
    var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<PhotoListAdapter.MyViewHolder>() {
    private val inflater: LayoutInflater
    private var photoListModelArrayList: ArrayList<PhotoResponseModelItem> = arrayListOf()
    var ctx: Context

    init {
        inflater = LayoutInflater.from(ctx)
        this.ctx = ctx
    }

    interface OnItemClickListener {
        fun onClick(position: Int, view: View, id: String, s: String, mPhotoListModelArrayList:ArrayList<PhotoResponseModelItem>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.rv_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Picasso.get()
            .load(photoListModelArrayList[position].thumbnailUrl)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imgThumbnail)


        holder.itemView.rootView.setOnClickListener {
            onItemClickListener.onClick(position, it, photoListModelArrayList[position].title,
                photoListModelArrayList[position].url, photoListModelArrayList)

        }


    }




    fun updateData(mPhotoListModelArrayList: List<PhotoResponseModelItem>){
        photoListModelArrayList= mPhotoListModelArrayList as ArrayList<PhotoResponseModelItem>
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return photoListModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgThumbnail: ImageView
        var btnDetails: CardView

        init {
            imgThumbnail = itemView.findViewById(R.id.imgThumbnail)
            btnDetails = itemView.findViewById(R.id.btnDetails)
        }
    }
}