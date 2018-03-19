package stat.khdanapp.com.bookreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    private int radioButtonNumberChecked = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button button = findViewById(R.id.allow_theme_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioButtonNumberChecked!=-1) {
                    finish();
                }
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.simple_radio:
                if (checked)
                    // Pirates are the best
                    radioButtonNumberChecked = NavigationActivity.SIMPLE_THEME;
                    break;
            case R.id.orange_radio:
                if (checked)
                    // Ninjas rule
                    radioButtonNumberChecked = NavigationActivity.ORANGE_THEME;
                    break;
            case R.id.braun_radio:
                if (checked)
                    // Ninjas rule
                    radioButtonNumberChecked = NavigationActivity.BRAUN_THEME;
                    break;
        }
    }
}
