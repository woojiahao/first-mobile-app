package moe.dreameh.assignment1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.MyViewHolder> {

    private List<Advice> adviceList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView author, content, category;

        public MyViewHolder(View view) {
            super(view);
            author = view.findViewById(R.id.author);
            content = view.findViewById(R.id.content);
            category = view.findViewById(R.id.category);
        }
    }


    public AdviceAdapter(List<Advice> adviceList) {
        this.adviceList = adviceList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advice_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Advice advice = adviceList.get(position);
        holder.author.setText(advice.getAuthor());
        holder.category.setText(advice.getCategory());
        holder.content.setText(advice.getContent());

    }

    @Override
    public int getItemCount() {
        return adviceList.size();
    }

}
