package com.example.demouser.dice;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class DiceMainActivity extends AppCompatActivity {

    //global variables for keeping track of scores
    private int userOverallScore;
    private int userTurnScore;
    private int compOverallScore;
    private int compTurnScore;

    //variables to be used
    private ImageView diceDrawable;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_main);

        res = getResources();
        //diceDrawable = res.getDrawable(R.drawable.dice1);
        diceDrawable = findViewById(R.id.diceImage);

        //score textboxes

        TextView userScoreText = findViewById(R.id.playerScore);
        TextView compScoreText = findViewById(R.id.computerScore);
        TextView actionText = findViewById(R.id.actionText);

        //button instantiate
        Button rollButton = findViewById(R.id.rollbutton);
        Button resetButton = findViewById(R.id.resetbutton);
        Button holdButton = findViewById(R.id.holdbutton);

        rollButton.setOnClickListener(new View.OnClickListener(){
            Random rand = new Random();
            @Override
            public void onClick(View v){
                // on click, random number is generated 1-6
                int diceNum = rand.nextInt(6) + 1;

                // make cases for all possible rolls
                switch (diceNum) {
                    case 1:
                        //drawable changes to dice1 image
                        diceDrawable.setImageResource(R.drawable.dice1);
                        //turnScore = 0
                        break;
                    case 2:
                        //drawable changes to dice2 image
                        diceDrawable.setImageResource(R.drawable.dice2);
                        //turnScore = turnScore + 2
                        break;
                    case 3:
                        //drawable changes to dice3 image
                        diceDrawable.setImageResource(R.drawable.dice3);
                        //turnScore = turnScore + 3
                        break;
                    case 4:
                        //drawable changes to dice4 image
                        diceDrawable.setImageResource(R.drawable.dice4);
                        //turnScore = turnScore + 4
                        break;
                    case 5:
                        //drawable changes to dice5 image
                        diceDrawable.setImageResource(R.drawable.dice5);
                        //turnScore = turnScore + 5
                        break;
                    case 6:
                        //drawable changes to dice6 image
                        diceDrawable.setImageResource(R.drawable.dice6);
                        //turnScore = turnScore + 6
                        break;
                }
                // changes dice drawable to associated image
            }
        });
    }
}
