package com.mkrlabs.pmis.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.mkrlabs.pmis.AuthenticationActivity;
import com.mkrlabs.pmis.R;
import com.mkrlabs.pmis.utils.MyShreadPref;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private WebView webview;
    private MaterialButton logOutButton;

    private ImageButton appBarChatIcon;

    private TextView toolBarName;
    private ImageButton navButton;
    private TextView navProfileTV,navTryList,navSetting,navRate,navAbout,navVersion,navLogoutTV,navUserName;
    private FrameLayout frameContainer;

    private DrawerLayout home_drawer;
    private MyShreadPref myShreadPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        myShreadPref = new MyShreadPref(this);

        inits();
        initLeftDrawer();
        initLeftDrawerClickListener();

        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_drawer.openDrawer(GravityCompat.START);
            }
        });

    }
    private void inits(){
        mAuth = FirebaseAuth.getInstance();
        frameContainer = findViewById(R.id.frameContainer);
        navButton= findViewById(R.id.navButton);
        toolBarName= findViewById(R.id.toolBarName);
        appBarChatIcon = findViewById(R.id.appBarChatIcon);

        home_drawer = findViewById(R.id.home_drawer);

    }
    private void initLeftDrawerClickListener() {

        navLogoutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);


                alertDialog.setMessage("Do you want to LogOut ?");
                alertDialog.setTitle("Alert !");
                alertDialog.setCancelable(true);

                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userLogOut();
                        home_drawer.closeDrawer(GravityCompat.START);


                    }
                });

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = alertDialog.create();

                dialog.show();


            }
        });

        navProfileTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //setUpFragment(new ProfileFragment(), "Profile");
               // bottomNavigation.setItemSelected(R.id.profile,true);

              //  home_drawer.closeDrawer(GravityCompat.START);
            }
        });

        navTryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // setUpFragment(new TryListFragment(), "Try List");
               // bottomNavigation.setItemSelected(R.id.tryList,true);

                //home_drawer.closeDrawer(GravityCompat.START);
            }
        });
    }
    public void setUpFragment(Fragment fragment, String name) {

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).commit();
            toolBarName.setText(name);

        }

    }

    private void userLogOut() {

        mAuth.signOut();
        myShreadPref.setUID("");
        Intent intent = new Intent(this, AuthenticationActivity.class);
        startActivity(intent);
        finish();


    }
    private void initLeftDrawer() {
        navUserName=findViewById(R.id.nav_user_name);
        navProfileTV=findViewById(R.id.navProfileTV);
        navTryList=findViewById(R.id.navTryList);
        navSetting=findViewById(R.id.navSettings);
        navRate=findViewById(R.id.navRateTv);
        navAbout=findViewById(R.id.navAboutTv);
        navVersion=findViewById(R.id.navVersion);
        navLogoutTV=findViewById(R.id.navLogoutTV);

        navUserName.setText(myShreadPref.getLOGGED_USER_NAME());

    }
}