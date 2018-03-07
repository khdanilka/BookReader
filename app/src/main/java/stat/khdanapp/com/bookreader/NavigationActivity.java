package stat.khdanapp.com.bookreader;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static Integer LOGIN_STATE = 1;

    private String CHECKED_NAV_MENU = "checked_nav_menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (LOGIN_STATE == 0) {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Intent intent = getIntent();
        int itemId = -1;
        if (intent != null) {
            itemId = intent.getIntExtra(CHECKED_NAV_MENU,-1);
            if (itemId != -1) navigationView.getMenu().getItem(itemId).setChecked(true);
        }

        setNavMenuColors(navigationView);
    }

    private void setNavMenuColors(NavigationView navigationView){
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked}, // default
                new int[]{android.R.attr.state_checked}  // checked
        };

        int[] colors = new int[]{
                Color.GREEN,
                Color.BLUE
        };

        ColorStateList color_menu_item = new ColorStateList(states, colors);
        navigationView.setItemTextColor(color_menu_item);
        navigationView.setItemIconTintList(color_menu_item);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_3dots, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Class layot_id = null;        //setTitle(R.string.catalog);
        int id = item.getItemId();
        int menuOrderId = -1;

        switch (id){
            case (R.id.nav_catalog):
                layot_id = CatalogBookActivity.class;
                menuOrderId = 0;
                break;
            case (R.id.nav_my_book):
                layot_id = MyBooksActivity.class;
                menuOrderId = 1;
                break;
            case (R.id.nav_share):
                break;
            case (R.id.nav_settings):
                break;
            default:
                throw new RuntimeException("чтото пошло не так");
        }

        if (layot_id != null)  {
            item.setChecked(true);
            activityNavigationCreate(layot_id,menuOrderId);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void activityNavigationCreate(Class layout_id, int item){

        Intent intent = new Intent(this,layout_id);
        intent.putExtra(CHECKED_NAV_MENU,item);
        startActivity(intent);
        overridePendingTransition(0,0);
        finish();
    }

    private void fragmentCreate(Class fragmentClass, MenuItem item){

        android.support.v4.app.Fragment fragment = null;
        if (fragmentClass!= null) {
            try {
                fragment = (android.support.v4.app.Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_content, fragment).commit();
            if (item == null) setTitle(R.string.catalog);
            else setTitle(item.getTitle());

        }
    }

}
