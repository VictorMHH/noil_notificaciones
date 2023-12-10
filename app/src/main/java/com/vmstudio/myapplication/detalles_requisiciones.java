package com.vmstudio.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vmstudio.myapplication.controlador.requisicionesAdapter;
import com.vmstudio.myapplication.controlador.requisicionesDetalleAdapter;
import com.vmstudio.myapplication.modelo.detalle_requisicion;
import com.vmstudio.myapplication.modelo.requisiciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class detalles_requisiciones extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView folioReq,FechaReq,departamentoReq,tipoCompraReq,totalReq;
    private ArrayList<detalle_requisicion> requisicionesList;
    requisicionesDetalleAdapter adapter;
    String contrato,sfolio,fecha,departamento,tipoC;
    int folio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_requisicion);


        instancias();
        recuperaDatosExtraActividadAnt();
        init();
        cargarDetalleReq(contrato,folio);

    }

    public void init(){
        toolbar=findViewById(R.id.tbDetalleReq);
        recyclerView=findViewById(R.id.rvDetalleReq);
        recyclerView.setHasFixedSize(true);
        folioReq=findViewById(R.id.tvFolioReqDet);
        FechaReq=findViewById(R.id.tvFechaReqDet);
        departamentoReq=findViewById(R.id.tvDepartamentoReqDet);
        tipoCompraReq=findViewById(R.id.tvTipoCompraReqDet);
        totalReq=findViewById(R.id.tvTotalReq);

        folioReq.setText(sfolio);
        FechaReq.setText(fecha);
        departamentoReq.setText(departamento);
        tipoCompraReq.setText(tipoC);

    }
    public void instancias(){
        requisicionesList=new ArrayList<>();
    }

    public void recuperaDatosExtraActividadAnt(){
        contrato=getIntent().getStringExtra("sContraro");
        folio=getIntent().getIntExtra("ifolioReq",0);
        sfolio=getIntent().getStringExtra("sFolio");
        fecha=getIntent().getStringExtra("Fecha");
        departamento=getIntent().getStringExtra("depa");
        tipoC=getIntent().getStringExtra("TipoCompra");

    }

    public void cargarDetalleReq(String sContrato,int Folio){
        RequestQueue queue = Volley.newRequestQueue(detalles_requisiciones.this);

        // TODO 26/11/2023 CONFIGURAR URL PARA QUE SE GENERE CON SHARED PREFERECNES Y LA CLASE WEBSERVICES
        String url = "https://victormhh.online/verytab/consulta_detalle_requisicion.php"+
                "?contrato="+sContrato+
                "&folio=" +Folio+"";

        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = (JSONObject) array.get(i);
                                requisicionesList.add(new detalle_requisicion(
                                        obj.getString("sContrato"),
                                        obj.getInt("iFolioRequisicion"),
                                        obj.getInt("IdInsumo"),
                                        obj.getString("mDescripcion"),
                                        obj.getInt("IdMedida"),
                                        obj.getDouble("dCantidad"),
                                        obj.getDouble("dCosto"),
                                        obj.getString("Medida"),
                                        obj.getString("Insumo"),
                                        obj.getDouble("CostoTotal"),
                                        obj.getDouble("CostoxCantidad")
                                ));
                            }
                            adapter = new requisicionesDetalleAdapter(requisicionesList);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        } catch (Exception e) {
                           Toast.makeText(detalles_requisiciones.this, "Error del catch: " + e, Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO 001235
                //Toast.makeText(detalles_requisiciones.this,"001235:algo salio mal: "+ error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0,1,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}