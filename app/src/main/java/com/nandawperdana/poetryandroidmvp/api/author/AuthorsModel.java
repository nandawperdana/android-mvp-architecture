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
public class AuthorsModel extends RootResponseModel implements Serializable {
    @SerializedName("authors")
    @Expose
    private List<String> authors = new ArrayList<String>();

    /**
     * @return The authors
     */
    public List<String> getAuthors() {
        return authors;
    }

    /**
     * @param authors The authors
     */
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
