package emsi.sabri.ma.sabri;


import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import emsi.sabri.ma.sabri.adapter.StarAdapter;
import emsi.sabri.ma.sabri.beans.Star;
import emsi.sabri.ma.sabri.service.StarService;

public class ListActivity extends AppCompatActivity {
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private static final String TAG = "StarAdapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_list);
        stars = new ArrayList<>();
        StarService service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void init() {
        StarService service = StarService.getInstance();
        service.create(new Star("Victor Hugo", "https://information.tv5monde.com/sites/info.tv5monde.com/files/styles/large_article/public/assets/images/Capture_decran_2015-11-19_a_16.03.23.png?itok=YXeuOdpj", 3.5f));
        service.create(new Star("Hemingway-ernest", "https://www.hello-la-floride.com/wp-content/uploads/Hemingway-ernest-230x230.jpeg", 3));
        service.create(new Star("James Baldwin", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f3/James_Baldwin_37_Allan_Warren_%28cropped%29.jpg/220px-James_Baldwin_37_Allan_Warren_%28cropped%29.jpg", 5));
        service.create(new Star("george clooney", "https://www.infos.fr/wp-content/uploads/2019/11/oscar-wilde-768x489.jpg", 1));
        service.create(new Star("louise bouroin", "https://c8.alamy.com/compfr/2d99tp9/le-plus-celebre-ecrivain-irlandais-bram-stoker-1847-1912-auteur-du-livre-d-horreur-dracula-scrittore-letteratura-letterato-litterature-vampiro-vampiri-tiller-vampir-vampirs-portrait-ritratto-barba-barbe-cravatta-cravate-archivio-gbb-2d99tp9.jpg", 5));
        service.create(new Star("louise sabri", "http://www.enviedecrire.com/wp-content/uploads/edgar-allan-poe.jpg", 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);


        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }
}