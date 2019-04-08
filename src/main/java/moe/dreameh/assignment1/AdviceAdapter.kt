package moe.dreameh.assignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class AdviceAdapter(private val adviceList: MutableList<Advice>) : RecyclerView.Adapter<AdviceAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var author: TextView
        var content: TextView
        var category: TextView

        init {
            author = view.findViewById(R.id.author)
            content = view.findViewById(R.id.content)
            category = view.findViewById(R.id.category)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.advice_list_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val advice = adviceList[position]
        holder.author.text = advice.author
        holder.category.text = advice.category
        holder.content.text = advice.content

    }

    override fun getItemCount(): Int {
        return adviceList.size
    }

}
