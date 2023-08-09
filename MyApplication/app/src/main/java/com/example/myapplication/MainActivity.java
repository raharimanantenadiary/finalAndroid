package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.myapplication.model.Notification;
import com.example.myapplication.model.Sitetouristique;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    TextView nom_utilisateur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationHelper.showNotification(this, "Application site touristique", "Bienvennu cher Utilisateur");
        drawerLayout=findViewById(R.id.nav_view);
        Toolbar toolbar=findViewById(R.id.toolbar);
        NavigationView navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Login());

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_draw_open,R.string.navigation_draw_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null)
        {
            SharedPreferences sp = getSharedPreferences("user_information", MODE_PRIVATE);
            String idUtilisateur = sp.getString("id_utilisateur", "false");
            if (idUtilisateur != "false") {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Acceuil()).commit();
                navigationView.setCheckedItem(R.id.home_menu);
            }else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Login()).commit();
                navigationView.setCheckedItem(R.id.login_menu);
            }
        }



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.login_menu:
                SharedPreferences sp = getSharedPreferences("user_information", MODE_PRIVATE);
                String idUtilisateur = sp.getString("id_utilisateur", "false");
                if (idUtilisateur != "false") {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Acceuil()).commit();
                    break;
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Login()).commit();
                    break;
                }
            case R.id.inscription_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Inscription()).commit();
                break;
            case R.id.home_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Acceuil()).commit();
                break;
            case R.id.ecran_preference_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BasculeMod()).commit();
                break;
            case R.id.logout_menu:
                SharedPreferences settings = getSharedPreferences("user_information", MODE_PRIVATE);
                settings.edit().clear().commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Login()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed()
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer((GravityCompat.START));
        }
        else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment); // Utilisez R.id.fragment_container ici
        fragmentTransaction.commit();
    }
}
