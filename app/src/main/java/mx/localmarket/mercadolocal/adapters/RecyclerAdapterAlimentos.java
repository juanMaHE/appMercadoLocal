package mx.localmarket.mercadolocal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mx.localmarket.mercadolocal.R;
import mx.localmarket.mercadolocal.models.Alimento;

public class RecyclerAdapterAlimentos extends RecyclerView.Adapter<RecyclerAdapterAlimentos.RecyclerHolder> {

    List<Alimento> items;

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

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        //Aquí se declaran los parametros de la vista
        TextView tvTitulo, tvDescripcion, tvProductor;
        ImageView imageView;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            tvTitulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
            tvProductor = (TextView) itemView.findViewById(R.id.tvProductor);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
