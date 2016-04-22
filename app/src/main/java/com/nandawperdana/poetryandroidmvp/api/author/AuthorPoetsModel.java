package com.nandawperdana.poetryandroidmvp.api.author;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nandawperdana.poetryandroidmvp.api.RootResponseModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nandawperdana on 4/22/2016.
 */
public class AuthorPoetsModel extends RootResponseModel implements Serializable {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("lines")
    @Expose
    private List<String> lines = new ArrayList<String>();
    @SerializedName("linecount")
    @Expose
    private Integer linecount;

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return The lines
     */
    public List<String> getLines() {
        return lines;
    }

    /**
     * @param lines The lines
     */
    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    /**
     * @return The linecount
     */
    public Integer getLinecount() {
        return linecount;
    }

    /**
     * @param linecount The linecount
     */
    public void setLinecount(Integer linecount) {
        this.linecount = linecount;
    }
}
