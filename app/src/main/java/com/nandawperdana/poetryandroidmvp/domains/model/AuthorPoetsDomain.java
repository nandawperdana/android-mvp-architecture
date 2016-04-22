package com.nandawperdana.poetryandroidmvp.domains.model;

import com.nandawperdana.poetryandroidmvp.api.author.AuthorPoetsModel;

import java.util.List;

/**
 * Created by nandawperdana on 4/22/2016.
 */
public class AuthorPoetsDomain {
    private AuthorPoetsModel model;
    private List<AuthorPoetsModel> models;

    public AuthorPoetsModel getModel() {
        return model;
    }

    public void setModel(AuthorPoetsModel model) {
        this.model = model;
    }

    public List<AuthorPoetsModel> getModels() {
        return models;
    }

    public void setModels(List<AuthorPoetsModel> models) {
        this.models = models;
    }
}
