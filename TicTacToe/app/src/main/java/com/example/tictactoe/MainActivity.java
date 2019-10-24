package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.tictactoe.R;

public class MainActivity extends AppCompatActivity {

    static final String PLAYER_1_SYMBOL = "X";
    static final String PLAYER_2_SYMBOL = "O";
    boolean player1Turn = true;

    byte[][] board = new byte[3][3];

    static final byte EMPTY_VALUE = 0;
    static final byte PLAYER_1_VALUE = 1;
    static final byte PLAYER_2_VALUE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout table = findViewById(R.id.table);
        for (int row_index=0; row_index < 3 ; row_index++) {
            TableRow row = (TableRow) table.getChildAt(row_index);
            for (int col_index = 0; col_index < 3; col_index++) {
                Button button = (Button) row.getChildAt(col_index);
                button.setOnClickListener(new CellListener(row_index,col_index));
            }
        }


    }

    class CellListener implements View.OnClickListener
    {
        int row,col;
        public CellListener(int row,int col)
        {
            this.row = row;
            this.col = col;

        }
        @Override
        public void onClick(View v) {
            if (board[row][col] != EMPTY_VALUE) {
                Toast.makeText(MainActivity.this, " Cell is already occupied", Toast.LENGTH_SHORT).show();
                return;
            } else {
                byte playerValue = EMPTY_VALUE;
                if (player1Turn) {
                    ((Button) v).setText(PLAYER_1_SYMBOL);
                    board[row][col] = PLAYER_1_VALUE;
                    playerValue = PLAYER_1_VALUE;
                } else {
                    ((Button) v).setText(PLAYER_2_SYMBOL);
                    board[row][col] = PLAYER_2_VALUE;
                    playerValue = PLAYER_2_VALUE;
                }
                int gameState = gameEnded(row, col, playerValue);
                if (gameState > 0)
                {
                    Toast.makeText(MainActivity.this, " Game is won by " + playerValue, Toast.LENGTH_SHORT).show();
                    setBoardEnabled(false);
                }
                player1Turn = !player1Turn;
            }
        }
    }
    public int gameEnded(int row,int col, byte playerValue)
    {
        boolean win = true;
        for(int i = 0; i<3 ; i++)
        {
            if(board[i][col] != playerValue)
            {
                win = false;
                break;
            }
        }
        if(win)
        {
            return playerValue;
        }
        return 0;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    void setBoardEnabled(boolean enable)
    {
        TableLayout table = findViewById(R.id.table);
        for (int row_index = 0; row_index < 3; row_index++) {
            TableRow row = (TableRow) table.getChildAt(row_index);
            for (int col_index = 0; col_index < 3; col_index++) {
                Button button = (Button) row.getChildAt(col_index);
                button.setOnClickListener(new CellListener(row_index, col_index));
                button.setEnabled(enable);
            }
        }
    }

    public boolean newGame(MenuItem item) {
        setBoardEnabled(true);

        for (int row_index = 0; row_index < 3; row_index++) {
            for (int col_index = 0; col_index < 3; col_index++)
            {
                board[row_index][col_index] = EMPTY_VALUE;
            }
        }

        TableLayout table = findViewById(R.id.table);
        for (int row_index = 0; row_index < 3; row_index++)
        {
            TableRow row = (TableRow) table.getChildAt(row_index);
            for (int col_index = 0; col_index < 3; col_index++) {
                Button button = (Button) row.getChildAt(col_index);
                button.setText("");
            }
        }
        return true;
    }
}
