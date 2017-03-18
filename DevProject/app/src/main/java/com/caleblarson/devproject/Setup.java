package com.caleblarson.devproject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Setup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);


        Button btnSubmit = (Button)findViewById(R.id.btnSearch);
        final EditText txtSearchVal = (EditText)findViewById(R.id.editTxtSearch);
        final RadioButton radioShow = (RadioButton)findViewById(R.id.radioShow);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isShow = false;
                if(radioShow.isChecked())
                    isShow = true;

                BackGroundJob job = new BackGroundJob();
                job.execute(new TaskParams(txtSearchVal.getText().toString(), Setup.this, isShow));

            }
        });
    }


    private static class TaskParams{
        String title;
        Context context;
        boolean isShow;

        public TaskParams(String title, Context context, Boolean isShow) {
            this.title = title;
            this.context = context;
            this.isShow = isShow;
        }
    }


    private class BackGroundJob extends AsyncTask<TaskParams, Void, Results> {

        @Override
        protected Results doInBackground(TaskParams... params) {
            APISearch queryObj = new APISearch(params[0].context);
            return queryObj.query(params[0].title, params[0].isShow);
        }

        @Override
        protected void onPostExecute(Results results) {
            //process results
        }
    }
}
