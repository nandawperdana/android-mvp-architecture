package com.nandawperdana.androidmvp.api;

import com.nandawperdana.androidmvp.utils.Enums;

import java.util.List;

/**
 * Created by nandawperdana on 4/22/2016.
 */
public interface APICallListener {
    void onAPICallSucceed(Enums.APIRoute route, BaseResponse responseModel);

    void onAPICallSucceed(Enums.APIRoute route, List<BaseResponse> responseModels);

    void onAPICallFailed(Enums.APIRoute route, Throwable throwable);
}
