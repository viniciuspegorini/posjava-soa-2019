package pb.utfpr.edu.br.pw26s.service;

import java.util.List;

import pb.utfpr.edu.br.pw26s.model.Genero;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GeneroService {

    @GET("/serie/genero/")
    Call<List<Genero>> getAll();

    @GET("/serie/genero/{id}")
    Call<Genero> getOne(@Path("id") Long id);

    @POST("/serie/genero/")
    Call<Void> save(@Body Genero genero);

    @DELETE("/serie/genero/{id}")
    Call<Void> delete(@Path("id") Long id);
}
