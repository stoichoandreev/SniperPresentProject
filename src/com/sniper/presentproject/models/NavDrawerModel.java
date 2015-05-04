package com.sniper.presentproject.models;

public class NavDrawerModel {
    private String title;
    private int icon;
    private boolean isSelected = false;
    public NavDrawerModel(){
    }
    public NavDrawerModel(String title, int icon){
        this.icon = icon;
        this.title = title;
    }
    public NavDrawerModel(String title){
        this.icon = icon;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isSelected() { return isSelected;  }

    public void setSelected(boolean isSelected) { this.isSelected = isSelected; }
}
