package com.vmstudio.myapplication.controlador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vmstudio.myapplication.R;
import com.vmstudio.myapplication.controlador.dblocal.conexionesAdapter;
import com.vmstudio.myapplication.modelo.conexiones;
import com.vmstudio.myapplication.modelo.requisiciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class requisicionesAdapter extends RecyclerView.Adapter<requisicionesAdapter.requisicionesViewHolder> {
    ArrayList<requisiciones> ArrayRequisiciones;
    ArrayList<requisiciones> ArrayRequisicionesOriginal;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onOpcioneClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public requisicionesAdapter(ArrayList<requisiciones> ArrayRequisiciones){
        this.ArrayRequisiciones=ArrayRequisiciones;

        //codigo para searview
        ArrayRequisicionesOriginal=new ArrayList<>();
        ArrayRequisicionesOriginal.addAll(ArrayRequisiciones);

    }
    public static class requisicionesViewHolder extends RecyclerView.ViewHolder {
        TextView folio,fecha,departamento,tipoCompra;
        ImageView opcionesItem;
        public requisicionesViewHolder(@NonNull View itemView, OnItemClickListener listenerb) {
            super(itemView);
            //se relacionan con los id de los componentes
            folio=itemView.findViewById(R.id.tvFolioReq);
            fecha=itemView.findViewById(R.id.tvFechaReq);
            departamento=itemView.findViewById(R.id.tvDepartamento);
            tipoCompra=itemView.findViewById(R.id.tvTipoCompra);
            opcionesItem= itemView.findViewById(R.id.imgOpcionesItemReq);

            //cuando se clickea el el cardview
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

            //cuando se selecciona las opcioens del item
            opcionesItem.setOnClickListener(new View.OnClickListener() {
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
    @Override    //metodo que carga el item al recyclerview
    public requisicionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_requisicion,parent,false);

        return new requisicionesViewHolder(view, mListener);
    }

    //metodo para searcview
    public void FiltroRequisicion(String txtBuscar){
        int tamanotxtBuscar=txtBuscar.length();
        if(tamanotxtBuscar==0){
            ArrayRequisiciones.clear();
            ArrayRequisiciones.addAll(ArrayRequisicionesOriginal);
        }else{
            List<requisiciones> coleccion=ArrayRequisiciones.stream()
                    .filter(i->i.getsNumFolio().toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());
            ArrayRequisiciones.clear();
            ArrayRequisiciones.addAll(coleccion);

        }
        notifyDataSetChanged();
    }

    //cambia el texto de los item dependiendo de la info obtenida
    @Override
    public void onBindViewHolder(@NonNull requisicionesViewHolder holder, int position) {
        // TODO 21/11/2023 AGREGAR EL ESTATUS A TODO EL PROCESO
        holder.folio.setText(ArrayRequisiciones.get(position).getsNumFolio());
        holder.fecha.setText(ArrayRequisiciones.get(position).getdIdFecha());
        holder.departamento.setText(ArrayRequisiciones.get(position).getDepartamento());
        holder.tipoCompra.setText(ArrayRequisiciones.get(position).getTipoCompra());

    }

    //cuenta el numero de items
    @Override
    public int getItemCount() {
        return ArrayRequisiciones.size();
    }


}
