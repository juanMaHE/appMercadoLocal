package mx.localmarket.mercadolocal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mx.localmarket.mercadolocal.R;
import mx.localmarket.mercadolocal.fragments.ProductorFragment;
import mx.localmarket.mercadolocal.models.Productor;

public class RecyclerAdapterProductores extends RecyclerView.Adapter<RecyclerAdapterProductores.RecyclerHolder> {

    List<Productor> items;

    public RecyclerAdapterProductores(List<Productor> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerAdapterProductores.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_productores, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterProductores.RecyclerHolder holder, int position) {
        //Aqui se setean lo valores a la item_list_alimentos
        holder.tvTitulo.setText(items.get(position).getNombre() + " " + items.get(position).getAp_paterno() + " " + items.get(position).getAp_materno());

        holder.btnIdProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setReorderingAllowed(true);
                fragmentTransaction.replace(R.id.fragmentAlimentos, ProductorFragment.newInstance("", "")).setCustomAnimations(R.anim.slide_in, R.anim.fade_out);
                fragmentTransaction.addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        //Aquí se declaran los parametros de la vista
        TextView tvTitulo, tvDescripcion, tvProductor, tvId;
        ImageView imageView;
        Button btnIdProveedor;
        CardView cardViewAlimentos;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
            tvProductor = (TextView) itemView.findViewById(R.id.tvProductor);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            btnIdProveedor = (Button) itemView.findViewById(R.id.btnVerProveedor);
            cardViewAlimentos = itemView.findViewById(R.id.cardProveedores);
        }
    }
}
