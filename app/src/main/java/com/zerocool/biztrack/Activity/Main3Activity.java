//package com.zerocool.biztrack.Activity;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.BottomNavigationView;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//
//import com.zerocool.biztrack.Fragments.AllProductList;
//import com.zerocool.biztrack.Fragments.CustomerList;
//import com.zerocool.biztrack.Fragments.Home;
//import com.zerocool.biztrack.NavigationFragments.HomeFragment;
//import com.zerocool.biztrack.R;
//import com.zerocool.biztrack.ViewPagerHandler.ViewPagerContainer;
//
//public class Main3Activity extends AppCompatActivity {
//
//    private TextView mTextMessage;
//
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//
//                    Fragment fragment = new Home();
//                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                    ft.replace(R.id.frame, fragment);
//                    ft.commit();
//                return true;
//
//                case R.id.navigation_dashboard:
//                    Fragment fragment2 = new CustomerList();
//                    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
//                    ft2.replace(R.id.frame, fragment2);
//                    ft2.commit();
////                    mTextMessage.setText(R.string.title_dashboard);
//                    return true;
//                case R.id.navigation_notifications:
////                    mTextMessage.setText(R.string.title_notifications);
//                    Fragment fragment3 = new AllProductList();
//                    FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
//                    ft3.replace(R.id.frame, fragment3);
//                    ft3.commit();
//                    return true;
//                default:
//                    Fragment fragment4 = new Home();
//                    FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
//                    ft4.replace(R.id.frame, fragment4);
//                    ft4.commit();
//                    return true;
//
//            }
//            //return false;
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main3);
//
//        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//    }
//
//}
