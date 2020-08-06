/*
* título: Qual é o número?
* autor: Rafael Gontijo Sabino Neves
* data: 30/07/2020
*
* Explicação: projeto consiste em criar um aplicativo que gere um número aleatório entre 1 e 300
* para o usuário adivinha-lo
* */


package com.example.qualnumero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qualnumero.ui.main.JogoController;
import com.example.qualnumero.ui.main.MainFragment;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {
    JogoController jogoController;
    ConstraintLayout Layout;
    int corDefault;

    public MainActivity() {
        try {
            this.corDefault = 12980026; // Cor inicial convertida de hexa para int
            this.jogoController = new JogoController();
        }catch (RuntimeException e){
            this.jogoController.inicializaResposta();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }

    public void envia(View view){   //função chamada ao clicar no botão envia
        EditText palpite = (EditText) findViewById(R.id.palpite);
        TextView mensagem = (TextView) findViewById(R.id.mensagem);
        Button novaPartidaBtn = (Button) findViewById(R.id.novaPartida);

        if(palpite.getText().length()>0) {      //verifica se algum numero foi digitado
            mensagem.setText(jogoController.getMensagemProcessada(Integer.parseInt(palpite.getText().toString())));
            this.setLed(Integer.parseInt(palpite.getText().toString()));
            novaPartidaBtn.setVisibility(jogoController.getMostraNovoJogo());
        }
    }

    public void reiniciaPartida(View view){ //função para reiniciar partida
        TextView mensagem = (TextView) findViewById(R.id.mensagem);
        Button novaPartidaBtn = (Button) findViewById(R.id.novaPartida);
        this.resetLed();
        try {
            jogoController.inicializaResposta();
            novaPartidaBtn.setVisibility(jogoController.getMostraNovoJogo());
            mensagem.setText(" ");
        }catch (RuntimeException e){
            mensagem.setText("Erro");
            this.setLed(Integer.parseInt(e.getMessage()));
        }

    }

    public void setLed(int valor){
        this.setUnidade(valor%10);
        if (valor>9){
            this.setDezena((valor/10)%10);
        } else{
            this.setDezena(10); //valor para setar todos leds como invisivel
        }
        if (valor>99){
            this.setCentena((valor/100)%10);
        } else{
            this.setCentena(10); //valor para setar todos leds como invisivel
        }
    }

    public void resetLed(){
        this.setUnidade(10); //Valores que não estão entre 0 e 9 apagam todos os leds
        this.setDezena(10);
        this.setCentena(10);
    }

    public void setUnidade(int unidade){
        int[] posi = jogoController.visibilidadeLed(unidade); //Vetor com
        TextView[] leds = new TextView[7];
        leds[0] = (TextView) findViewById(R.id.unidadeTop);
        leds[1] = (TextView) findViewById(R.id.unidadeTopRight);
        leds[2] = (TextView) findViewById(R.id.unidadeBotRight);
        leds[3] = (TextView) findViewById(R.id.unidadeBot);
        leds[4] = (TextView) findViewById(R.id.unidadeBotLeft);
        leds[5] = (TextView) findViewById(R.id.unidadeTopLeft);
        leds[6] = (TextView) findViewById(R.id.unidadeMid);

        for (int i =0; i<7;i++){
            leds[i].setVisibility(posi[i]);
        }
    }

    public void setDezena(int dezena){
        int[] posi = jogoController.visibilidadeLed(dezena);
        TextView[] leds = new TextView[7];
        leds[0] = (TextView) findViewById(R.id.dezenaTop);
        leds[1] = (TextView) findViewById(R.id.dezenaTopRight);
        leds[2] = (TextView) findViewById(R.id.dezenaBotRight);
        leds[3] = (TextView) findViewById(R.id.dezenaBot);
        leds[4] = (TextView) findViewById(R.id.dezenaBotLeft);
        leds[5] = (TextView) findViewById(R.id.dezenaTopLeft);
        leds[6] = (TextView) findViewById(R.id.dezenaMid);

        for (int i =0; i<7;i++){
            leds[i].setVisibility(posi[i]);
        }
    }

    public void setCentena(int centena){
        int[] posi = jogoController.visibilidadeLed(centena);
        TextView[] leds = new TextView[7];
        leds[0] = (TextView) findViewById(R.id.centenaTop);
        leds[1] = (TextView) findViewById(R.id.centenaTopRight);
        leds[2] = (TextView) findViewById(R.id.centenaBotRight);
        leds[3] = (TextView) findViewById(R.id.centenaBot);
        leds[4] = (TextView) findViewById(R.id.centenaBotLeft);
        leds[5] = (TextView) findViewById(R.id.centenaTopLeft);
        leds[6] = (TextView) findViewById(R.id.centenaMid);

        for (int i =0; i<7;i++){
            leds[i].setVisibility(posi[i]);
        }
    }

    public void openColorPicker(View view) {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, this.corDefault, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                corDefault = color;
                TextView[] leds = new TextView[21];
                leds[0] = (TextView) findViewById(R.id.unidadeTop);
                leds[1] = (TextView) findViewById(R.id.unidadeTopRight);
                leds[2] = (TextView) findViewById(R.id.unidadeBotRight);
                leds[3] = (TextView) findViewById(R.id.unidadeBot);
                leds[4] = (TextView) findViewById(R.id.unidadeBotLeft);
                leds[5] = (TextView) findViewById(R.id.unidadeTopLeft);
                leds[6] = (TextView) findViewById(R.id.unidadeMid);
                leds[7] = (TextView) findViewById(R.id.dezenaTop);
                leds[8] = (TextView) findViewById(R.id.dezenaTopRight);
                leds[9] = (TextView) findViewById(R.id.dezenaBotRight);
                leds[10] = (TextView) findViewById(R.id.dezenaBot);
                leds[11] = (TextView) findViewById(R.id.dezenaBotLeft);
                leds[12] = (TextView) findViewById(R.id.dezenaTopLeft);
                leds[13] = (TextView) findViewById(R.id.dezenaMid);
                leds[14] = (TextView) findViewById(R.id.centenaTop);
                leds[15] = (TextView) findViewById(R.id.centenaTopRight);
                leds[16] = (TextView) findViewById(R.id.centenaBotRight);
                leds[17] = (TextView) findViewById(R.id.centenaBot);
                leds[18] = (TextView) findViewById(R.id.centenaBotLeft);
                leds[19] = (TextView) findViewById(R.id.centenaTopLeft);
                leds[20] = (TextView) findViewById(R.id.centenaMid);
                for(TextView led : leds){
                    led.setBackgroundColor(corDefault);
                }
            }
        });
        colorPicker.show();
    }
}