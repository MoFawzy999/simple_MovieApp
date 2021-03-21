package com.fawzy.parsingjson;

public class MovieModelClass {

    private String name ;
    private String vote ;
    private String image ;

    public MovieModelClass() {
    }

    public MovieModelClass(String name, String vote, String image) {
        this.name = name;
        this.vote = vote;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
