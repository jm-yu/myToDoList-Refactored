package jmyu.ufl.edu.mytodolist;

import jmyu.ufl.edu.mytodolist.models.*;
import jmyu.ufl.edu.mytodolist.utils.*;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener((view) -> {
            Toast.makeText(MainActivity.this, "Fab clicked", Toast.LENGTH_LONG).show();
        });
        setupUI(mockData());
    }
    private void setupUI(@NonNull List<Todo> todos) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_list_container);
        linearLayout.removeAllViews();

        for (Todo todo : todos) {
            View view = getListItemView(todo);
            linearLayout.addView(view);
        }
    }
    private View getListItemView(@NonNull Todo todo) {
        View view = getLayoutInflater().inflate(R.layout.main_list_item, null);
        ((TextView) view.findViewById(R.id.main_list_item_text)).setText(todo.text);
        return view;
    }



    private List<Todo> mockData() {
        List<Todo> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            list.add(new Todo("todo " + i, DateUtils.stringToDate("2015 7 29 0:00")));
        }
        return list;
    }
}
