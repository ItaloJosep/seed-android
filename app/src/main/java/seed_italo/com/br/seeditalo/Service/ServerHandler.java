package seed_italo.com.br.seeditalo.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import seed_italo.com.br.seeditalo.BuildConfig;
import seed_italo.com.br.seeditalo.Uteis.Constants;

public class ServerHandler {

    public static ServicesRouter nstaApiInstance = null;
    private static ObjectMapper mapper;

    public static ServicesRouter getApiInstance() {
        if (nstaApiInstance == null) {
            OkHttpClient client = new OkHttpClient();
            client.networkInterceptors().add(HeaderHandler.getApiHeaderInstance());
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.interceptors().add(interceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.DEBUG ? Constants.URL_DEV : Constants.URL_PROD).client(client)
                    .addConverterFactory(JacksonConverterFactory.create(getJsonConverter()))
                    .build();
            nstaApiInstance = retrofit.create(ServicesRouter.class);
        }
        return nstaApiInstance;
    }


    public static ObjectMapper getJsonConverter() {
        if (mapper == null) {
            mapper = new ObjectMapper()
                    .registerModule(new JodaModule())
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        }
        return mapper;
    }
}
