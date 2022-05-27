package mx.localmarket.mercadolocal.fragments;

import android.os.Bundle;
import android.service.carrier.CarrierMessagingService;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
    ProgressBar progressBar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AlimentosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProveedoresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlimentosFragment newInstance(String param1, String param2) {
        AlimentosFragment fragment = new AlimentosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CargalistaAlimtentos();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alimentos, container, false);
        progressBar= view.findViewById(R.id.progressBarAlimentos);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.toolBarMain);
        toolbar.setNavigationIcon(R.drawable.ic_back_button);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setCustomAnimations(R.anim.slide_in,R.anim.fade_out);
//                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                fragmentTransaction.setReorderingAllowed(true);
//                InicioFragment fragment = new InicioFragment();
//                fragmentTransaction.replace(R.id.fragmentAlimentos, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
                getActivity().onBackPressed();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setReorderingAllowed(true);
//                fragmentTransaction.replace(R.id.fragmentAlimentos, InicioFragment.newInstance("", ""));
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });

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
                progressBar.setVisibility(getView().GONE);
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
