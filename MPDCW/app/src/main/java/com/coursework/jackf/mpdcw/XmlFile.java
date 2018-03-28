package com.coursework.jackf.mpdcw;



import android.widget.ArrayAdapter;

/**
 *
 * Name- Jack Fagan
 * Matriculation Number- S1423745
 *
 *
 *
 */

public class XmlFile {
    private String title;
    private String description;
    private String link;
    private String georssPoint;
    private String author;
    private String comment;
    private String pubDate;

    public XmlFile()
    {
        title = "";
        description = "";
        link = "";
        georssPoint = "";
        author = "";
        comment = "";
        pubDate = "";
    }

    public XmlFile(String atitle, String adescription, String alink, String ageorssPoint, String aauthor, String acomment, String apubDate)
    {
        title = atitle;
        description = adescription;
        link = alink;
        georssPoint = ageorssPoint;
        author = aauthor;
        comment = acomment;
        pubDate = apubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGeorssPoint() {
        return georssPoint;
    }

    public void setGeorssPoint(String geoPoint) {
        this.georssPoint = geoPoint;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString()
    {
        String temp;
        temp = title  + "\n" +
                description + " \n" +
                link + " \n" +
                georssPoint + "\n" +
                author + " \n" +
                comment + " \n" +
                pubDate+ " \n" + " \n" + " \n" +
                "---------------------------------------------------------"+ " \n";
        return temp;
    }


} // End of class
