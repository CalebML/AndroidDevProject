package com.caleblarson.devproject;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.URL;

public class ResultFragment extends Fragment {

    public ResultFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle=getArguments();

        String title = bundle.getString("title");
        String imageURL = bundle.getString("imageURL");

        TextView viewTitle = (TextView)getView().findViewById(R.id.txtTitle);
        ImageView viewImage = (ImageView)getView().findViewById(R.id.imgFragment);

        viewTitle.setText(title);
        DownloadImage job = new DownloadImage();
        job.execute(new DownloadParams(imageURL, viewImage));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }


//    public void onButtonPressed(Uri uri) {
//
//    }

    private class DownloadParams
    {
        String imageURL;
        ImageView view;

        public DownloadParams(String imageURL, ImageView view) {
            this.imageURL = imageURL;
            this.view = view;
        }
    }

    private void setImage(Drawable drawable)
    {
        //mImageView.setBackgroundDrawable(drawable);
    }

    public class DownloadImage extends AsyncTask<DownloadParams, Integer, Drawable> {

        ImageView view;

        @Override
        protected Drawable doInBackground(DownloadParams... param) {
            this.view = param[0].view;
            return downloadImage(param[0].imageURL);
        }

        protected void onPostExecute(Drawable image)
        {
            //setImage(image);
            view.setImageDrawable(image);
        }


        /**
         * Actually download the Image from the imageUrl
         */
        private Drawable downloadImage(String imageUrl)
        {
            //Prepare to download image
            URL url;
            BufferedOutputStream out;
            InputStream in;
            BufferedInputStream buf;

            //BufferedInputStream buf;
            try {
                url = new URL(imageUrl);
                in = url.openStream();

                // Read the inputstream
                buf = new BufferedInputStream(in);

                // Convert the BufferedInputStream to a Bitmap
                Bitmap bMap = BitmapFactory.decodeStream(buf);
                if (in != null) {
                    in.close();
                }
                if (buf != null) {
                    buf.close();
                }

                return new BitmapDrawable(bMap);

            } catch (Exception e) {

            }

            return null;
        }

    }

}
