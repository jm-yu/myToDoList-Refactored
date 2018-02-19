package jmyu.ufl.edu.mytodolist;

import android.content.Context;
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

public class TodoListAdapter extends BaseAdapter{

    private Context context;
    private List<Todo> data;

    public TodoListAdapter(Context context, List<Todo> todos){
        this.context = context;
        this.data = todos;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        View view = LayoutInflater.from(this.context).inflate(R.layout.main_list_item, viewGroup, false);

        Todo todo = data.get(i);

        ((TextView)view.findViewById(R.id.main_list_item_text)).setText(todo.text);

        return view;
    }
}
