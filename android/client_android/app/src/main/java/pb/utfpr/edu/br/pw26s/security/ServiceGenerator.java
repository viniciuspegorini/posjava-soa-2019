package pb.utfpr.edu.br.pw26s.security;

import android.text.TextUtils;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass){
        return null;
    }

    public static <S> S createService(Class<S> serviceClass,
                                      final String authToken) {
        return null;
    }

}
