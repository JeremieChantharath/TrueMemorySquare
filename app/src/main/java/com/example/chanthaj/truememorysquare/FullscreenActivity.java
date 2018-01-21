package com.example.chanthaj.truememorysquare;


import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private String userEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        Game game = new Game();

        int dp = 25;
        int px = dpToPx(25);
        System.out.println(px);
        generateButtons(game.getNumButtons());


    }

    public void generateButtons(int numButtons) {

        //Ca c'est l'id du button
        int buttonId = 1;

        //Ca ça sert à placer les boutons sur une ligne
        for (int i = 1; i <= numButtons; i++) {
            //Ca ça sert à les placer sur une colonne
            for (int j = 1; j <= numButtons; j++) {

                //Ca créer un bouton
                Button btn = new Button(this);
                //Donne l'id au bouton, pour ensuite pouvoir le différencier des autres, chaque boutons aura un id différent
                btn.setId(buttonId);

                //Donne une couleur au bouton (osef)
                btn.setBackgroundColor(Color.rgb(70, 80, 90));

                //Créer les paramètres que l'on donnera au bouton
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                //Donne les dimensions au bouton (ils auront tous la même taille)
                setButtonsParams(p, i, j);
                //Verifie si le bouton est le premier de la colonne, s'il ne l'est pas on place ce bouton à DROITE du bouton précédent
                if (!isInFirstColomn(buttonId, numButtons))
                    p.addRule(RelativeLayout.RIGHT_OF, buttonId - 1);
                //Vérifie si le bouton est sur la première ligne, s'il ne l'est pas, place le bouton en dessous du bouton censé être juste au dessus de lui
                if(i>1)
                    p.addRule(RelativeLayout.BELOW, buttonId - numButtons);

                //Donne les paramètres au bouton
                btn.setLayoutParams(p);

                //OSEf on s'en sert pas
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        view.setBackgroundColor(Color.rgb(70, 0, 0));
                        Log.v(TAG,Integer.toString(view.getId()));

                    }
                });
                //Recupère la zone blanche du screen
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativelayout);
                //Place le bouton dans la zone blanche là ou il devrait être
                rl.addView(btn);
                //Augmente la variable id de 1 (le premier bouton aura 0 pour id, le 2e aura 1, etc)
                Log.v(TAG,Integer.toString(buttonId));
                buttonId++;
            }
        }
    }

    public void setButtonsParams(RelativeLayout.LayoutParams p, int i, int j) {
        p.leftMargin = 50;
        p.topMargin = 50 ;
        p.width = 150;
        p.height = 150;
    }

    public boolean isInFirstColomn(int buttonId, int numberOfButtons) {
        return buttonId == 1 || buttonId % numberOfButtons == 1;
    }

    public int dpToPx(int dp){
        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return Float.floatToIntBits(px);
    }

}


