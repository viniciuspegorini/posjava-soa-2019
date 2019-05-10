package pb.utfpr.edu.br.pw26s.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AbstractInterface <T>{

    @GET("/{route}/")
    Call<List<T>> getAll(@Path("route") String route);

    @GET("/{route}/{id}")
    Call<T> getById(@Path("route") String route, @Path("id") Long id);

    @POST("/{route}/")
    Call<Void> save(@Path("route") String route, @Body T entity);

    @PUT("/{route}/")
    Call<Void> update(@Path("route") String route, @Body T entity);

    @DELETE("/{route}/{id}")
    Call<Void> delete(@Path("route") String route, @Path("id") Long id);
}
