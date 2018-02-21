package jmyu.ufl.edu.mytodolist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jmyu on 2/20/18.
 */

public class TodoListViewHolder extends RecyclerView.ViewHolder{

    TextView todoText;

    public TodoListViewHolder(View itemView) {

        super(itemView);
        todoText = (TextView)itemView.findViewById(R.id.main_list_item_text);
    }
}
