package jmyu.ufl.edu.mytodolist;

import jmyu.ufl.edu.mytodolist.models.*;
import jmyu.ufl.edu.mytodolist.utils.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_TODO_EDIT = 100;
    ArrayList<Todo> todos;
    TodoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, TodoEditActivity.class);
            startActivityForResult(intent, REQ_CODE_TODO_EDIT);
        });

        loadData();
        setupUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_TODO_EDIT && resultCode == RESULT_OK){
            String todoID = data.getStringExtra(TodoEditActivity.KEY_TODO_ID);
            if (todoID == null){
                Todo todo = data.getParcelableExtra(TodoEditActivity.KEY_TODO);
                update(todo);
            } else {
                delete(todoID);
            }
        }
    }

    private void delete(String todoID) {
        for (int i = 0; i < todos.size(); i++){
            Todo item = todos.get(i);
            if (TextUtils.equals(item.id, todoID)){
                todos.remove(i);
                break;
            }
        }
    }

    private void update(Todo todo) {
        boolean found = false;
        for (int i = 0; i < todos.size(); i++){
            Todo item = todos.get(i);
            if (TextUtils.equals(item.id, todo.id)){
                todos.set(i, todo);
                found = true;
                break;
            }
        }
        if (!found){
            todos.add(todo);
        }
        adapter.notifyDataSetChanged();
    }

    private void setupUI() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodoListAdapter(todos);
        recyclerView.setAdapter(adapter);
    }

    private void loadData() {
        todos = new ArrayList<>();
    }
}
