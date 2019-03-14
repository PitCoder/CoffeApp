package mx.ipn.escom.prueba.coffeeapp.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.ipn.escom.prueba.coffeeapp.R;
import mx.ipn.escom.prueba.coffeeapp.adapters.OrdenesHistoricalAdapter;
import mx.ipn.escom.prueba.coffeeapp.viewmodel.OrdenesHistoricalViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdenesHistoricalFragment extends androidx.fragment.app.Fragment {
    private RecyclerView recyclerView;
    private OrdenesHistoricalViewModel ordenesHistoricalViewModel;
    private OrdenesHistoricalAdapter ordenesHistoricalAdapter;
    private Bundle bundle;


    public OrdenesHistoricalFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("--->","ENTRE");
        //Here we inflate the layout for this fragment
        ordenesHistoricalViewModel = new OrdenesHistoricalViewModel();
        ordenesHistoricalViewModel.setOrdenesBusiness(getActivity().getApplication());
        //Here we inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_ordenes, container, false);
        //Attach elements to the view (In this case we attach to the view the RecyclerView)
        recyclerView = view.findViewById(R.id.listaOrdenes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        bundle = this.getArguments();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            Integer idCuenta = bundle.getInt("CUENTA_ID");
            if(idCuenta == null) {
                Log.d("---->", "OJO AQUI PRRO");
            }
            else{
                Log.d("---->HOLI", idCuenta.toString());
            }
            recyclerView.setAdapter(new OrdenesHistoricalAdapter(this, ordenesHistoricalViewModel.getHistorialPedidos(idCuenta), idCuenta));
            ordenesHistoricalAdapter = (OrdenesHistoricalAdapter) recyclerView.getAdapter();
            if (ordenesHistoricalAdapter.getmList() == null){
                ordenesHistoricalAdapter.setmList(ordenesHistoricalViewModel.getHistorialPedidos(idCuenta));
                Log.d("---->Result_ARRAY", String.valueOf(ordenesHistoricalAdapter.getmList().size()));
            }else{
                ProgressBar progressBar = view.findViewById(R.id.ordenes_progress);
                progressBar.setVisibility(View.GONE);
            }
        }
    }
}
