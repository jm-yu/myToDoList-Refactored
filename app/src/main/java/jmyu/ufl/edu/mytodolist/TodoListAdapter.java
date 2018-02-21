package jmyu.ufl.edu.mytodolist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import jmyu.ufl.edu.mytodolist.models.Todo;

/**
 * Created by jmyu on 2/19/18.
 */

public class TodoListAdapter extends RecyclerView.Adapter{

    private List<Todo> data;

    public TodoListAdapter(List<Todo> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        return new TodoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Todo todo = data.get(position);
        ((TodoListViewHolder) holder).todoText.setText(todo.text);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
