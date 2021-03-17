package com.example.todoapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.todoapp2.TodoActivity.TODO_EXTRA;

public class DetailActivity extends AppCompatActivity {
    public TextView detailTextView;
    private String[] todoDetails;
    public int todoIndex;
    public static final String IS_TODO_COMPLETE = "com.example.TodoApp2";
    public static final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Resources res= getResources();
        todoDetails = res.getStringArray(R.array.todo_detail);

        Intent intent =getIntent();
        todoIndex = intent.getIntExtra(TODO_EXTRA, 0);

        detailTextView =findViewById(R.id.detail_tv);
        detailTextView.setText(todoDetails[todoIndex]);

    CheckBox checkboxIsComplete = (CheckBox)findViewById(R.id.checkBoxIsComplete);
        checkboxIsComplete.setOnClickListener(mTodoListener);

}

    private void updateTextViewTodoDetail(int todoIndex) {

        final TextView textViewTodoDetail;
        textViewTodoDetail = (TextView) findViewById(R.id.detail_tv);
        textViewTodoDetail.setText(todoDetails[todoIndex]);

    }

    private View.OnClickListener mTodoListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId() ) {
                case R.id.checkBoxIsComplete:
                    CheckBox checkboxIsComplete = (CheckBox)findViewById(R.id.checkBoxIsComplete);
                    setIsComplete(checkboxIsComplete.isChecked());
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    private void setIsComplete(boolean isChecked) {
        if(isChecked){
            Toast.makeText(DetailActivity.this, "Hurray, it's done!", Toast.LENGTH_LONG).show();
            Log.d(TAG, "clicked should display Hurray, it's done!");
        } else {
            Toast.makeText(DetailActivity.this, "There is always tomorrow!", Toast.LENGTH_LONG).show();
            Log.d(TAG, "clicked should There is always tomorrow!");
        }

        Intent intent = new Intent();
        intent.putExtra(IS_TODO_COMPLETE, isChecked);
        setResult(RESULT_OK, intent);
    }
}
