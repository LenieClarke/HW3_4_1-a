package com.example.hw3_4_1_a;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    String language;
    private static @StyleRes int theme = R.style.AppTheme_BlackTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(theme);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initLanguage();
        initTheme();

        Button okBtn = findViewById(R.id.okBtn);
        LinearLayout backdrop = findViewById(R.id.backdrop);
        TextView showText = findViewById(R.id.showText);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = new Locale(language);
                Configuration configuration = new Configuration();
                configuration.setLocale(locale);
                getResources().updateConfiguration(configuration,
                        getBaseContext().getResources().getDisplayMetrics());
                recreate();
            }
        });

        switch (theme) {
            case R.style.AppTheme_BlackTheme:
                backdrop.setBackgroundColor(getResources().getColor(R.color.colorBackdropBlack));
                showText.setTextColor(getResources().getColor(R.color.colorPrimaryDarkBlack));
                okBtn.setBackgroundColor(getResources().getColor(R.color.colorAccentBlack));
                break;
            case R.style.AppTheme_GreenTheme:
                backdrop.setBackgroundColor(getResources().getColor(R.color.colorBackdropGreen));
                showText.setTextColor(getResources().getColor(R.color.colorPrimaryDarkGreen));
                okBtn.setBackgroundColor(getResources().getColor(R.color.colorAccentGreen));
                break;
            case R.style.AppTheme_BlueTheme:
                backdrop.setBackgroundColor(getResources().getColor(R.color.colorBackdropBlue));
                showText.setTextColor(getResources().getColor(R.color.colorPrimaryDarkBlue));
                okBtn.setBackgroundColor(getResources().getColor(R.color.colorAccentBlue));
                break;
        }
    }

    private void initLanguage() {
        Spinner languageSpinner = findViewById(R.id.languageSpinner);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().getStringArray(R.array.languages);
                language = languages[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initTheme() {
        Spinner themeSpinner = findViewById(R.id.themeSpinner);

        ArrayAdapter<?> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.themes, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSpinner.setAdapter(arrayAdapter);

        themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        theme = R.style.AppTheme_BlackTheme;
                        break;
                    case 1:
                        theme = R.style.AppTheme_GreenTheme;
                        break;
                    case 2:
                        theme = R.style.AppTheme_BlueTheme;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}