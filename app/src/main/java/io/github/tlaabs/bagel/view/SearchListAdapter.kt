package io.github.tlaabs.bagel.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.tlaabs.bagel.R
import io.github.tlaabs.bagel.api.Repo
import kotlinx.android.synthetic.main.repo_item.view.*
import java.util.zip.Inflater


class SearchListAdapter : RecyclerView.Adapter<SearchListAdapter.SearchViewHolder>() {

    private var items: MutableList<Repo> = mutableListOf()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = items[position]
        if (item != null) {
            holder.loadItem(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_item, parent, false)
        return SearchViewHolder(view)
    }

    fun addItem(newItems: List<Repo>?) {
        if(newItems != null) {
            items.addAll(newItems)
            notifyDataSetChanged()
        }
    }

    inner class SearchViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val repoName : TextView = view.findViewById(R.id.repo_name)
        private val repoDesc : TextView = view.findViewById(R.id.repo_desc)
        private val starCount : TextView = view.findViewById(R.id.start_count)

        fun loadItem(item: Repo) {
            repoName.text = item.name
            repoDesc.text = item.description
            starCount.text = item.stargazers_count.toString()
        }
    }
}