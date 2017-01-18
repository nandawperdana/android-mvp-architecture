package com.nandawperdana.androidmvp.presentation.ui.screen.main.mvp;

import com.nandawperdana.androidmvp.api.contact.ContactsModel;
import com.nandawperdana.androidmvp.domains.model.ContactsDomain;
import com.nandawperdana.androidmvp.utils.Enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nandawperdana on 4/21/2016.
 */
public class MainModel implements Serializable {
    public ContactsDomain contactsDomain;
    private List<ContactsModel.Contact> listContact;

    private String authorName;

    private Enums.ErrorState errorState;

    public MainModel() {
        this.contactsDomain = new ContactsDomain();
        this.listContact = new ArrayList<>();
    }

    public List<ContactsModel.Contact> getListContact() {
        return listContact;
    }

    public void setListContact(List<ContactsModel.Contact> listContact) {
        this.listContact = listContact;
    }

    public void setListContact() {
        getListContact().clear();
        for (ContactsModel.Contact data : contactsDomain.getModel().getContacts()) {
            getListContact().add(data);
        }
    }

    public Enums.ErrorState getErrorState() {
        return errorState;
    }

    public void setErrorState(Enums.ErrorState errorState) {
        this.errorState = errorState;
    }
}
