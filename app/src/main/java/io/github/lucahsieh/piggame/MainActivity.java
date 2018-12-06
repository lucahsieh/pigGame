package io.github.lucahsieh.piggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected int player1Score;
    protected int player2Score;
    protected int turnSum;
    protected int curPlayer;
    protected ImageView dice1;
    protected ImageView dice2;
    protected TextView player1Status;
    protected TextView player2Status;
    protected TextView status;
    protected TextView rule;
    protected Button rollBtn;
    protected Button holdBtn;
    protected Button restartBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        player1Score = 0;
        player2Score = 0;
        dice1 = (ImageView)findViewById(R.id.dice1);
        dice2 = (ImageView)findViewById(R.id.dice2);
        rollBtn = (Button)findViewById(R.id.roll);
        holdBtn = (Button)findViewById(R.id.hold);
        restartBtn = (Button)findViewById(R.id.restart);
        player1Status = (TextView)findViewById(R.id.player1);
        player2Status = (TextView)findViewById(R.id.player2);
        rule = (TextView)findViewById(R.id.rule);
        status = (TextView)findViewById(R.id.status);
        curPlayer = 1;
        renderScore();
        rollBtn.setVisibility(View.VISIBLE);
        holdBtn.setVisibility(View.VISIBLE);
        restartBtn.setVisibility(View.GONE);
        rule.setVisibility(View.VISIBLE);
        status.setText("Player1's turn");
    }

    private void takeTurn(){
        curPlayer = (curPlayer == 1) ? 2 : 1;
        renderScore();
    }

    public void restart(View view){
        init();
    }

    public void rollDice(View view) {
        int ran1 = (int) (Math.random() * 6) + 1;
        int ran2 = (int) (Math.random() * 6) + 1;
        renderDice(ran1, dice1);
        renderDice(ran2, dice2);

        if (ran1 == 1 || ran2 == 1) {
            turnSum = 0;
            status.setText("player" + curPlayer + " rolled the skull!");
            takeTurn();
        } else {
            turnSum += ran1 + ran2;
            status.setText(turnSum + "");
        }
    }

    public void hold(View view){
        if(curPlayer == 1)
            player1Score += turnSum;
        if(curPlayer == 2)
            player2Score += turnSum;
        turnSum = 0;

        if(checkWinner() == 0)
            takeTurn();
    }

    private void renderScore() {
        if(curPlayer == 1) {
            player1Status.setText("**player1: " + player1Score);
            player2Status.setText("  player2: " + player2Score);
        }else if(curPlayer == 2){
            player1Status.setText("  player1: " + player1Score);
            player2Status.setText("**player2: " + player2Score);
        }
    }

    public int checkWinner(){
        if(player1Score >= 100) {
            status.setText("Player1 wins!");
            rollBtn.setVisibility(View.GONE);
            holdBtn.setVisibility(View.GONE);
            restartBtn.setVisibility(View.VISIBLE);
            rule.setVisibility(View.GONE);
            renderScore();
            return 1;
        } else if(player2Score >= 100) {
            status.setText("Player2 wins!");
            rollBtn.setVisibility(View.GONE);
            holdBtn.setVisibility(View.GONE);
            restartBtn.setVisibility(View.VISIBLE);
            rule.setVisibility(View.GONE);
            renderScore();
            return 1;
        }
        return 0;
    }

    private void renderDice(int i, ImageView img) {
        switch(i){
            case 1:
                img.setImageResource(R.drawable.dice1);
                break;
            case 2:
                img.setImageResource(R.drawable.dice2);
                break;
            case 3:
                img.setImageResource(R.drawable.dice3);
                break;
            case 4:
                img.setImageResource(R.drawable.dice4);
                break;
            case 5:
                img.setImageResource(R.drawable.dice5);
                break;
            default:
                img.setImageResource(R.drawable.dice6);
                break;
        }
    }
}

