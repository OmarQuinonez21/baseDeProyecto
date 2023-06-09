package com.example.inicio.adaptadoresRutina;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inicio.R;
import com.example.inicio.VerActivity;
import com.example.inicio.entidadesRutina.Rutina;
import com.example.inicio.RutinaActivity;
import java.util.ArrayList;

public class ListaRutinasAdapter extends RecyclerView.Adapter<ListaRutinasAdapter.RutinaViewHolder>{
    String usuario;
    ArrayList<Rutina> listaRutinas;
    public ListaRutinasAdapter(ArrayList<Rutina> listaRutinas, String user){
        usuario=user;
        this.listaRutinas = listaRutinas;
    }


    @NonNull
    @Override
    public ListaRutinasAdapter.RutinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_rutina, null,false );
        return new RutinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaRutinasAdapter.RutinaViewHolder holder, int position) {
        holder.column_habito.setText(listaRutinas.get(position).getHabito());
        holder.column_categoria.setText(listaRutinas.get(position).getCategoria());
        holder.column_frecuencia.setText(listaRutinas.get(position).getFrecuencia());
        holder.column_lunes.setText(listaRutinas.get(position).getLunes());
        holder.column_martes.setText(listaRutinas.get(position).getMartes());
        holder.column_miercoles.setText(listaRutinas.get(position).getMiercoles());
        holder.column_jueves.setText(listaRutinas.get(position).getJueves());
        holder.column_viernes.setText(listaRutinas.get(position).getViernes());
        holder.column_sabado.setText(listaRutinas.get(position).getSabado());
        holder.column_domingo.setText(listaRutinas.get(position).getDomingo());
    }

    @Override
    public int getItemCount() {
        return listaRutinas.size();
    }

    public class RutinaViewHolder extends RecyclerView.ViewHolder {

        TextView column_habito, column_frecuencia, column_categoria, column_lunes, column_martes, column_miercoles, column_jueves, column_viernes, column_sabado, column_domingo;
        public RutinaViewHolder(@NonNull View itemView) {
            super(itemView);
            column_habito = itemView.findViewById(R.id.column_habito);
            column_categoria = itemView.findViewById(R.id.column_categoria);
            column_frecuencia = itemView.findViewById(R.id.column_frecuencia);
            column_lunes = itemView.findViewById(R.id.column_lunes);
            column_martes = itemView.findViewById(R.id.column_martes);
            column_miercoles = itemView.findViewById(R.id.column_miercoles);
            column_jueves = itemView.findViewById(R.id.column_jueves);
            column_viernes = itemView.findViewById(R.id.column_viernes);
            column_sabado = itemView.findViewById(R.id.column_sabado);
            column_domingo = itemView.findViewById(R.id.column_domingo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("usuario", usuario);
                    intent.putExtra("IdHabitos", listaRutinas.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
