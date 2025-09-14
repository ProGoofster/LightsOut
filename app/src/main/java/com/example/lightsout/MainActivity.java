package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int GRID_SIZE = 3;
    private GridLayout grid;
    private boolean cellState [][];

    private TextView score;

    View.OnClickListener gridButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button current = (Button) v;
            for (int i = 0; i < grid.getChildCount(); i++) {
                Button gridButton = (Button) grid.getChildAt(i);

                if (gridButton == current) {
                    int row = i / GRID_SIZE;
                    int col = i % GRID_SIZE;


                    cellState[row][col] = !cellState[row][col];
                }
            }

            update();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cellState = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}};

        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.light_grid);

        randomize();

        score = findViewById(R.id.score);

        update();

        for (int i = 0; i < grid.getChildCount(); i++) {
            Button gridButton = (Button) grid.getChildAt(i);

            gridButton.setOnClickListener(gridButtonListener);
        }

        Button resetButton = findViewById(R.id.reset);
        resetButton.setOnClickListener(v -> reset());

    }

    public void update(){
        recolor();
        score.setText(getString(R.string.score_format, countOn()));
    }

    public void reset(){
        cellState = new boolean[][]{{false, false, false}, {false, false, false}, {false, false, false}};
        update();
    }
    public void recolor(){
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button gridButton = (Button) grid.getChildAt(i);

            // Find the button's row and col
            int row = i / GRID_SIZE;
            int col = i % GRID_SIZE;

            if (cellState[row][col] == true) {
                gridButton.setBackgroundColor(getColor(R.color.blue_500));
            } else {
                gridButton.setBackgroundColor(getColor(R.color.black));
            }
        }
    }

    public void randomize(){
        Random random = new Random();
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                cellState[i][j] = random.nextBoolean();
            }
        }
    }

    public int countOn(){
        int count = 0;
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                if(cellState[i][j]) count++;
            }
        }
        return count;
    }

}