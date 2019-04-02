package seed_italo.com.br.seeditalo.Service;

import android.util.Log;
import com.squareup.okhttp.ResponseBody;
import java.lang.annotation.Annotation;
import retrofit.Callback;
import retrofit.Converter;
import retrofit.Response;
import retrofit.Retrofit;

public abstract class BaseCallback<T> implements Callback<T> {
    protected String errorMessage;

    @Override
    public void onResponse(Response<T> response, Retrofit retrofit) {
            if (!response.isSuccess()){
            this.parseHTTPError(response);
            Log.e("Retrofit",errorMessage);
            return;
        }
    }

    @Override
    public void onFailure(Throwable t) {
        errorMessage = "erro";
    }

    public APIError.Messages parseError(Response<T> response, Retrofit retrofit) {
        try {
            switch (response.code()) {
                case 304:
                    return new APIError(304, "Dados não sofreram alterações desde a última requisição").getMessages();
                case 429:
                    return new APIError(429, "Muitas requisições").getMessages();
                case 500:
                    return new APIError(500, "Sistema não está respondendo!").getMessages();
            }
            Converter<ResponseBody, APIError> errorConverter = retrofit.responseConverter(APIError.class, new Annotation[0]);
            APIError error = errorConverter.convert(response.errorBody());
            return error.getMessages();
        } catch (Exception e) {
            e.printStackTrace();
            return new APIError(400,"Ocorreu um erro interno do sistema").getMessages();
        }
    }

    private void parseHTTPError(Response<T> response) {
        int responseCode = response.code();
        switch (responseCode) {
            case 304:
                errorMessage = "Dados não sofreram alterações desde a última requisição";
                break;
            case 429:
                errorMessage = "Muitas requisições";
                break;
            case 422:
                errorMessage = "Formato inválido";
                break;
            case 500:
                errorMessage = "Erro interno do servidor";
                break;
            default:
                errorMessage = "Ocorreu um erro";
        }
    }
}
