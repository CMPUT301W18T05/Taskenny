package com.cmput301w18t05.taskzilla;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cmput301w18t05.taskzilla.request.RequestManager;
import com.cmput301w18t05.taskzilla.request.command.AddTaskRequest;
import com.cmput301w18t05.taskzilla.request.command.UpdateTaskRequest;

public class EditTaskActivity extends AppCompatActivity {
    private Task task;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        task = new Task();
        ctx = getApplicationContext();
        super.onCreate(savedInstanceState);
        setTitle("Edit Task");
        setContentView(R.layout.activity_edit_task);
        EditText TaskNameText = (EditText) findViewById(R.id.TaskName);
        EditText DescriptionText = (EditText) findViewById(R.id.Description);
        task.setName("testTask"); //Dummy
        task.setDescription("testDescription"); //Dummy
        TaskNameText.setText(task.getName());
        DescriptionText.setText(task.getDescription());

    }

    public void TaskCancelButton(View view){
        finish();
    }

    public void TaskSaveButton(View view){
        EditText TaskNameText = (EditText) findViewById(R.id.TaskName);
        EditText DescriptionText = (EditText) findViewById(R.id.Description);
        String TaskName = TaskNameText.getText().toString();
        String Description = DescriptionText.getText().toString();

        if(TaskName.length() >25){
            TaskNameText.requestFocus();
            TaskNameText.setError("Title length exceeds 25 characters");
        }
        else if(TaskName.length() == 0){
            TaskNameText.requestFocus();
            TaskNameText.setError("Title required");
        }
        else if(Description.length()>280){
            DescriptionText.requestFocus();
            DescriptionText.setError("Description length exceeds 280 characters");
        }



         else {
            task = new Task();
            task.setId("AWIwRF0u42PX8bQQT0sL");
            task.setName(TaskName);
            task.setDescription(Description);
            UpdateTaskRequest request = new UpdateTaskRequest(task);
            RequestManager.getInstance().invokeRequest(ctx, request);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("Task Name", TaskName);
            returnIntent.putExtra("Description", Description);
            setResult(RESULT_OK,returnIntent);
            finish();
        }
    }
}
