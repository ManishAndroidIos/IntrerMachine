package com.app.itauditor.retrofit;

import retrofit2.Response;

/**
 * Created by ak-001 on 18/3/17.
 */

public interface RequestNotifier {
    void notifySuccess(Response<?> response, int code);
    void notifyError(Throwable parseerror);
    void notifyString(String notifstring);
}
