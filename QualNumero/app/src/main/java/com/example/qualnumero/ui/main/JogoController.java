package com.example.qualnumero.ui.main;


import android.os.StrictMode;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class JogoController {
    private int resposta;
    private int mostraNovoJogo;
    private int cdErro;

    public JogoController() {
        this.mostraNovoJogo = View.INVISIBLE;
        cdErro = 0;
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            this.inicializaResposta();
        }
        catch (RuntimeException e){
            throw e;
        }
    }

    public void inicializaResposta(){
        try {
            HttpService retorno = new HttpService();
            JSONObject retornoJson = new JSONObject(retorno.doInBackground());
            this.resposta = retornoJson.getInt("value");
            this.mostraNovoJogo = View.INVISIBLE;
            System.out.println(retornoJson.getInt("value"));
        }
        catch (RuntimeException e){
            this.mostraNovoJogo = View.VISIBLE;
            throw e;
        }
        catch (JSONException e) {
            this.mostraNovoJogo = View.VISIBLE;
            System.out.println("ENTROU NA EXCEPTION"+e.getMessage());
            //e.printStackTrace();
        }

    }

    public String getMensagemProcessada(int palpite){
        String msgRetorno = "";
        if(palpite == this.resposta) {
            msgRetorno =  "Acertou!";
            this.mostraNovoJogo = View.VISIBLE;
        }
        if(palpite > this.resposta){
            msgRetorno = "É menor";
        }
        if(palpite < this.resposta){
            msgRetorno = "É maior";
        }
        return  msgRetorno;
    }

    public int[] visibilidadeLed(int num){
        int visibilidadePos[] = new int[7];
        for (int i=0;i < 7;i++){
            visibilidadePos[i]=View.INVISIBLE;
        }
        switch (num) {
            case 1:
                visibilidadePos[1] = View.VISIBLE;
                visibilidadePos[2] = View.VISIBLE;
                break;
            case 2:
                visibilidadePos[0] = View.VISIBLE;
                visibilidadePos[1] = View.VISIBLE;
                visibilidadePos[6] = View.VISIBLE;
                visibilidadePos[4] = View.VISIBLE;
                visibilidadePos[3] = View.VISIBLE;
                break;
            case 3:
                visibilidadePos[0] = View.VISIBLE;
                visibilidadePos[1] = View.VISIBLE;
                visibilidadePos[6] = View.VISIBLE;
                visibilidadePos[2] = View.VISIBLE;
                visibilidadePos[3] = View.VISIBLE;
                break;
            case 4:
                visibilidadePos[1] = View.VISIBLE;
                visibilidadePos[2] = View.VISIBLE;
                visibilidadePos[5] = View.VISIBLE;
                visibilidadePos[6] = View.VISIBLE;
                break;
            case 5:
                visibilidadePos[0] = View.VISIBLE;
                visibilidadePos[2] = View.VISIBLE;
                visibilidadePos[3] = View.VISIBLE;
                visibilidadePos[5] = View.VISIBLE;
                visibilidadePos[6] = View.VISIBLE;
                break;
            case 6:
                visibilidadePos[0] = View.VISIBLE;
                visibilidadePos[2] = View.VISIBLE;
                visibilidadePos[3] = View.VISIBLE;
                visibilidadePos[4] = View.VISIBLE;
                visibilidadePos[5] = View.VISIBLE;
                visibilidadePos[6] = View.VISIBLE;
                break;
            case 7:
                visibilidadePos[0] = View.VISIBLE;
                visibilidadePos[1] = View.VISIBLE;
                visibilidadePos[2] = View.VISIBLE;
                break;
            case 8:
                visibilidadePos[0] = View.VISIBLE;
                visibilidadePos[1] = View.VISIBLE;
                visibilidadePos[2] = View.VISIBLE;
                visibilidadePos[3] = View.VISIBLE;
                visibilidadePos[4] = View.VISIBLE;
                visibilidadePos[5] = View.VISIBLE;
                visibilidadePos[6] = View.VISIBLE;
                break;
            case 9:
                visibilidadePos[0] = View.VISIBLE;
                visibilidadePos[1] = View.VISIBLE;
                visibilidadePos[2] = View.VISIBLE;
                visibilidadePos[3] = View.VISIBLE;
                visibilidadePos[5] = View.VISIBLE;
                visibilidadePos[6] = View.VISIBLE;
                break;
            case 0:
                visibilidadePos[0] = View.VISIBLE;
                visibilidadePos[1] = View.VISIBLE;
                visibilidadePos[2] = View.VISIBLE;
                visibilidadePos[3] = View.VISIBLE;
                visibilidadePos[4] = View.VISIBLE;
                visibilidadePos[5] = View.VISIBLE;
                break;
        }
        return visibilidadePos;
    }

    public int getMostraNovoJogo() {
        return mostraNovoJogo;
    }

    public void setMostraNovoJogo(int mostraNovoJogo) {
        this.mostraNovoJogo = mostraNovoJogo;
    }
}
