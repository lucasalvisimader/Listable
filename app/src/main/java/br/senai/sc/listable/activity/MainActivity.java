package br.senai.sc.listable.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import br.senai.sc.listable.R;
import br.senai.sc.listable.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        Toolbar toolbar = binding.appBarMain.toolbar;

        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.setTextAppearance(this, R.style.Theme_Toolbar_Title);
                break;
            }
        }

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_terms) {
                TextView textView = (TextView) item.getActionView();
                // Estilo personalizado para o item de política de privacidade e termos de uso
                textView.setTextAppearance(this, R.style.Theme_Toolbar_Terms);
            } /*else {
                // Se não for o último item, aplica o estilo padrão
                TextView textView = (TextView) item.getActionView();
                // Estilo padrão para os demais itens
                textView.setTextAppearance(this, R.style.NavigationDrawerText);
            }*/

            // Implementar aqui o código para tratar os cliques nos itens do Navigation Drawer

            return true;
        });

        navigationView.setItemTextAppearance(R.style.Theme_Toolbar_Terms);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
