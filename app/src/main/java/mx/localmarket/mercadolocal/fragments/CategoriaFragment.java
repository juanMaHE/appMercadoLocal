package mx.localmarket.mercadolocal.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import mx.localmarket.mercadolocal.adapters.RecyclerAdapterCategorias;
import mx.localmarket.mercadolocal.adapters.RecyclerAdapterProductores;
import mx.localmarket.mercadolocal.models.Categoria;
import mx.localmarket.mercadolocal.models.Productor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<Categoria> categoriaList;
    RecyclerView recyclerView;

    public CategoriaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoriaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriaFragment newInstance(String param1, String param2) {
        CategoriaFragment fragment = new CategoriaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categoria, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolBarCategoria);
        toolbar.setNavigationIcon(R.drawable.ic_back_button);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();

            }
        });
        CargalistaCategorias();
        // Inflate the layout for this fragment
        return view;
    }


    private void CargalistaCategorias() {

        RequestQueue queue = Volley.newRequestQueue(getContext());
//        Puede usar 10.0.2.2 para acceder a su máquina real, es un alias configurado para ayudar en el desarrollo.
        String url = "http://10.0.2.2:8080/api/categorias/obtenerCategorias";
        String imagenPredeterminada = "https://demoimagesprueba.s3.us-west-2.amazonaws.com/naranja.png";
        categoriaList = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject productorResponse = response.getJSONObject(i);

                        categoriaList.add(new Categoria(productorResponse.getInt("id"), productorResponse.getString("nombre"), ""));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recyclerView = (RecyclerView) getView().findViewById(R.id.rvCategoria);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                RecyclerAdapterCategorias recyclerAdapterProductores = new RecyclerAdapterCategorias(categoriaList);
                recyclerView.setAdapter(recyclerAdapterProductores);
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