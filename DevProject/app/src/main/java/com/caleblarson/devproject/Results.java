package com.caleblarson.devproject;

/**
 * Created by Larso on 3/17/2017.
 */

public class Results {
    private int total_results;
    private int total_returned;
    public Show[] results;

    public Results(int total_results, int total_returned, Show[] results) {
        this.total_results = total_results;
        this.total_returned = total_returned;
        this.results = results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_returned() {
        return total_returned;
    }

    public void setTotal_returned(int total_returned) {
        this.total_returned = total_returned;
    }

    public Show[] getResults() {
        return results;
    }

    public void setResults(Show[] results) {
        this.results = results;
    }
}
