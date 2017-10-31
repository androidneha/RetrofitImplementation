package hybrid.anay.com.retrofitimplementation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Anay on 10/31/2017.
 */

public  interface ApiInterface {
    @GET("movie/top_rated")
     Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
     Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}