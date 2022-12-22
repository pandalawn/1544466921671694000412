package com.bestfake.fakecall.model;



public class MoreList {

    public int id;
    public String image_url;
    public String name;
    public String link_url;

    public MoreList() {

    }
    public int getId() {
        return id;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getLink_url() {
        return link_url;
    }

    public String getName() {
        return name;
    }



    public MoreList(int id, String jdl, String img, String ps) {
        this.id = id;
        this.name = jdl;
        this.image_url = img;
        this.link_url = ps;

    }



}
