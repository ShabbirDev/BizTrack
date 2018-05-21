package com.zerocool.biztrack.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.zerocool.biztrack.Fragments.AllProductList;
import com.zerocool.biztrack.Fragments.CustomerList;
import com.zerocool.biztrack.Fragments.Home;
import com.zerocool.biztrack.R;


public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener{

    AHBottomNavigation bottomNavigation;
//    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigation= (AHBottomNavigation) findViewById(R.id.myBottomNavigation_ID);

        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();

    }

    private void createNavItems()
    {
        //CREATE ITEMS
        AHBottomNavigationItem products = new AHBottomNavigationItem("Products",R.drawable.ic_orion_product_list);
        AHBottomNavigationItem dashboardItem = new AHBottomNavigationItem("DashBoard",R.drawable.ic_dashboard_black_24dp);
        AHBottomNavigationItem customersItem = new AHBottomNavigationItem("Customers",R.drawable.ic_orion_customer_list);

        //ADD ITEMS TO BAR
        bottomNavigation.addItem(products);
        bottomNavigation.addItem(dashboardItem);
        bottomNavigation.addItem(customersItem);

        //PROPERTIES
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        bottomNavigation.setCurrentItem(1);
        Home home = new Home();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_id,home).commit();

    }

    @Override
    public void onTabSelected(int position, boolean wasSelected) {
        if(position==0)
        {
            AllProductList allProductList = new AllProductList();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,allProductList).commit();
        }else if(position==1)
        {
            Home home = new Home();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,home).commit();
        }else if(position==2)
        {
            CustomerList customerList = new CustomerList();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,customerList).commit();
        }
        else
        {
            Home home = new Home();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,home).commit();
        }


    }


}