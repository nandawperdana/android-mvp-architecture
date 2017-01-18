package com.nandawperdana.androidmvp.api;

import com.nandawperdana.androidmvp.utils.Enums;

import java.util.List;

/**
 * Created by nandawperdana on 4/22/2016.
 */
public interface APICallListener {
    void onAPICallSucceed(Enums.APIRoute route, RootResponseModel responseModel);

    void onAPICallSucceed(Enums.APIRoute route, List<RootResponseModel> responseModels);

    void onAPICallFailed(Enums.APIRoute route, Throwable throwable);
}
