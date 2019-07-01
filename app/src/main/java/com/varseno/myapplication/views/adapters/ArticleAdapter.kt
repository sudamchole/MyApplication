package com.varseno.myapplication.views.adapters

import android.content.ClipData
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.varseno.myapplication.R
import kotlinx.android.synthetic.main.item_list_view.view.*
import android.widget.TextView
import android.support.annotation.NonNull
import android.view.LayoutInflater
import com.varseno.myapplication.models.MostViewed


/**
 * Created by Sudam Chole on 7/1/2019.
 */
class ArticleAdapter(internal var mCtx: Context, internal var articleList: List<MostViewed>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapterViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.item_list_view, parent, false)
        return ArticleAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleAdapterViewHolder, position: Int) {
        val article = articleList[position]

        holder.txtTitle.setText(article.name)
        holder.txtPublishDate.setText(article.publisher)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    inner class ArticleAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         var txtTitle:TextView
         var txtPublishDate:TextView
        init {
            txtTitle=itemView.findViewById(R.id.textView_tittle)
            txtPublishDate=itemView.findViewById(R.id.textViewDatePublished)

        }
    }
}