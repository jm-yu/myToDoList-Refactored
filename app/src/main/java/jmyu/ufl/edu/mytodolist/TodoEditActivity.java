package jmyu.ufl.edu.mytodolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import jmyu.ufl.edu.mytodolist.models.Todo;
import jmyu.ufl.edu.mytodolist.utils.DateUtils;

public class TodoEditActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public static final String KEY_TODO = "todo";
    public static final String KEY_TODO_ID = "todo_id";

    private EditText todoEdit;
    private TextView dateTv;
    private TextView timeTv;
    private CheckBox completeCb;

    private Todo todo;
    private Date remindDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();

    }
    private void setupUI(){

        setContentView(R.layout.activity_todo_edit);

        todoEdit = (EditText) findViewById(R.id.todo_edit_content);
        dateTv = (TextView) findViewById(R.id.todo_date);
        timeTv = (TextView) findViewById(R.id.todo_time);
        completeCb = (CheckBox) findViewById(R.id.todo_detail_complete);

        if (todo == null){
            findViewById(R.id.todo_delete).setVisibility(View.GONE);
        } else {
            todoEdit.setText(todo.text);
            //completeCb.setChecked(todo.done);

            findViewById(R.id.todo_delete).setOnClickListener(v -> {
                delete();
            });
        }

        if (remindDate != null) {
            //dateTv.setText(DateUtils.timeToStringDate(remindDate));
            //timeTv.setText(DateUtils.timeToStringTime(remindDate));
        } else {
            dateTv.setText("set_date");
            timeTv.setText("set_time");
        }

        setTimePicker();
    }

    private void setTimePicker() {
        dateTv.setOnClickListener((View v) -> {
            Calendar c = getCalendarFromRemindDate();
            Dialog dialog = new DatePickerDialog(
                    TodoEditActivity.this,
                    TodoEditActivity.this,
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });

        timeTv.setOnClickListener((View v) -> {
            Calendar c = getCalendarFromRemindDate();
            Dialog dialog = new TimePickerDialog(
                    TodoEditActivity.this,
                    TodoEditActivity.this,
                    c.get(Calendar.HOUR_OF_DAY),
                    c.get(Calendar.MINUTE),
                    true);
            dialog.show();
        });
    }


    private Calendar getCalendarFromRemindDate() {
        Calendar c = Calendar.getInstance();
        if (remindDate != null){
            c.setTime(remindDate);
        }
        return c;
    }

    private void delete(){
        Toast.makeText(this, "delete clicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = getCalendarFromRemindDate();
        c.set(year, month, dayOfMonth);

        remindDate = c.getTime();
        //dateTv.setText(DateUtils.timeToStringDate(remindDate));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = getCalendarFromRemindDate();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);

        remindDate = c.getTime();
        //dateTv.setText(DateUtils.timeToStringTime(remindDate));
    }
}
