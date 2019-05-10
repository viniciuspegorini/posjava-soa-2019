package pb.utfpr.edu.br.pw26s.security;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor{
    private String authToken;

    public AuthenticationInterceptor(String token){
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder()
                .header("Authentication","Bearer " + authToken);

        Request request = builder.build();

        return chain.proceed(request);
    }
}
