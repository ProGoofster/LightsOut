Jake Berryman

UI: activity_main.xml

Code: MainActivity.java

cellstate a matrix

code to color each button based on cell state:
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

code to get a handle on the grid layout: