package com.example.inicio;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inicio.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class pomodoroActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final long START_TIME_IN_MILLIS = 0;
    private EditText num1;

    private TextView indicaciones;
    private TextView mTextViewCountDown;

    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;
    private String mensaje ="¡Felicidades Terminaste!";
    long x=0;
    long y=0;

    long o=0;

    long d=0;
    int number1=0;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    ImageButton btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(R.layout.activity_pomodoro);
        getSupportActionBar().hide(); //esconde el titulo de la app para usar toda la pantalla

        FloatingActionButton fButton = findViewById(R.id.fab);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), habitosActivity.class));

            }
        });

        btnRegresar=(ImageButton)findViewById(R.id.regreso_pomo);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);
        indicaciones = findViewById(R.id.text_view_indicaciones);

        btnRegresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
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

        BottomNavigationView navPomo = findViewById(R.id.bottomNavigationView);
        navPomo.setSelectedItemId(R.id.navigation_pomo);
        navPomo.setBackground(null);

        navPomo.setOnItemSelectedListener(item->{
            if(item.getItemId()==R.id.navigation_rut){
                finish();
                startActivity(new Intent(getApplicationContext(), RutinaActivity.class));
                return true;
            } else if (item.getItemId()==R.id.navigation_home) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                return true;
            }
            else if (item.getItemId()==R.id.navigation_usuario) {
                finish();
                startActivity(new Intent(getApplicationContext(), usuarioActivity.class));

                return true;
            }


            return true;
        });
    }

    private void startTimer() {
            if(y>0){
                x=y;
            }
            else {
                num1 = (EditText) findViewById(R.id.ingresaMinutos);

                number1 = Integer.parseInt(num1.getText().toString());
                x = Long.valueOf(number1);
                x = x * 30000;
                d= (x/(3));
                o=x;
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
                    mCountDownTimer = new CountDownTimer(d, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            x = millisUntilFinished;
                            updateCountDownText();
                        }

                        @Override
                        public void onFinish() {
                            mCountDownTimer = new CountDownTimer(o, 1000) {
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
                                    indicaciones.setTextColor(Color.WHITE);
                                    //aqui se puede agregar una booleana para descanso y cuando salga la boleana dar felicitaciones
                                }
                            }.start();
                            indicaciones.setText("Concentrate");
                            indicaciones.setTextColor(Color.RED);
                            mTimerRunning = true;
                            mButtonStartPause.setText("Pause");
                            mButtonReset.setVisibility(View.INVISIBLE);
                            //aqui se puede agregar una booleana para descanso y cuando salga la boleana dar felicitaciones
                        }
                    }.start();
                    indicaciones.setText("Descansa");
                    indicaciones.setTextColor(Color.GREEN);
                    mTimerRunning = true;
                    mButtonStartPause.setText("Pause");
                    mButtonReset.setVisibility(View.INVISIBLE);
                    //aqui se puede agregar una booleana para descanso y cuando salga la boleana dar felicitaciones
                }
            }.start();
            indicaciones.setText("Concentrate");
            indicaciones.setTextColor(Color.RED);
            mTimerRunning = true;
            mButtonStartPause.setText("Pause");
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
        indicaciones.setText("Ingresa los minutos de concentracion");
        indicaciones.setTextColor(Color.WHITE);
        x = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
        num1.setVisibility(View.VISIBLE);
        mTextViewCountDown.setTextSize(60);
        x=0;
        y=0;
        num1.setText("");
    }

    private void updateCountDownText() {
        int minutes = (int) (x / 1000) / 60;
        int seconds = (int) (x / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);

    }


}