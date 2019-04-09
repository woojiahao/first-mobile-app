package moe.dreameh.assignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class AdviceAdapter(private val adviceList: MutableList<Advice>) : RecyclerView.Adapter<AdviceAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var author: TextView = view.findViewById(R.id.author)
        var content: TextView = view.findViewById(R.id.content)
        var category: TextView = view.findViewById(R.id.category)
    }

    // Essentially set the XML Layout advice_list_row as the layout for a "row"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.advice_list_row, parent, false)

        return MyViewHolder(itemView)
    }

    // Set ViewHolder variables to the list's item variables
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val advice = adviceList[position]
        holder.author.text = advice.author
        holder.category.text = advice.category
        holder.content.text = advice.content

    }

    //Get size of advicelist
    override fun getItemCount(): Int {
        return adviceList.size
    }

}
