package com.caleblarson.devproject;

/**
 * Created by Larso on 3/17/2017.
 */

public class Show {

    private String title;
    private String id;
    private String artwork_208x117;
    private boolean hulu;
    private boolean netflix;

    public Show(String title, String id, String artwork_208x117) {
        this.title = title;
        this.id = id;
        this.artwork_208x117 = artwork_208x117;
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

    public String getArtwork_208x117() {
        return artwork_208x117;
    }

    public void setArtwork_208x117(String artwork_208x117) {
        this.artwork_208x117 = artwork_208x117;
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
}