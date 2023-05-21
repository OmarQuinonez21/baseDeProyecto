package com.example.inicio;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.inicio.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.InputMismatchException;
import java.util.Locale;

public class pomodoroActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final long START_TIME_IN_MILLIS = 600000;
    private EditText num1;

    private TextView mTextViewCountDown;

    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;
    private String mensaje ="¡Felicidades Terminaste!";
long x=0;
long y=0;
int number1=0;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(R.layout.activity_pomodoro);
        getSupportActionBar().hide(); //esconde el titulo de la app para usar toda la pantalla



        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
    }

    private void startTimer() {
            if(y>0){
                x=y;
            }
            else {
                num1 = (EditText) findViewById(R.id.ingresaMinutos);
                number1 = Integer.parseInt(num1.getText().toString());
                x = Long.valueOf(number1);
                x = x * 60000;
            }

            num1.setVisibility(View.INVISIBLE);
            mCountDownTimer = new CountDownTimer(x, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    x = millisUntilFinished;
                    updateCountDownText();
                }

                @Override
                public void onFinish() {
                    mTimerRunning = false;
                    mButtonStartPause.setText("Start");
                    mButtonStartPause.setVisibility(View.INVISIBLE);
                    mButtonReset.setVisibility(View.VISIBLE);

                    mTextViewCountDown.setTextSize(30);
                    mTextViewCountDown.setText(mensaje);
                    //aqui se puede agregar una booleana para descanso
                }
            }.start();

            mTimerRunning = true;
            mButtonStartPause.setText("pause");
            mButtonReset.setVisibility(View.INVISIBLE);
        }


    private void pauseTimer() {
        y=x;
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        x = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
        num1.setVisibility(View.VISIBLE);
        mTextViewCountDown.setTextSize(60);
        x=0;
        y=0;
    }

    private void updateCountDownText() {
        int minutes = (int) (x / 1000) / 60;
        int seconds = (int) (x / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);

    }


}