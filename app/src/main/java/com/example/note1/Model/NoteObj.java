package com.example.note1.Model;

public class NoteObj {
    private int id;
    private String title;
    private String detail;
    private String dateTime;
    private boolean favorite;

    public NoteObj() {

    }

    public NoteObj(String title, String detail, String dateTime, boolean favorite) {
        this.title = title;
        this.detail = detail;
        this.dateTime = dateTime;
        this.favorite = favorite;
    }

    public NoteObj(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public NoteObj(String title, String detail, String dateTime) {
        this.title = title;
        this.detail = detail;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isFavorite() {
        return favorite;
    }
}