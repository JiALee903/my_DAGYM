package org.techtown.dagym;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.techtown.dagym.entity.dto.BoardListResponseDto;
import org.techtown.dagym.session.SharedPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    DataService dataService = new DataService();
//    ArrayList<BoardListResponseDto> mlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.nav_home, R.id.nav_calendar, R.id.nav_inbody, R.id.nav_timer, R.id.nav_board, R.id.nav_chat)
                        .setDrawerLayout(drawer)
                        .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        dataService.select.selectBoard().enqueue(new Callback<ArrayList<BoardListResponseDto>>() {
//            @Override
//            public void onResponse(Call<ArrayList<BoardListResponseDto>> call, Response<ArrayList<BoardListResponseDto>> response) {
//                Log.i("TAG", "onResponse: array test = " + response.body().get(0).getTitle());
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<BoardListResponseDto>> call, Throwable t) {
//
//            }
//        });

    }

//    public ArrayList<BoardListResponseDto> getArrayList() {
//        return mlist;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mypage) {
            Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
            startActivity(intent);
        }

        if(id == R.id.action_logout) {
            SharedPreference.removeAllAttribute(getApplicationContext());
            Intent intent = new Intent(getApplicationContext(), LoginListActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

//    public void replaceFragment(Object o) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.text, (Fragment) BoardFragment).commit();
//    }
}