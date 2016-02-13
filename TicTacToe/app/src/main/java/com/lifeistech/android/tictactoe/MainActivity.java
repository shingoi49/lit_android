package com.lifeistech.android.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int PLAYER_COUNT = 2;
    private static final int[] PLAYER_IMAGES = {R.drawable.ic_batu,R.drawable.ic_maru};
    private int mTurn;
    private int[] mGameBoard;
    private ImageButton[] mBoardButtons;
    private TextView mPlayerTextView;
    private TextView mWinnerTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayerTextView = (TextView)findViewById(R.id.playerText);
        mWinnerTextView = (TextView)findViewById((R.id.winnerText));

        mBoardButtons = new ImageButton[9];
        int[] ButtonsIds = {
                R.id.imageButton1,
                R.id.imageButton2,
                R.id.imageButton3,
                R.id.imageButton4,
                R.id.imageButton5,
                R.id.imageButton6,
                R.id.imageButton7,
                R.id.imageButton8,
                R.id.imageButton9};
        for(int i = 0; i < mBoardButtons.length; i++){
            mBoardButtons[i] = (ImageButton)findViewById(ButtonsIds[i]);
            mBoardButtons[i].setTag(new Integer(i));
        }
        init();
        setPlayer();
    }
    private void init(){
        mTurn = 1;
        mGameBoard = new int[mBoardButtons.length];
        for(int i = 0; i < mBoardButtons.length; i++){
            mGameBoard[i] = -1;
            mBoardButtons[i].setImageBitmap(null);
        }
        mWinnerTextView.setVisibility(View.GONE);
    }
    private void setPlayer(){
        if(mTurn % 2 == 0){
            mPlayerTextView.setText("Player: ×(2)");
        }else{
            mPlayerTextView.setText("Player: ○(1)");
        }
    }
    public void tapMask(View v){
        if(mWinnerTextView.getVisibility() == View.VISIBLE)return;
        int buttonNumber = (Integer)v.getTag();
        if(mGameBoard[buttonNumber] == -1){
            mBoardButtons[buttonNumber].setImageResource(PLAYER_IMAGES[mTurn % 2]);
            mGameBoard[buttonNumber] = mTurn % 2;
            int judge = judgeGame();
            if(judge != -1){
                if (judge == 0){
                    mWinnerTextView.setText("Game End\nPlayer: ×(2)\nwin");
                    mWinnerTextView.setTextColor(Color.BLUE);
                }else{
                    mWinnerTextView.setText("Game End\nPlayer: ×(1)\nwin");
                    mWinnerTextView.setTextColor(Color.RED);
                }
                mWinnerTextView.setVisibility(View.VISIBLE);
            }else{
                if(mTurn >= mGameBoard.length){
                    mWinnerTextView.setText("Game End\ndraw");
                    mWinnerTextView.setTextColor(Color.GREEN);
                    mWinnerTextView.setVisibility(View.VISIBLE);
                }
                mTurn++;
                setPlayer();
            }
        }
    }
    private int judgeGame(){
        for(int i = 0; i < 3; i++){
            if(mGameBoard[i * 3] != -1 &&
                    mGameBoard[i * 3] == mGameBoard[i * 3 + 1] &&
                    mGameBoard[i * 3] == mGameBoard[1* 3 + 2]){
                return mGameBoard[i * 3];
            }
            if(mGameBoard[i] != -1 &&
                    mGameBoard[i] == mGameBoard[i + 3] &&
                    mGameBoard[i] == mGameBoard[1 + 6]){
                return mGameBoard[i];
            }
        }
        if(mGameBoard[0] != -1 &&
                mGameBoard[0] == mGameBoard[4] &&
                mGameBoard[0] == mGameBoard[8]){
            return mGameBoard[0];
        }
        if(mGameBoard[2] != -1 &&
                mGameBoard[2] == mGameBoard[4] &&
                mGameBoard[2] == mGameBoard[6]){
            return mGameBoard[2];
        }
        return -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            init();
            setPlayer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
