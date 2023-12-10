package com.vmstudio.myapplication.controlador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vmstudio.myapplication.R;
import com.vmstudio.myapplication.modelo.detalle_requisicion;
import com.vmstudio.myapplication.modelo.requisiciones;

import java.util.ArrayList;

public class requisicionesDetalleAdapter extends RecyclerView.Adapter<requisicionesDetalleAdapter.requisicionesDetalleViewHolder> {
    ArrayList<detalle_requisicion> ArrayRequisicionesDetalle;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onOpcioneClick(int position);

    }

    public void setOnItemClickListener(requisicionesDetalleAdapter.OnItemClickListener listener) {
        mListener =  listener;
    }

    public requisicionesDetalleAdapter(ArrayList<detalle_requisicion> ArrayRequisicionesDet){
        this.ArrayRequisicionesDetalle=ArrayRequisicionesDet;

    }


    public class requisicionesDetalleViewHolder extends RecyclerView.ViewHolder {
        TextView folioInsumo,descripcionInsumo,medidaInsumo,cantidadInsumo,costoInsumo,totalReq;
        public requisicionesDetalleViewHolder(@NonNull View itemView, OnItemClickListener listenerb) {
            super(itemView);
            folioInsumo=itemView.findViewById(R.id.tvIdInsumoReqDet);
            descripcionInsumo=itemView.findViewById(R.id.tvDescripcionInsumoReqDet);
            medidaInsumo=itemView.findViewById(R.id.tvMedidaReqDet);
            cantidadInsumo=itemView.findViewById(R.id.tvCantidadReqDet);
            costoInsumo=itemView.findViewById(R.id.tvCostoReqDet);
            totalReq=itemView.findViewById(R.id.tvTotalxInsumoReqDet);

        }
    }

    @NonNull
    @Override
    public requisicionesDetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalle_requisicion,parent,false);
        return new requisicionesDetalleViewHolder(view, mListener);

    }

    @Override
    public void onBindViewHolder(@NonNull requisicionesDetalleViewHolder holder, int position) {

        utileria u=new utileria();

        String costoTotal=u.formatoNumero(ArrayRequisicionesDetalle.get(position).getCostoxCantidad(),2);
        String costo=u.formatoNumero(ArrayRequisicionesDetalle.get(position).getdCosto(),2);
        String cantidad=u.formatoNumero(ArrayRequisicionesDetalle.get(position).getdCantidad(),0);

        holder.folioInsumo.setText(ArrayRequisicionesDetalle.get(position).getInsumo());
        holder.descripcionInsumo.setText(ArrayRequisicionesDetalle.get(position).getmDescripcion());
        holder.medidaInsumo.setText(ArrayRequisicionesDetalle.get(position).getMedida());
        holder.cantidadInsumo.setText(cantidad);
        holder.costoInsumo.setText("$"+costo);
        holder.totalReq.setText("$"+costoTotal);

    }

    @Override
    public int getItemCount() {
        return ArrayRequisicionesDetalle.size();
    }



}
