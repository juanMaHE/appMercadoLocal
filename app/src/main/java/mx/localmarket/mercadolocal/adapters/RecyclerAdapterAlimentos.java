package mx.localmarket.mercadolocal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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


        holder.tvTitulo.setText(items.get(position).getNombre());
        holder.tvDescripcion.setText(items.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvDescripcion;
        ImageView imageView;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            tvTitulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
