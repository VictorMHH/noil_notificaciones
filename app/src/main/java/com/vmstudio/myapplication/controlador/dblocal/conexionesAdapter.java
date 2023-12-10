package com.vmstudio.myapplication.controlador.dblocal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vmstudio.myapplication.R;
import com.vmstudio.myapplication.modelo.conexiones;

import java.util.ArrayList;

public class conexionesAdapter extends RecyclerView.Adapter<conexionesAdapter.conexionesViewHolder> {

    ArrayList<conexiones> ArrayConexiones;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onOpcioneClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public conexionesAdapter(ArrayList<conexiones> ArrayConexiones){
        this.ArrayConexiones=ArrayConexiones;

    }


    public class conexionesViewHolder extends RecyclerView.ViewHolder  {

        TextView NombreConexion,Url;
        ImageView opciones;

        public conexionesViewHolder(@NonNull View itemView,OnItemClickListener listenerb) {
            super(itemView);

           // itemView.setOnClickListener(this);
            NombreConexion=itemView.findViewById(R.id.tvnombreConexionItem);
            Url=itemView.findViewById(R.id.tvUrlItem);
            opciones= itemView.findViewById(R.id.icopciones);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listenerb != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listenerb.onItemClick(position);
                        }
                    }
                }
            });

            opciones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listenerb != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listenerb.onOpcioneClick(position);
                        }
                    }
                }
            });
        }

    }


    @NonNull
    @Override
    public conexionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conexiones,parent,false);

        return new conexionesViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull conexionesViewHolder holder, int position) {
        holder.NombreConexion.setText(ArrayConexiones.get(position).getNombreServidor());
        holder.Url.setText(ArrayConexiones.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return ArrayConexiones.size();
    }


}



