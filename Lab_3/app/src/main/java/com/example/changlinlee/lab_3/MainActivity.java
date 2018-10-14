package com.example.changlinlee.lab_3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;



public class MainActivity extends AppCompatActivity {
    private GameView mGameView;
    private Button btnStart;
    private final static int PIECE_NONE = 0;
    private final static int PIECE_BLUE = 1;
    private final static int PIECE_RED = 2;
    private final static int STATE_NOT_START =0;
    private final static int STATE_PLAYING = 1;
    private final static int STATE_BLUE_WIN = 2;
    private final static int STATE_RED_WIN = 3;
    private final static int STATE_DRAW_GAME = 4;
    private String TAG_GAME_STATE = "tagGameState";
    private String TAG_IS_BLUE_TURN = "tagIsBlueTurn";
    private String TAG_LINE_LEFT = "tagLineLeft";
    private String TAG_LINE_MIDDLE = "tagLineMiddle";
    private String TAG_LINE_RIGHT = "tagLineRight";
    private String TAG_WIN_LINE = "tagWinLine";

    private int[][] boardState = new int[3][3];
    private boolean[] hvWinLine = new boolean[8];
    private boolean isBlueTurn = true;
    private int gameState = STATE_NOT_START;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.wait_start);

        mGameView = (GameView) findViewById(R.id.mGameView);
        mGameView.setHandler(new Handler() {
            public void handleMessage(Message msg){
                if(gameState != STATE_PLAYING)
                    return;
                int posX = msg.getData().getInt(GameView.TAG_ON_TOUCH_X);
                int posY = msg.getData().getInt(GameView.TAG_ON_TOUCH_Y);
                inputPiece(posX, posY);
                mGameView.invalidate();
            }
        });


        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameState = STATE_PLAYING;
                if (isBlueTurn)
                    setTitle(R.string.turn_blue);
                else
                    setTitle(R.string.turn_red);
                btnStart.setVisibility(View.INVISIBLE);
                for (int i = 0; i < 8; i++)
                    hvWinLine[i] = false;
                mGameView.cleanAll();
                mGameView.invalidate();
            }

        });


    }


    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TAG_GAME_STATE, gameState);
        outState.putBoolean(TAG_IS_BLUE_TURN, isBlueTurn);
        outState.putIntArray(TAG_LINE_LEFT, boardState[0]);
        outState.putIntArray(TAG_LINE_MIDDLE, boardState[1]);
        outState.putIntArray(TAG_LINE_RIGHT, boardState[2]);
        outState.putBooleanArray(TAG_WIN_LINE, hvWinLine);
    }


    public void onRestoreInstanceState(Bundle savedInstanceState) {
        gameState = savedInstanceState.getInt(TAG_GAME_STATE, STATE_NOT_START);
        isBlueTurn = savedInstanceState.getBoolean(TAG_IS_BLUE_TURN, true);
        boardState[0] = savedInstanceState.getIntArray(TAG_LINE_LEFT);
        boardState[1] = savedInstanceState.getIntArray(TAG_LINE_MIDDLE);
        boardState[2] = savedInstanceState.getIntArray(TAG_LINE_RIGHT);
        hvWinLine = savedInstanceState.getBooleanArray(TAG_WIN_LINE);
        if (gameState == STATE_PLAYING) {
            btnStart.setVisibility(View.INVISIBLE);
            if (isBlueTurn)
                setTitle(R.string.turn_blue);
            else
                setTitle(R.string.turn_red);
        }
        else{
            btnStart.setVisibility(View.VISIBLE);
            switch (gameState) {
                case STATE_NOT_START:
                    setTitle(R.string.wait_start);
                    break;
                case STATE_BLUE_WIN:
                    setTitle(R.string.win_blue);
                    break;
                case STATE_RED_WIN:
                    setTitle(R.string.win_red);
                    break;
                case STATE_DRAW_GAME:
                    setTitle(R.string.draw_game);
                    break;
            }
        }
        mGameView.cleanAll();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardState[i][j] == PIECE_BLUE)
                    mGameView.setBlueCross(i, j);
                else if (boardState[i][j] == PIECE_RED)
                    mGameView.setRedCircle(i, j);
            }
        }
       for (int i =0; i < 8; i++)
            if (hvWinLine[i])
                mGameView.setWinLine(i);
        mGameView.invalidate();

    }

    public static boolean win=false;
    private void inputPiece(int posX, int posY) {


        if (boardState[posX][posY] != PIECE_NONE)
            return;
        if (isBlueTurn) {
            boardState[posX][posY] = PIECE_BLUE;
            mGameView.setBlueCross(posX, posY);
            isBlueTurn = false;
            judgement();
            if(!win){

                setTitle(R.string.turn_red);
            }

        }
        else {
            boardState[posX][posY] = PIECE_RED;
            mGameView.setRedCircle(posX, posY);

            isBlueTurn = true;
            judgement();
            if(!win){
                setTitle(R.string.turn_blue);
            }
        }

        for(int i=0;i<3;i++){
            for(int u=0;u<3;u++){
                if(boardState[i][u]==0){
                    return;
                }
            }
        }


        setTitle(R.string.draw_game);
        gameState=STATE_DRAW_GAME;
        btnStart.setVisibility(View.VISIBLE);

    }
    public void judgement() {
        for (int k = 0; k < 3; k++) {

            if (boardState[k][0] == boardState[k][1] && boardState[k][1] == boardState[k][2]) {
                if (boardState[k][0] == 1) {
                    setTitle(R.string.win_blue);
                    gameState = STATE_BLUE_WIN;
                    win = true;
                }

                if (boardState[k][0] == 2) {
                    setTitle(R.string.win_red);
                    gameState = STATE_RED_WIN;
                    win = true;
                }
            }

        }

        for (int j = 0; j < 3; j++) {
            if (boardState[0][j] == boardState[1][j] && boardState[1][j] == boardState[2][j]) {
                if (boardState[0][j] == 1) {
                    setTitle(R.string.win_blue);
                    gameState = STATE_BLUE_WIN;

                    win = true;
                }
                if (boardState[0][j] == 2) {
                    setTitle(R.string.win_red);
                    gameState = STATE_RED_WIN;
                    win = true;
                }
            }

        }

        if (boardState[0][0] == boardState[1][1] && boardState[1][1] == boardState[2][2]) {
            if (boardState[0][0] == 1) {
                setTitle(R.string.win_blue);
                gameState = STATE_BLUE_WIN;

                win = true;
            }
            if (boardState[0][0] == 2) {
                setTitle(R.string.win_red);
                gameState = STATE_RED_WIN;
                win = true;
            }
        }

        if (boardState[0][2] == boardState[1][1] && boardState[1][1] == boardState[2][0]) {
            if (boardState[0][2] == 1) {
                setTitle(R.string.win_blue);
                gameState = STATE_BLUE_WIN;

                win = true;
            }
            if (boardState[0][2] == 2) {
                setTitle(R.string.win_red);
                gameState = STATE_RED_WIN;
                win = true;
            }
        }

        if (win) {
            btnStart.setVisibility(View.VISIBLE);
            return;
        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
        }



}
