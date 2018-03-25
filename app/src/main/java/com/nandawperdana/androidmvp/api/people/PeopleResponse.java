package com.nandawperdana.androidmvp.api.people;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nandawperdana.androidmvp.api.BaseResponse;

import java.util.List;

/**
 * Created by nandawperdana on 1/18/2017.
 */

public class PeopleResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<People> people = null;

    public List<People> getPeople() {
        return people;
    }

    public void setPeople(List<People> people) {
        this.people = people;
    }

}
