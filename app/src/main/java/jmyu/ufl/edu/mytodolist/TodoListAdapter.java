package jmyu.ufl.edu.mytodolist;

import android.content.Context;
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
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.main_list_item, viewGroup, false);

            vh = new ViewHolder();
            vh.todoText = (TextView) convertView.findViewById(R.id.main_list_item_text);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder)convertView.getTag();
        }

        Todo todo = data.get(i);
        vh.todoText.setText((todo.text));

        return convertView;
    }
    private static class ViewHolder{
        TextView todoText;
    }
}
