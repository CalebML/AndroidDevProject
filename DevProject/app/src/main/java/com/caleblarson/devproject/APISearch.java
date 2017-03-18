package com.caleblarson.devproject;

/**
 * Created by Larso on 3/14/2017.
 */

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class APISearch
{
    public enum queryType
    {
        SEARCH, SHOW, MOVIE
    }
    Context context;


    APISearch(Context context)
    {
        this.context = context;
        //query("Smallville", queryType.SEARCH, true);
    }

    public Results query(String title, boolean isShow)
    {
        queryType type = queryType.MOVIE;
        if(isShow)
            type = queryType.SHOW;

        String json = queryServer(title, queryType.SEARCH, true);

        Gson g = new Gson();
        Results results = g.fromJson(json, Results.class);

        int numResults = results.getTotal_results();
        if(numResults > 25)
        {
            numResults = results.getTotal_returned();
        }


        for(int i = 0; i < numResults; i++)
        {

            json = queryServer(results.results[i].getId(), type, isShow, context.getString(R.string.hulu));
            Results show = g.fromJson(json, Results.class);
            if(show.getTotal_results() > 0)
            {
                results.results[i].setHulu(true);
            }
            json = queryServer(results.results[i].getId(), type, isShow, context.getString(R.string.netflix));
            show = g.fromJson(json, Results.class);
            if(show.getTotal_results() > 0)
            {
                results.results[i].setNetflix(true);
            }
        }

        return results;
    }

    private String queryServer(String titleOrID, queryType type, boolean isShow)
    {return queryServer(titleOrID, type, isShow, "netflix");}


    private String queryServer(String titleOrID, queryType type, boolean isShow, String source)
    {
        URL url = null;
        try {
            String strType = "";
            switch (type) {
                case SEARCH:
                    strType = "search";
                    break;

                case SHOW:
                    strType = "shows";
                    break;

                default:
                    strType = "movies";
                    break;
            }

            String strURL = context.getString(R.string.api) + strType;

            if(type == queryType.SEARCH)
            {
                strURL = strURL + "?api_key=" + context.getString(R.string.api_key) + "&type=";
                if(isShow)
                    strURL = strURL + "show";
                else
                    strURL = strURL + "movie";
                strURL = strURL + "&field=title&query=" + titleOrID;
            }
            else
            {
                strURL =  strURL + "/" + titleOrID;
                if(isShow)
                    strURL =  strURL + "/episodes";
                strURL =  strURL + "?api_key=" + context.getString(R.string.api_key) + "&sources=" + source;

            }
            //url = new URL("http://api-public.guidebox.com/v2/shows/2310/episodes?api_key=406bfde01b56a945c9aef50299d41091299a976c&season=1&sources=hulu_plus");
            //url = new URL("http://api-public.guidebox.com/v2/search?api_key=406bfde01b56a945c9aef50299d41091299a976c&type=show&field=title&query=" + title);
            url = new URL(strURL);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        }
        conn.setRequestProperty("Accept", "application/json");

        try {
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        String output = "";
        //System.out.println("Output from Server .... \n");
        try {
            output = br.readLine();
            //while ((output = br.readLine()) != null) {
            //System.out.println(output);
            //}
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        conn.disconnect();
        return output;
    }
}