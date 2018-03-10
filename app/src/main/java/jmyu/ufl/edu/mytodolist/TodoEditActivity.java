package jmyu.ufl.edu.mytodolist;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import jmyu.ufl.edu.mytodolist.models.Todo;
import jmyu.ufl.edu.mytodolist.utils.AlarmUtils;
import jmyu.ufl.edu.mytodolist.utils.DateUtils;
import jmyu.ufl.edu.mytodolist.utils.UIUtils;

public class TodoEditActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public static final String KEY_TODO = "todo";
    public static final String KEY_TODO_ID = "todo_id";
    public static final String KEY_NOTIFICATION_ID = "notification_id";

    private EditText todoEdit;
    private TextView dateTv;
    private TextView timeTv;
    private CheckBox completeCb;

    private Todo todo;
    private Date remindDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todo = getIntent().getParcelableExtra(KEY_TODO);
        remindDate = (todo != null) ? todo.remindDate : null;
        setupUI();

        cancelNotificationIfNeeded();
    }

    private void cancelNotificationIfNeeded() {
        int notificationId = getIntent().getIntExtra(KEY_NOTIFICATION_ID, -1);
        if (notificationId != -1) {
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(notificationId);
        }
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
            completeCb.setChecked(todo.done);

            findViewById(R.id.todo_delete).setOnClickListener(v -> {
                delete();
            });
        }

        if (remindDate != null) {
            dateTv.setText(DateUtils.timeToStringDate(remindDate));
            timeTv.setText(DateUtils.timeToStringTime(remindDate));
        } else {
            dateTv.setText("set_date");
            timeTv.setText("set_time");
        }

        UIUtils.setTextViewStrikeThrough(todoEdit, completeCb.isChecked());
        todoEdit.setTextColor(completeCb.isChecked() ? Color.GRAY : Color.WHITE);

        setCheckBox();
        setTimePicker();
        setSaveButton();
    }

    private void setCheckBox() {
        completeCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UIUtils.setTextViewStrikeThrough(todoEdit, isChecked);
                todoEdit.setTextColor(isChecked ? Color.GRAY : Color.WHITE);
            }
        });
        View completeWrapper = findViewById(R.id.todo_detail_complete_wrapper);
        completeWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeCb.setChecked(!completeCb.isChecked());
            }
        });
    }

    private void setSaveButton() {
        FloatingActionButton fab = findViewById(R.id.todo_finish_edit);
        fab.setOnClickListener(v -> {
            saveAndExit();
        });
    }

    private void saveAndExit() {
        if (todo == null) {
            todo = new Todo(todoEdit.getText().toString(), remindDate);
        } else {
            todo.text = todoEdit.getText().toString();
            todo.remindDate = remindDate;
        }

        todo.done = completeCb.isChecked();

        if (remindDate != null) {
            // fire alarm when saving the todo_item
            Log.i("alarm is set to", remindDate.toString());
            AlarmUtils.setAlarm(this, todo);
        }

        Intent result = new Intent();
        result.putExtra(KEY_TODO, todo);
        setResult(Activity.RESULT_OK, result);
        finish();
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
        Intent result = new Intent();
        result.putExtra(KEY_TODO_ID, todo.id);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = getCalendarFromRemindDate();
        c.set(year, month, dayOfMonth);

        remindDate = c.getTime();
        dateTv.setText(DateUtils.timeToStringDate(remindDate));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = getCalendarFromRemindDate();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);

        remindDate = c.getTime();
        timeTv.setText(DateUtils.timeToStringTime(remindDate));
    }
}
