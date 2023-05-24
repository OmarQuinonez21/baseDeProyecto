package com.example.inicio.adaphabitos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inicio.R;
import com.example.inicio.entidades.habitos;

import java.util.ArrayList;

public class ListaHabitosAdapter extends RecyclerView.Adapter<ListaHabitosAdapter.HabitosViewHolder>{

    ArrayList<habitos> listaHabitos;

    public ListaHabitosAdapter(ArrayList<habitos> listaHabitos){
        this.listaHabitos = listaHabitos;
    }
    @NonNull
    @Override
    public HabitosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_habitos, null,false);
        return new HabitosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitosViewHolder holder, int position) {
        holder.viewNombre.setText(listaHabitos.get(position).getNombre());
        holder.viewCat.setText(listaHabitos.get(position).getCat());
        holder.viewRep.setText(listaHabitos.get(position).getRep());

    }

    @Override
    public int getItemCount() {
        return listaHabitos.size();
    }

    public class HabitosViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre,viewCat, viewRep;

        public HabitosViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewHabitos);
            viewCat = itemView.findViewById(R.id.viewcategoria);
            viewRep = itemView.findViewById(R.id.viewrep);
        }
    }
}
