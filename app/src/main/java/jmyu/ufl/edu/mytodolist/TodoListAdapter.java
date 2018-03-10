package jmyu.ufl.edu.mytodolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.List;

import jmyu.ufl.edu.mytodolist.models.Todo;
import jmyu.ufl.edu.mytodolist.utils.UIUtils;

/**
 * Created by jmyu on 2/19/18.
 */

public class TodoListAdapter extends RecyclerView.Adapter{

    private List<Todo> data;

    public TodoListAdapter(List<Todo> data) {
        this.data = data;
    }

    Context context;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        return new TodoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Todo todo = data.get(position);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TodoEditActivity.class);
            intent.putExtra(TodoEditActivity.KEY_TODO, todo);
            ((MainActivity)context).startActivityForResult(intent, MainActivity.REQ_CODE_TODO_EDIT);
        });

        ((TodoListViewHolder) holder).todoText.setText(todo.text);
        ((TodoListViewHolder) holder).doneCheckbox.setChecked(todo.done);
        UIUtils.setTextViewStrikeThrough(((TodoListViewHolder) holder).todoText, todo.done);

        ((TodoListViewHolder) holder).doneCheckbox.setOnCheckedChangeListener((v, checked) -> {
            ((MainActivity)context).updateTodo(position, checked);
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
