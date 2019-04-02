package seed_italo.com.br.seeditalo.Service;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;

import java.io.IOException;

import retrofit.Response;

public class ApiHeaders implements Interceptor {

    public String mSessionId;

    public void setSessionId(Response response) {
        String[] session = response.headers().get("Set-Cookie").split("=");
        if (session != null &&
                !session[1].equals(mSessionId)){
            mSessionId = session[1];
//            PreferencesHandler.saveSession(mSessionId);
        }
    }

    public void setSessionId(String sessionId) {
        mSessionId = sessionId;
//        PreferencesHandler.saveSession(mSessionId);
    }

    public void clearSessionId() {
        mSessionId = null;
    }

    @Override
    public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//            mSessionId = PreferencesHandler.getSessionId();
//            request = chain.request().newBuilder()
//                    .addHeader("Authorization", "Token token="+ mSessionId)
//                    .addHeader("Accept-Language", "pt-BR")
//                    .addHeader("Accept", "application/json").build();
        //            mSessionId = PreferencesHandler.getSessionId();
        request = chain.request().newBuilder()
                .addHeader("Accept-Language", "pt-BR")
                .addHeader("Accept", "application/json").build();
        return chain.proceed(request);
    }
}