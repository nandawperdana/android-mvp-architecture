package com.nandawperdana.androidmvp.presentation.ui.screen.main.mvp;

import android.content.Context;

import com.nandawperdana.androidmvp.api.people.People;
import com.nandawperdana.androidmvp.domains.model.PeopleDomain;
import com.nandawperdana.androidmvp.presentation.presenters.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nandawperdana on 4/21/2016.
 */
public class MainModel extends BaseViewModel {
    public PeopleDomain peopleDomain;
    private List<People> listPeople;

    public MainModel(Context context) {
        super(context);
        this.peopleDomain = new PeopleDomain();
        this.listPeople = new ArrayList<>();
    }

    public List<People> getListPeople() {
        return listPeople;
    }

    public void setListPeople(List<People> listPeople) {
        this.listPeople = listPeople;
    }

    public void setListPeople() {
        getListPeople().clear();
        for (People data : peopleDomain.getModel().getPeople()) {
            getListPeople().add(data);
        }
    }
}
