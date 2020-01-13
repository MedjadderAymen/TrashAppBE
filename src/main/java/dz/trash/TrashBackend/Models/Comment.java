package dz.trash.TrashBackend.Models;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

public class Comment {
    private int id_comment ; // car un user peut ajouter plsr commentaire
    private String content ;
    private Date creation_date;
     private boolean is_enabled ;
     private int id_challenge;

    public Comment(int id_comment, String content, Date creation_date, boolean is_enabled,Client c,int id_ch) throws ParseException {
        this.id_comment = id_comment;
        this.content = content;
        this.creation_date = creation_date;
        this.is_enabled = is_enabled;
        this.id_challenge=id_ch;
        addClient(c);

    }

    public Comment() throws ParseException {}

    //classe association  ==> one to many unidirectionnelle entre commentaire et client

    private Client owner;
    public void addClient(Client c){
        setOwner(c);
    }
    public Client getOwner(){
        return owner;
    }
    public void setOwner(Client c){
        this.owner = c;
    }


    //**************************************************************************************************


    public int getId_challenge() {
        return id_challenge;
    }

    public void setId_challenge(int id_challenge) {
        this.id_challenge = id_challenge;
    }

    public int getId_comment() {
        return id_comment;
    }

    public void setId_comment(int id_comment) {
        this.id_comment = id_comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public boolean getIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(boolean is_enabled) {
        this.is_enabled = is_enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return getId_comment() == comment.getId_comment();
    }


    @Override
    public int hashCode() {

        return Objects.hash(getId_comment());
    }
}