package com.caleblarson.devproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
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

//                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.NetflixScrollView)).commit();
//                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.HuluScrollView)).commit();

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
            for (int i = 0; i < results.getTotal_results(); i++)
            {
                if(results.results[i].isNetflix())
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("title", results.results[i].getTitle());
                    if(results.results[i].getartwork_608x342() != null)
                        bundle.putString("imageURL", results.results[i].getartwork_608x342());
                    else
                        bundle.putString("imageURL", results.results[i].getPoster_400x570());
                    Fragment newFragment = new ResultFragment();
                    newFragment.setArguments(bundle);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.add(R.id.NetflixScrollView, newFragment).commit();
                }
                if(results.results[i].isHulu())
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("title", results.results[i].getTitle());
                    if(results.results[i].getartwork_608x342() != null)
                        bundle.putString("imageURL", results.results[i].getartwork_608x342());
                    else
                        bundle.putString("imageURL", results.results[i].getPoster_400x570());
                    Fragment newFragment = new ResultFragment();
                    newFragment.setArguments(bundle);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.add(R.id.HuluScrollView, newFragment).commit();
                }
            }
        }
    }
}
