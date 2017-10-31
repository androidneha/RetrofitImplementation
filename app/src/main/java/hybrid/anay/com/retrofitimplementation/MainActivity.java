package hybrid.anay.com.retrofitimplementation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "11ed05d21aa0bbf4f0a925b9aa52b8b0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     if (API_KEY=="")
     {
        Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
        return;
    }

    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
    call.enqueue(new Callback<MoviesResponse>()
    {
        @Override
        public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response)
        {
            List<Movie> movies = response.body().getResults();
            recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            Toast.makeText(getApplicationContext(),"Number of movies received: " + movies.size(),Toast.LENGTH_LONG).show();
            Log.d(TAG, "Number of movies received: " + movies.size());
            Log.d(TAG, "Number of movies received: " + response.body().toString());
        }

        @Override
        public void onFailure(Call<MoviesResponse>call, Throwable t)
        {
            Log.e(TAG, t.toString());
        }
    });
}
}
