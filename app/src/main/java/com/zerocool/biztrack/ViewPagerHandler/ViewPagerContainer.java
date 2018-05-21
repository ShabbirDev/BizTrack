package com.zerocool.biztrack.ViewPagerHandler;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


//import com.zerocool.biztrack.Fragments.ProductEntry;
import com.zerocool.biztrack.Fragments.InventoryList;
import com.zerocool.biztrack.Fragments.ToPurchase;
import com.zerocool.biztrack.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivy on 4/27/2016.
 */

public class ViewPagerContainer extends Fragment {
    private static final String TAG ="Hello" ;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentManager supportFragmentManager;
    List<Bitmap> imagesUri;
    Bitmap screen1;

    public ViewPagerContainer() {
    }
    View rootView;
    String FILE;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_data_enrty_container, container, false);




        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);

        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

//        adapter.addFrag(new ProductEntry(), "Add New");
        adapter.addFrag(new InventoryList(), "All ModelSave");
        adapter.addFrag(new ToPurchase(), "Purchase");
//        adapter.addFrag(new E_TeachingLearningFragment(), "");
//        adapter.addFrag(new F_AssessmentFragment(), "");
//        adapter.addFrag(new G_InstitutionalStructuresAndFacilities(), "");
//        adapter.addFrag(new H_StudentSupport(), "");
//        adapter.addFrag(new I_ResearchAndExtension(), "");
//        adapter.addFrag(new J_StaffFragments(), "");
        //adapter.addFrag(new SubmitTest(), "");
        //adapter.addFrag(new GPS(), "");


        viewPager.setAdapter(adapter);

    }

    public void ViewChangeBtn1() {
        viewPager.setCurrentItem(1);
    }

    public void ViewChangeBtn2() {
        viewPager.setCurrentItem(2);
    }

    public void ViewChangeBtn3() {
        viewPager.setCurrentItem(3);
    }

    public void ViewChangeBtn4() {
        viewPager.setCurrentItem(4);
    }

    public void ViewChangeBtn5() {
        viewPager.setCurrentItem(5);
    }

    public void ViewChangeBtn6() {
        viewPager.setCurrentItem(6);
    }

    public void ViewChangeBtn7() {
        viewPager.setCurrentItem(7);
    }

    public void ViewChangeBtn8() {viewPager.setCurrentItem(8); }

    public void ViewChangeBtn9() {viewPager.setCurrentItem(0);}


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);

        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
