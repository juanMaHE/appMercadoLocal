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
import mx.localmarket.mercadolocal.models.Categoria;

public class RecyclerAdapterCategorias extends RecyclerView.Adapter<RecyclerAdapterCategorias.RecyclerHolder> {
    List<Categoria> items;

    public RecyclerAdapterCategorias(List<Categoria> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerAdapterCategorias.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_categorias, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterCategorias.RecyclerHolder holder, int position) {
        holder.textViewCategoria.setText(items.get(position).getNombre());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        ImageView imageViewCategoria;
        TextView textViewCategoria;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            imageViewCategoria = (ImageView) itemView.findViewById(R.id.imageViewCategorias);
            textViewCategoria = (TextView) itemView.findViewById(R.id.tvCategoriasNombre);
        }
    }
}
