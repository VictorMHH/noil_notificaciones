package com.vmstudio.myapplication.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.vmstudio.myapplication.R;
import com.vmstudio.myapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private PieChart pieChart;
    private BarChart barChartRequisiciones, barChartOrdenesCompra;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        pieChart=root.findViewById(R.id.graficaPastelReq);
        barChartRequisiciones=binding.charBarrasRequisciones;
        barChartOrdenesCompra=binding.charBarraOrdenCompra;

        construlle_grafica_pastel();
        construyeGraficaBarrasRequisicion();
        construyeGraficaBarrasOrdenCompra();


       // final TextView textView = binding.textHome;
       // homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void construlle_grafica_pastel(){
        Description description=new Description();
        description.setText("Grafica de pastel");
        description.setPosition(150,100);
        pieChart.setDescription(description);


        ArrayList<PieEntry> pieEntries2=new ArrayList<>();
        pieEntries2.add(new PieEntry(2,"Requisicion"));
        pieEntries2.add(new PieEntry(4,"Orden de compra"));

        PieDataSet pieDataSet=new PieDataSet( pieEntries2 , "Descripción");

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }


    public void construyeGraficaBarrasRequisicion(){
        barChartRequisiciones.getAxisRight().setDrawLabels(false);
        List<String> xvalues= Arrays.asList("Validadas","Autorizadas","Pendientes","canceladas");
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 10));
        entries.add(new BarEntry(1, 20));
        entries.add(new BarEntry(2, 30));
        entries.add(new BarEntry(3, 40));


        YAxis yAxis= barChartRequisiciones.getAxisLeft();
        yAxis.setAxisMaximum(0f);
        yAxis.setAxisMaximum(50f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);


        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData data = new BarData(set);
       // data.setBarWidth(0.9f); // set custom bar width
        barChartRequisiciones.setData(data);
        //barChartRequisiciones.setFitBars(true); // make the x-axis fit exactly all bars
        barChartRequisiciones.getDescription().setEnabled(false);
        barChartRequisiciones.invalidate();

        barChartRequisiciones.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xvalues));
        barChartRequisiciones.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChartRequisiciones.getXAxis().setGranularity(1f);
        barChartRequisiciones.getXAxis().setGranularityEnabled(true);



    }


    public void construyeGraficaBarrasOrdenCompra(){
        List<BarEntry> ent1 = new ArrayList<>();
        ent1.add(new BarEntry(0, 5));
        ent1.add(new BarEntry(1, 4));
        ent1.add(new BarEntry(2, 8));
        ent1.add(new BarEntry(3, 6));
        BarDataSet set = new BarDataSet(ent1, "BarDataSet");
        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        barChartOrdenesCompra.setData(data);
        barChartOrdenesCompra.setFitBars(true); // make the x-axis fit exactly all bars
        barChartOrdenesCompra.invalidate();

    }
    public void consulta_estatus_requisiciones(){
        // TODO 13/11/2023 CONFIGURAR URL PARA QUE SE GENERE CON SHARED PREFERECNES Y LA CLASE WEBSERVICES
       String url="https://victormhh.online/verytab/consulta_estatus_requisicion.php?fechai=01/10/2023&fechaf=11/11/2023";
    }


}