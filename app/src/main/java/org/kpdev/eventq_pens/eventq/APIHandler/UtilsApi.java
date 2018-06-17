package org.kpdev.eventq_pens.eventq.APIHandler;

public class UtilsApi {
    public static final String BASE_URL_API = "http://192.168.43.20:8000/api/v1/rest-auth/";

    public static BaseApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
