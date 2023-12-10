package com.vmstudio.myapplication.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vmstudio.myapplication.R;
import com.vmstudio.myapplication.controlador.requisicionesAdapter;
import com.vmstudio.myapplication.databinding.FragmentGalleryBinding;
import com.vmstudio.myapplication.detalles_requisiciones;
import com.vmstudio.myapplication.modelo.requisiciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GalleryFragment extends Fragment implements SearchView.OnQueryTextListener {

    private FragmentGalleryBinding binding;
    private ArrayList<requisiciones> requisicionesList;
    RecyclerView recyclerReq;
    ExtendedFloatingActionButton fabExOpciones;
    FloatingActionButton fabfiltrar, fabbuscar;
    TextView tvfabBuscar,tvfabFiltrar;
    SearchView buscarRequiscion;
    LinearLayout layoutRv;
    ShimmerFrameLayout shimmerFrameLayout;

    requisicionesAdapter adapter;

    boolean fabisExtend;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        init();
        shimmerFrameLayout=root.findViewById(R.id.shimmerview);

        cargarRecyclerRequisiones();
        Toast.makeText(getContext(),"Prueba de github",Toast.LENGTH_SHORT).show();

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        shimmerFrameLayout.startShimmer();
        super.onResume();
    }

    @Override
    public void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    private void init(){
        recyclerReq=binding.rvRequisicion;
        buscarRequiscion=binding.searchRequisicion;
        layoutRv=binding.layautRecycler;

        //linea para la busqueda de searcview
        buscarRequiscion.setOnQueryTextListener(this);
    }
    public void cargarRecyclerRequisiones() {
        recyclerReq.setHasFixedSize(true);
        requisicionesList=new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String sContrato = "VERYTAB";
        String Folio = "-1";
        String Estatus = "-1";
        String FechaI = "2023/11/01";
        String FechaF = "2023/11/10";

       // TODO 13/11/2023 CONFIGURAR URL PARA QUE SE GENERE CON SHARED PREFERECNES Y LA CLASE WEBSERVICES
        String url = "https://victormhh.online/verytab/consulta_requisicion.php"+
                "?contrato="+sContrato+
                "&folio=" +Folio+
                "&estatus="+Estatus+
                "&fechai="+FechaI+
                "&fechaf="+FechaF;

        StringRequest request=new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //lo que hace si sale bien
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = (JSONObject) array.get(i);
                                requisicionesList.add(new requisiciones(
                                        obj.getString("sContrato"),
                                        obj.getString("sNumFolio"),
                                        obj.getString("iFolioRequisicion"),
                                        obj.getString("dIdFecha"),
                                        obj.getString("IdDepartamento"),
                                        obj.getString("sStatus"),
                                        obj.getString("TipoCompra"),
                                        obj.getString("Departamento")
                                ));

                            }

                             adapter = new requisicionesAdapter(requisicionesList);
                            adapter.setOnItemClickListener(new requisicionesAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    Intent intent=new Intent(getContext(), detalles_requisiciones.class);
                                    intent.putExtra("sContraro",requisicionesList.get(position).getsContrato());
                                    intent.putExtra("ifolioReq",Integer.parseInt(requisicionesList.get(position).getiFolioRequisicion()));
                                    intent.putExtra("sFolio",requisicionesList.get(position).getsNumFolio());
                                    intent.putExtra("Fecha",requisicionesList.get(position).getdIdFecha());
                                    intent.putExtra("depa",requisicionesList.get(position).getDepartamento());
                                    intent.putExtra("TipoCompra",requisicionesList.get(position).getTipoCompra());
                                    startActivity(intent);

                                }

                                @Override
                                public void onOpcioneClick(int position) {
                                   /* Intent intent=new Intent(getContext(), detalles_requisiciones.class);
                                    startActivity(intent);*/
                                }
                            });
                             recyclerReq.setAdapter(adapter);
                             recyclerReq.setLayoutManager(new LinearLayoutManager(getContext()));
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Error del catch: " + e, Toast.LENGTH_SHORT).show();
                        }
                        shimmerFrameLayout.startShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        layoutRv.setVisibility(View.VISIBLE);
                }
    }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO 001234
                Toast.makeText(getContext(),"001234:algo salio mal: "+ error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    private void clickFabOpciones(){
       if(!fabisExtend){
           fabbuscar.show();
           fabfiltrar.show();
           tvfabBuscar.setVisibility(View.VISIBLE);
           tvfabFiltrar.setVisibility(View.VISIBLE);
           fabExOpciones.extend();

           fabisExtend=true;
       }else{
           fabbuscar.hide();
           fabfiltrar.hide();
           tvfabBuscar.setVisibility(View.GONE);
           tvfabFiltrar.setVisibility(View.GONE);
           fabExOpciones.shrink();
           fabisExtend=false;
       }
    }

    private void clickFabFiltrar(){
        Toast.makeText(getContext(),"Se presiono FAB filtrar",Toast.LENGTH_SHORT).show();
    }

    private void clickFabBuscar(){
        Toast.makeText(getContext(),"Se presiono FAB buscar",Toast.LENGTH_SHORT).show();
    }



    //metodos que se generan al implementar la la clase SearchView.OnQueryTextListener
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String textoBuscar) {
        adapter.FiltroRequisicion(textoBuscar);
        return false;
    }
}