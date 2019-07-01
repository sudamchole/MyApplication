package com.varseno.myapplication.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.varseno.myapplication.R
import android.widget.TextView
import android.view.LayoutInflater
import com.varseno.myapplication.models.MostView



/**
 * Created by Sudam Chole on 7/1/2019.
 */
class ArticleAdapter(internal var mCtx: Context, internal var articleList: List<MostView.Result>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapterViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.item_list_view, parent, false)
        return ArticleAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleAdapterViewHolder, position: Int) {
        val article = articleList[position]

        holder.txtTitle.setText(article.title)
        holder.txtPublishDate.setText(article.published_date)
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