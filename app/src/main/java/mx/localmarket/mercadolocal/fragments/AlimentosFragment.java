package mx.localmarket.mercadolocal.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.localmarket.mercadolocal.R;
import mx.localmarket.mercadolocal.adapters.RecyclerAdapterAlimentos;
import mx.localmarket.mercadolocal.models.Alimento;


public class AlimentosFragment extends Fragment {

    List<Alimento> alimentos;
    RecyclerView rvAlimentos;

    public AlimentosFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://52.38.111.74:8076/api/alimentos/obtenerAlimentos";
        alimentos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject alimento = response.getJSONObject(i);
                        alimentos.add(new Alimento(alimento.getInt("id"), alimento.getString("nombre")
                                , alimento.getInt("id_categoria"), alimento.getString("descripcion")));

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_alimentos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
