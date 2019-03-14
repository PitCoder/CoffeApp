package mx.ipn.escom.prueba.coffeeapp.views;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import mx.ipn.escom.prueba.coffeeapp.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class OrdenesFragment extends androidx.fragment.app.Fragment {

    // Define the listener of the interface type
    // listener will the activity instance containing fragment
    private AdapterView.OnItemSelectedListener listener;
    private RecyclerView recyclerView;
    private FragmentActivity myContext;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private BroadcastReceiver broadcastReceiver;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    Bundle bundle;

    public OrdenesFragment() {
        super();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ordenes, container, false);
        //Attach elements to the view (In this case we attach to the view the RecyclerView)
        //recyclerView = view.findViewById(R.id.listaOrdenes);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        cargarFragment(new OrdenesHistoricalFragment());

        bundle = this.getArguments();

        myContext = getActivity();

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.container);
        toolbar = view.findViewById(R.id.toolBar);
        //sectionsPagerAdapter = new SectionsPagerAdapter(myContext.getSupportFragmentManager());
        //viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }


    private Boolean cargarFragment(androidx.fragment.app.Fragment fragment){
        Bundle bundle = new Bundle();
        if (fragment!=null){
            bundle.putInt("CUENTA_ID", bundle.getInt("CUENTA_ID"));
            fragment.setArguments(bundle);
            myContext.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    // Store the listener (activity) that will have events fired once the fragment is attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AdapterView.OnItemSelectedListener) {
            listener = (AdapterView.OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public androidx.fragment.app.Fragment getItem(int position) {
            Bundle localBundle = new Bundle();
            androidx.fragment.app.Fragment fragment = null;
            localBundle.putInt("CUENTA_ID", bundle.getInt("CUENTA_ID"));
            switch (position){
                case 0:
                    fragment = new OrdenesHistoricalFragment();
                    break;
                //case 1:s
                   // fragment = new ProductosFragment();
                   // break;
                default:
                    //fragment = new MenuDiaFragment();
                    break;
            }
            fragment.setArguments(localBundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Historial de Pedidos";
                case 1:
                    return "Pedidos Activos";
                default:
                    return null;
            }
        }
    }
}
