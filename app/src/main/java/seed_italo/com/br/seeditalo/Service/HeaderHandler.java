package seed_italo.com.br.seeditalo.Service;

public class HeaderHandler {

    private static ApiHeaders apiHeaders;


    public static ApiHeaders getApiHeaderInstance() {
        if (apiHeaders == null) apiHeaders = new ApiHeaders();
        apiHeaders.setSessionId("");
        return apiHeaders;
    }

}
