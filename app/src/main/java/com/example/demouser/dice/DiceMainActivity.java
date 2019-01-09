package com.example.demouser.dice;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class DiceMainActivity extends AppCompatActivity {

    //global variables for keeping track of scores
    private int userOverallScore = 0;
    private int userTurnScore = 0;
    private int compOverallScore;
    private int compTurnScore;

    //texts with scores and action
    private TextView actionText;
    private TextView compScoreText;
    private TextView userScoreText;

    //variables to be used
    private ImageView diceDrawable;
    private Resources res;
    private Button rollButton;
    private Button resetButton;
    private Button holdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_main);

        res = getResources();
        //diceDrawable = res.getDrawable(R.drawable.dice1);
        diceDrawable = findViewById(R.id.diceImage);

        //score textboxes
        userScoreText = findViewById(R.id.playerScore);
        compScoreText = findViewById(R.id.computerScore);
        actionText = findViewById(R.id.actionText);

        //button instantiate
        rollButton = findViewById(R.id.rollbutton);
        resetButton = findViewById(R.id.resetbutton);
        holdButton = findViewById(R.id.holdbutton);

        rollButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                userTurnScore = diceRoll("player");
            }

        });

        // reset button sets all to 0 and updates displays
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reset global variables for keeping track of scores
                userOverallScore = 0;
                userTurnScore = 0;
                compOverallScore = 0;
                compTurnScore = 0;
                compScoreText.setText("Computer Score: "+ compOverallScore);
                userScoreText.setText("Your Score: " + userOverallScore);
                actionText.setText("");
            }
        });

        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionText.setText("You choose to hold");
                userOverallScore = userOverallScore + userTurnScore;
                endTurn("player");
            }

        });

    }

    /**
     * Randomly chooses number between 1-6 and changes dice image associated with roll
     * @param turn either "computer" or "user" must be passed in
     */
    public int diceRoll(String turn){
        int turnScore = 0;
        String turnText = "";

        if (turn.equals("player")){
            turnScore = userTurnScore;
            turnText = "Your turn score: ";
        }
        else if (turn.equals("computer")){
            turnScore = compTurnScore;
            turnText = "Computer turn score: ";
        }

        // random num generator for dice
        Random rand = new Random();

        // on click, random number is generated 1-6
        int diceNum = rand.nextInt(6) + 1;

        // make cases for all possible rolls
        switch (diceNum) {
            case 1:
                //drawable changes to dice1 image
                diceDrawable.setImageResource(R.drawable.dice1);
                turnScore = 0;
                actionText.setText("Rolled a 1!");

                // end turn here, cannot roll again
                if (turn.equals("player")){
                    userOverallScore = userOverallScore + turnScore;
                    endTurn("player");
                }
                else if (turn.equals("computer")){
                    compOverallScore = compOverallScore + turnScore;
                    endTurn("computer");
                }

                return turnScore;

            case 2:
                //drawable changes to dice2 image
                diceDrawable.setImageResource(R.drawable.dice2);
                turnScore = turnScore + 2;
                actionText.setText(turnText + turnScore);
                return turnScore;

            case 3:
                //drawable changes to dice3 image
                diceDrawable.setImageResource(R.drawable.dice3);
                turnScore = turnScore + 3;
                actionText.setText(turnText +turnScore);
                return turnScore;

            case 4:
                //drawable changes to dice4 image
                diceDrawable.setImageResource(R.drawable.dice4);
                turnScore = turnScore + 4;
                actionText.setText(turnText + turnScore);
                return turnScore;

            case 5:
                //drawable changes to dice5 image
                diceDrawable.setImageResource(R.drawable.dice5);
                turnScore = turnScore + 5;
                actionText.setText(turnText + turnScore);
                return turnScore;

            case 6:
                //drawable changes to dice6 image
                diceDrawable.setImageResource(R.drawable.dice6);
                turnScore = turnScore + 6;
                actionText.setText(turnText + turnScore);
                return turnScore;

        }
        return 0;
    }

    /**
     * Ends the turn of the user and switches to the computer's
     * @param turn String to determine what to pass in: must be "player" or "computer"
     */
    public void endTurn(String turn){

        if (turn.equals("player")){
            userScoreText.setText("Player Score: " + userOverallScore);

            //prompt computer to roll dice
            computerTurn();
        }
        else if (turn.equals("computer")){
            compScoreText.setText("Computer Score: updated " + compOverallScore);
        }

        //reset turn scores for both
        userTurnScore = 0;
        compTurnScore = 0;
    }

    /**
     * Ends the turn of the user and switches to the computer's
     */
    public void computerTurn(){
        // disable roll and hold buttons
        rollButton.setEnabled(false);
        holdButton.setEnabled(false);

        // random gen for whether computer keeps going
        final Handler handler = new Handler();

        Runnable r = new Runnable(){
            // random generator to determine if computer will roll dice
            Random rand = new Random();

            // start at 1, so that the computer will roll at least once to begin with
            int nextInt = 1;

            @Override
            public void run (){
                // 1/4 chance for computer to keep rolling
                if (nextInt>0){

                    //computer rolls dice and updates display
                    compTurnScore = diceRoll("computer");
                    actionText.setText("Computer rolled a " + compTurnScore);

                    // get a new int to determine if the computer will roll again
                    nextInt = rand.nextInt(5);

                    // recursive call to run method
                    handler.postDelayed(this, 1000);

                }
            }
        };
        // start call into run function
        handler.postDelayed(r, 1000);

        // after computer done rolling, update its score and in display and global variables
        compOverallScore = compOverallScore + compTurnScore;

        //compScoreText.setText("Computer Score: " + compTurnScore);
        endTurn("computer");

        // enable for player again
        rollButton.setEnabled(true);
        holdButton.setEnabled(true);
        actionText.setText("Your turn again");
    }

}
