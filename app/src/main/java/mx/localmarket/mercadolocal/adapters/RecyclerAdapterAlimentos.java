package mx.localmarket.mercadolocal.adapters;

import android.os.Bundle;
import android.util.Log;
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
import mx.localmarket.mercadolocal.models.Alimento;

public class RecyclerAdapterAlimentos extends RecyclerView.Adapter<RecyclerAdapterAlimentos.RecyclerHolder> {

    List<Alimento> items;
    Bundle bundle = null;

    public RecyclerAdapterAlimentos(List<Alimento> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerAdapterAlimentos.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_alimentos, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterAlimentos.RecyclerHolder holder, int position) {
        //Aqui se setean lo valores a la item_list_alimentos
        holder.tvTitulo.setText(items.get(position).getNombre());
        holder.tvDescripcion.setText(items.get(position).getDescripcion());
        holder.tvProductor.setText(items.get(position).getProductor().getNombre() + " " + items.get(position).getProductor().getAp_paterno() + " " + items.get(position).getProductor().getAp_materno());
        Picasso.with(holder.imageView.getContext()).load(items.get(position).getImagen()).resize(100, 100).centerCrop().into(holder.imageView);


        holder.btnIdProductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                bundle.putInt("idProductor", items.get(position).getProductor().getId());
                ProductorFragment productorFragment = new ProductorFragment();
                productorFragment.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setReorderingAllowed(true);

                fragmentTransaction.replace(R.id.fragmentAlimentos, productorFragment);
                fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();

            }
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {
        //Aqu?? se declaran los parametros de la vista
        TextView tvTitulo, tvDescripcion, tvProductor, tvId;
        ImageView imageView;
        Button btnIdProductor;
        CardView cardViewAlimentos;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
            tvProductor = (TextView) itemView.findViewById(R.id.tvProductor);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            btnIdProductor = (Button) itemView.findViewById(R.id.btnVerProductor);
            cardViewAlimentos = itemView.findViewById(R.id.cardAlimentos);

        }


    }
}
