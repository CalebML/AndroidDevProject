package com.caleblarson.devproject;

/**
 * Created by Larso on 3/17/2017.
 */

public class Show {

    private String title;
    private String id;
    private String artwork_608x342;
    private String poster_400x570;
    private boolean hulu;
    private boolean netflix;

    public Show(String title, String id, String artwork_608x342, String poster_400x570) {
        this.title = title;
        this.id = id;
        this.artwork_608x342 = artwork_608x342;
        this.poster_400x570 = poster_400x570;
        netflix = false;
        hulu = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getartwork_608x342() {
        return artwork_608x342;
    }

    public void setartwork_608x342(String artwork_608x342) {
        this.artwork_608x342 = artwork_608x342;
    }

    public boolean isHulu() {
        return hulu;
    }

    public void setHulu(boolean hulu) {
        this.hulu = hulu;
    }

    public boolean isNetflix() {
        return netflix;
    }

    public void setNetflix(boolean netflix) {
        this.netflix = netflix;
    }

    public String getPoster_400x570() {
        return poster_400x570;
    }

    public void setPoster_400x570(String poster_400x570) {
        this.poster_400x570 = poster_400x570;
    }
}