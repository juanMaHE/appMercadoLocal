package mx.localmarket.mercadolocal.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.localmarket.mercadolocal.R;
import mx.localmarket.mercadolocal.adapters.RecyclerAdapterAlimentos;
import mx.localmarket.mercadolocal.models.Alimento;
import mx.localmarket.mercadolocal.models.Productor;


public class AlimentosFragment extends Fragment {

    List<Alimento> alimentos;
    RecyclerView rvAlimentos;


    public AlimentosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alimentos, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        final NavController navController = Navigation.findNavController(view);
        Toolbar toolbar = view.findViewById(R.id.toolBarMain);
        toolbar.setNavigationIcon(R.drawable.ic_back_button);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                Fragment fragment = new InicioFragment();
//                activity.getSupportFragmentManager().beginTransaction()
//                        .setCustomAnimations(R.anim.slide_in, R.anim.fade_out).replace(R.id.fragmentAlimentos,fragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
//                navController.navigate(R.id.inicioFragment);
            }
        });
        CargalistaAlimtentos();
    }


    private void CargalistaAlimtentos() {

        RequestQueue queue = Volley.newRequestQueue(getContext());
//        Puede usar 10.0.2.2 para acceder a su máquina real, es un alias configurado para ayudar en el desarrollo.
        String url = "http://52.38.111.74:8076/api/alimentos/obtenerAlimentos";
        String imagenPredeterminada = "https://demoimagesprueba.s3.us-west-2.amazonaws.com/naranja.png";
        alimentos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject alimento = response.getJSONObject(i);

                        JSONObject productor = alimento.getJSONObject("productor");

                        alimentos.add(new Alimento(alimento.getInt("id"), alimento.getString("nombre")
                                , alimento.getInt("id_categoria"), alimento.getString("descripcion"), alimento.getString("imagen"),
                                new Productor(productor.getInt("id"), productor.getString("nombre"), productor.getString("apPaterno"),
                                        productor.getString("apMaterno"), productor.getString("sexo"), productor.getInt("edad")
                                )));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                rvAlimentos = (RecyclerView) getView().findViewById(R.id.rvAlimentos);
                rvAlimentos.setLayoutManager(new LinearLayoutManager(getContext()));
                RecyclerAdapterAlimentos recyclerAdapterAlimentos = new RecyclerAdapterAlimentos(alimentos);
                rvAlimentos.setAdapter(recyclerAdapterAlimentos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        queue.add(jsonArrayRequest);
    }

}
