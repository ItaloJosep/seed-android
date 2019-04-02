package seed_italo.com.br.seeditalo.Service;

import java.util.List;
import retrofit.Call;
import retrofit.http.GET;
import seed_italo.com.br.seeditalo.Models.Value;

public interface ServicesRouter {

    @GET("ticker.json")
    Call<Value> getValues();

    @GET("ticker.json")
    Call<List<String>> getValues1();
}
