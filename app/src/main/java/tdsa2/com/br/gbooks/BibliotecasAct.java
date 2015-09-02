package tdsa2.com.br.gbooks;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import tdsa2.com.br.gbooks.endpoints.BibliotecasService;
import tdsa2.com.br.gbooks.model.Biblioteca;
import tdsa2.com.br.gbooks.model.BibliotecaContainer;
import tdsa2.com.br.gbooks.db.BDManager;
import tdsa2.com.br.gbooks.util.Connection;

public class BibliotecasAct extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibliotecas);

        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #getLibraries()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            mMap = googleMap;
                            getLibraries();
                        }
                    });
        } else {
            getLibraries();
        }
    }

    private void getLibraries() {
        if (Connection.checkConn(this)) {

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://1-dot-doacaoanimais-1040.appspot.com/")
                    .build();
            BibliotecasService service = restAdapter.create(BibliotecasService.class);
            service.getBibliotecas(new Callback<BibliotecaContainer>() {

                @Override
                public void failure(RetrofitError error) {
                    Log.e("GBOOKS", "WEB: FALHA");
                }

                @Override
                public void success(BibliotecaContainer c, Response response) {
                    Log.e("GBOOKS", "WEB: SUCESSO");
                    BDManager bd = new BDManager(BibliotecasAct.this);
                    bd.cleanLibraries();

                    for (Biblioteca b : c.bibliotecas) {
                        Log.e("GBOOKS", "WEB: "+b.nome);
                        Log.e("GBOOKS", "WEB: "+b.endereco);
                        MarkerOptions mOpt = new MarkerOptions();
                        mOpt.title(b.nome);
                        mOpt.snippet(b.endereco);
                        mOpt.position(new LatLng(b.latitude, b.longitude));
                        mMap.addMarker(mOpt);
                        bd.insertLibrary(b);
                    }
                }
            });
        } else {
            BDManager bd = new BDManager(BibliotecasAct.this);
            List<Biblioteca> libs = bd.getLibraries();
            for (Biblioteca b : libs) {
                Log.e("GBOOKS", b.nome);
                Log.e("GBOOKS", b.endereco);
                MarkerOptions mOpt = new MarkerOptions();
                mOpt.title(b.nome);
                mOpt.snippet(b.endereco);
                mOpt.position(new LatLng(b.latitude, b.longitude));
                mMap.addMarker(mOpt);
            }
        }
    }
}
