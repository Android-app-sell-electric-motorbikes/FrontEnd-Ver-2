package com.example.otodiensale.api;

import android.content.Context;
import com.example.otodiensale.util.SecurePrefs;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request req = chain.request();
        // get application context via singleton (or inject)
        Context ctx = AppContext.get(); // implement AppContext to provide app Context
        String token = SecurePrefs.getInstance(ctx).getToken();
        if (token != null && !token.isEmpty()) {
            Request newReq = req.newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(newReq);
        }
        return chain.proceed(req);
    }
}
