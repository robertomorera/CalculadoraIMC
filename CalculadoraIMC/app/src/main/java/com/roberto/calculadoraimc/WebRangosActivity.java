package com.roberto.calculadoraimc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Clase que prepara la pantalla para presentar
 * los datos de IMC obtenidos desde una URL.
 */
public class WebRangosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_rangos);
        //Obtenemos la referencia al webView.
        WebView webViewRangos=(WebView)findViewById(R.id.webViewRangos);
        //Asociamos el cliente por defecto.
        webViewRangos.setWebViewClient(new WebViewClient());
        //Habilitamos el JavaScript en el navegador.
        webViewRangos.getSettings().setJavaScriptEnabled(true);
        //Cargamos una URL.
        webViewRangos.loadUrl("https://www.cdc.gov/healthyweight/spanish/assessing/");

    }
}
