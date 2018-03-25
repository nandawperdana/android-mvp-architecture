package com.nandawperdana.androidmvp.domains.model;

import com.nandawperdana.androidmvp.api.people.PeopleResponse;

/**
 * Created by nandawperdana on 1/18/2017.
 */

public class PeopleDomain {
    private PeopleResponse model;

    public PeopleResponse getModel() {
        return model;
    }

    public void setModel(PeopleResponse model) {
        this.model = model;
    }
}
