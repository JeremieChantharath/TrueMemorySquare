package com.example.chanthaj.truememorysquare;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FullscreenActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private ArrayList<Integer> userEntries;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        this.game = new Game();
        this.userEntries = new ArrayList<>();


        generateWithDelay();
    }

    public void printInfos(){

        TextView score = (TextView)findViewById(R.id.score);
        score.setText("Score :" + this.game.getScore());

        TextView level = (TextView)findViewById(R.id.level);
        level.setText("Level :" + this.game.getLevel());

        TextView levelLife = (TextView)findViewById(R.id.levelLife);
        levelLife.setText("LevelLife :" + this.game.getLevelLife());

        TextView life = (TextView)findViewById(R.id.life);
        life.setText("Life :" + this.game.getLife());
    }

    public void generateWithDelay(){

        generateButtonsToMemorize(this.game.getNumButtons());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                generateButtons(game.getNumButtons());
            }
        }, 3000);
    }

    public void generateButtons(int numButtons) {

        int buttonId = 1;
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativelayout);
        rl.removeAllViews();

        //Ca ça sert à placer les boutons sur une ligne
        for (int i = 1; i <= numButtons; i++) {
            //Ca ça sert à les placer sur une colonne
            for (int j = 1; j <= numButtons; j++) {

                Button btn = new Button(this);
                btn.setId(buttonId);

                //Couleur de base
                btn.setBackgroundColor(Color.rgb(70, 80, 90));

                //Créer l'objet pour les paramètres du bouton
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                //methode perso, donne les dimensions au bouton
                setButtonsParams(p);

                //Verifie si le bouton est le premier de la colonne, s'il ne l'est pas on place ce bouton à DROITE du bouton précédent
                if (!isInFirstColomn(buttonId, numButtons))
                    p.addRule(RelativeLayout.RIGHT_OF, buttonId - 1);
                //Vérifie si le bouton est sur la première ligne, s'il ne l'est pas, place le bouton en dessous du bouton censé être juste au dessus de lui
                if(i>1)
                    p.addRule(RelativeLayout.BELOW, buttonId - numButtons);

                //Donne les paramètres au bouton
                btn.setLayoutParams(p);


                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Log.v(TAG,"*********************** NEW BUTTON PRESSED *********************** ");

                        Log.v(TAG,"PRESSED " + view.getId());
                        Log.v(TAG," SUITE " + game.getStringSuite());

                        Log.v(TAG," USER ENTRIES " + game.getLife());

                        Log.v(TAG,"LEVEL " + game.getLevel());
                        Log.v(TAG,"LEVEL LIFE " + game.getLevelLife());
                        Log.v(TAG," LIFE " + game.getLife());


                        // un essai nécessaire
                        view.setClickable(false);

                        //En cas de bonne réponse
                        if(game.verifCarre(view.getId())){
                            //Ajoute à la liste des réponses du joueur
                            userEntries.add(view.getId());
                            view.setBackgroundColor(Color.rgb(70, 200, 70));

                            if(game.nextLevel(userEntries))
                            {
                                game.setLevel(game.getLevel()+1);
                                userEntries.clear();
                                game.createSuite();
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        generateWithDelay();
                                    }
                                }, 1000);
                            Toast.makeText(getApplicationContext(),"Bien joué !",Toast.LENGTH_SHORT).show();
                            }

                         //En cas de mauvaise
                        }else{
                            view.setBackgroundColor(Color.rgb(200, 0, 0));
                            game.setLevelLife(game.getLevelLife()-1);
                            if(game.levelLost())
                            {
                                game.setLevel(game.getLevel()-1);
                                game.setLife(game.getLife()-1);
                                if(game.gameOver())
                                {
                                    Intent intent = new Intent(getApplicationContext(), GameOver.class);
                                    startActivity(intent);
                                }
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        generateWithDelay();
                                    }
                                }, 1000);
                                Toast.makeText(getApplicationContext(),"Dommage..",Toast.LENGTH_SHORT).show();
                            }

                        }
                        printInfos();
                    }
                });
                //Recupère la zone blanche du screen
                //Place le bouton dans la zone blanche là ou il devrait être
                rl.addView(btn);
                //Augmente la variable id de 1 (le premier bouton aura 0 pour id, le 2e aura 1, etc)
                buttonId++;
            }
        }
    }

    //TODO faire les generate comme il faut
    public void generateButtonsToMemorize(int numButtons) {

        int buttonId = 1;

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativelayout);
        rl.removeAllViews();

        for (int i = 1; i <= numButtons; i++) {
            for (int j = 1; j <= numButtons; j++) {
                Button btn = new Button(this);
                btn.setId(buttonId);
                //Si fait partit de la suite rouge, sinon comme les autres
                if(this.game.getSuite().contains(buttonId))
                    btn.setBackgroundColor(Color.rgb(70, 0, 0));
                else
                    btn.setBackgroundColor(Color.rgb(70, 80, 90));

                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                //On ne peut pas commencer à jouer avant le début
                btn.setClickable(false);
                setButtonsParams(p);
                if (!isInFirstColomn(buttonId, numButtons))
                    p.addRule(RelativeLayout.RIGHT_OF, buttonId - 1);
                if(i>1)
                    p.addRule(RelativeLayout.BELOW, buttonId - numButtons);
                btn.setLayoutParams(p);
                rl.addView(btn);
                buttonId++;
            }
        }
    }


    public void setButtonsParams(RelativeLayout.LayoutParams p) {
        p.leftMargin = 50;
        p.topMargin = 50;
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


