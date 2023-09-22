public class part1 {

    public static int countVisibleTrees(int[][] treesMap) {
        int rows = treesMap.length;
        int cols = treesMap[0].length;
        int visibleTrees = 0;


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int height = treesMap[i][j];

                // Check if it's on the edge
                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                    visibleTrees++;
                } else {

                    // Check if it's visible from all directions inside the map
                    boolean northVisible = true;
                    boolean southVisible = true;
                    boolean estVisible = true;
                    boolean westVisible = true;

                    for (int k = 0; k < i ; k++ ){
                        if (treesMap[k][j] >= height) {
                            northVisible = false;
                            break;
                        }
                    }

                    for (int l = j+1 ; l <= cols-1 ; l++ ){
                        if (treesMap[i][l] >= height) {
                            estVisible = false;
                            break;
                        }
                    }

                    for (int m = i+1; m <= rows-1 ; m++ ){
                        if (treesMap[m][j] >= height) {
                            southVisible = false;
                            break;
                        }
                    }

                    for (int n = 0; n < j ; n++ ){
                        if (treesMap[i][n] >= height) {
                            westVisible = false;
                            break;
                        }
                    }

                    // Check if it's visible from one of the directions
                    if (northVisible || southVisible || estVisible || westVisible) {
                        visibleTrees++;

                    }
                }
            }
        }

        return visibleTrees;
    }

    public static void main(String[] args) {
        int[][] treesMap = {
                {2,1,1,3,3,2,2,1,3,0,3,3,1,0,1,1,2,4,1,3,0,1,1,0,4,1,0,0,2,5,4,0,4,0,3,5,3,2,4,5,4,0,5,0,6,4,6,5,5,2,2,4,2,4,3,2,5,0,2,5,4,4,2,4,2,3,2,0,5,3,2,2,1,4,4,2,0,1,1,1,4,0,3,2,4,3,3,1,0,2,2,2,3,1,3,3,3,0,1},
                {3,1,1,0,2,3,3,2,0,2,4,2,0,3,0,1,1,2,0,3,3,3,5,4,2,0,5,5,3,3,0,0,3,1,0,0,2,2,4,1,6,6,3,5,4,2,3,3,5,5,4,6,3,4,2,2,1,0,4,1,6,3,5,2,3,0,0,5,1,4,1,0,3,4,3,2,3,5,3,4,2,4,3,2,1,1,2,4,3,4,0,3,4,0,3,0,1,0,0},
                {0,0,1,2,2,3,2,0,4,4,3,3,0,2,2,0,1,3,1,0,3,3,0,5,4,1,0,2,2,2,3,1,5,5,5,4,0,3,2,3,6,6,5,0,6,0,2,1,4,0,5,0,0,2,3,1,5,0,4,4,4,6,3,4,2,0,0,5,1,0,3,3,3,0,2,2,0,1,2,5,5,1,3,3,1,4,3,1,2,4,4,2,0,4,1,2,1,2,2},
                {0,1,3,1,3,2,2,3,3,0,1,2,3,4,4,2,2,5,5,3,5,0,3,3,3,0,4,1,1,2,2,5,3,2,0,1,3,0,3,4,2,1,5,6,3,4,2,2,6,2,3,3,0,5,0,2,1,2,0,4,6,0,5,5,4,3,6,2,1,0,4,4,5,4,4,2,1,0,3,5,2,3,3,0,4,1,2,2,1,2,4,0,1,0,1,2,1,3,1},
                {1,2,3,2,0,3,2,1,3,2,0,0,4,1,4,1,1,3,4,3,1,3,2,4,0,5,1,0,2,4,4,3,5,2,6,5,6,0,6,0,5,6,2,0,6,2,2,0,3,3,0,3,5,3,4,0,5,6,6,4,4,1,3,2,5,2,1,2,1,2,4,1,3,4,0,0,1,4,0,2,1,5,0,4,0,4,3,3,2,2,0,2,2,4,0,4,3,2,2},
                {3,0,0,3,1,4,3,3,4,3,0,3,2,0,0,3,2,4,1,1,0,0,0,1,0,1,1,0,6,5,0,5,0,6,2,4,1,3,6,6,5,4,0,4,4,0,3,0,0,6,1,6,2,0,0,5,0,0,4,2,3,1,4,2,5,2,6,3,2,6,3,3,3,2,5,0,1,1,0,3,4,0,2,1,5,2,1,2,1,0,0,2,2,4,4,2,2,1,3},
                {3,0,0,3,1,2,3,0,4,3,1,3,1,2,1,2,3,3,2,0,3,5,4,4,3,0,5,1,3,0,4,4,4,4,6,0,4,6,3,4,3,6,4,5,0,1,4,5,6,6,3,6,1,1,0,2,0,2,0,1,5,1,3,4,0,6,3,1,6,2,4,5,2,3,0,4,4,4,4,3,4,5,5,1,2,0,1,4,4,0,3,2,2,2,3,0,3,0,2},
                {1,2,1,2,1,3,3,3,0,2,2,4,0,3,1,5,5,3,4,2,2,2,3,1,0,3,6,2,3,3,1,6,3,2,5,0,6,0,6,2,4,1,6,0,0,0,5,7,1,7,6,1,7,6,3,4,4,0,0,4,3,4,6,4,6,2,4,4,0,0,0,3,0,0,6,4,1,0,0,4,4,5,0,2,5,3,1,3,4,1,1,3,0,2,4,0,1,1,4},
                {3,0,1,3,2,4,2,4,0,4,3,1,5,0,3,1,2,5,5,0,0,1,1,6,0,6,2,4,5,3,1,4,2,4,2,5,1,5,4,6,5,5,7,3,1,1,2,2,6,4,3,4,3,7,1,7,7,4,7,7,5,5,0,4,1,4,6,0,5,4,3,3,0,1,2,6,1,5,3,0,1,2,3,5,0,1,3,2,5,2,1,2,0,3,0,2,3,4,0},
                {0,0,3,2,3,2,0,4,0,4,0,1,1,1,2,0,0,4,3,0,5,5,1,0,1,5,5,5,2,0,1,2,1,5,0,4,6,2,1,4,6,7,7,6,2,7,3,1,3,1,3,5,3,6,5,7,7,7,6,7,1,1,4,3,3,5,4,1,2,6,6,5,2,2,4,2,1,2,5,2,5,1,2,3,5,3,0,3,1,5,3,4,4,3,2,1,1,2,4},
                {0,2,0,0,1,1,2,1,1,0,1,4,1,5,2,1,2,0,1,4,0,6,2,2,1,3,2,3,1,5,2,3,6,1,3,3,5,6,5,5,1,5,2,3,5,5,6,4,1,3,4,3,7,6,6,7,3,4,3,3,2,5,6,4,5,7,5,4,3,3,0,5,6,0,3,0,6,3,0,0,0,5,4,5,5,0,5,0,5,5,0,5,4,4,2,0,4,0,1},
                {1,2,0,2,3,2,2,4,4,1,5,4,1,3,4,2,5,5,1,2,2,4,1,5,0,5,3,2,4,6,3,2,4,7,6,3,2,5,1,7,6,4,5,3,7,6,4,2,1,7,2,2,4,4,2,2,2,7,4,1,2,3,2,5,6,6,7,3,3,2,4,6,6,3,2,2,3,0,1,3,1,2,2,5,0,0,0,0,4,1,5,4,0,1,3,1,4,0,3},
                {3,4,0,0,3,2,2,1,5,4,2,0,2,5,0,5,5,3,1,4,2,0,6,4,1,4,6,0,6,4,5,6,4,1,7,3,3,5,7,2,7,3,5,3,3,1,5,3,2,4,7,7,3,5,6,6,3,4,1,7,2,7,3,6,4,6,6,3,2,3,7,1,4,3,4,5,5,3,5,6,0,6,3,6,4,2,0,0,0,4,4,5,2,5,4,4,1,4,3},
                {0,4,3,4,1,4,4,4,3,3,2,2,5,5,2,4,2,2,1,6,1,3,3,0,4,4,0,0,6,7,6,1,5,5,6,6,1,3,1,7,7,5,4,6,7,4,1,4,7,7,2,5,1,1,6,2,1,2,5,7,6,3,2,5,4,2,3,4,1,1,7,5,3,1,3,3,3,6,1,0,5,3,1,4,0,4,3,2,5,0,1,2,3,0,1,2,2,0,0},
                {0,1,2,1,1,1,0,2,1,4,4,2,3,0,4,6,5,2,1,0,2,4,1,3,1,0,6,1,3,4,6,6,2,4,4,4,5,1,6,4,7,6,2,7,5,1,1,2,8,3,4,2,8,7,6,6,7,6,7,3,5,1,6,3,6,5,6,1,1,4,3,3,4,3,2,5,4,5,5,1,5,2,6,0,1,2,0,2,1,1,0,2,4,0,4,1,2,0,2},
                {0,3,2,1,0,2,5,2,1,5,4,4,5,5,4,0,6,2,0,3,2,0,1,0,5,7,2,4,7,7,7,4,5,1,2,4,3,3,2,5,7,5,3,3,7,8,8,2,8,4,7,6,3,2,6,2,5,2,2,7,2,3,7,5,7,6,2,3,5,7,5,3,4,1,5,4,2,1,3,6,1,1,1,2,1,0,1,0,4,3,5,5,4,0,5,3,1,0,0},
                {3,1,1,4,4,4,3,1,2,3,3,3,3,6,5,1,2,2,2,0,6,1,5,0,3,7,1,7,2,1,7,6,3,6,2,4,3,7,4,3,7,6,8,7,3,8,8,5,2,5,7,6,5,2,5,7,7,4,8,2,8,7,7,4,5,4,5,6,6,1,4,2,6,3,5,2,2,3,5,0,1,2,6,5,0,5,6,3,4,2,1,5,1,4,2,3,5,0,3},
                {1,4,4,2,5,1,4,3,2,1,3,4,4,1,3,5,2,2,4,3,2,6,3,4,1,6,4,5,2,1,7,6,5,2,2,2,7,7,8,8,7,5,2,2,8,5,3,7,6,8,4,4,7,3,7,5,7,5,2,8,3,3,5,5,7,4,5,7,6,7,6,4,5,6,3,3,7,2,0,5,5,4,5,0,5,4,0,1,3,1,5,0,5,3,0,3,0,3,4},
                {0,0,0,2,5,5,0,2,3,2,5,1,2,5,0,5,2,3,1,1,5,3,5,7,4,2,7,6,1,5,7,5,3,5,2,3,5,7,5,8,3,8,7,6,6,8,4,5,3,6,3,7,6,3,4,6,6,4,5,5,8,3,6,8,5,4,4,3,1,6,4,7,3,1,7,1,6,1,4,4,6,4,5,3,5,4,5,3,0,5,4,2,2,4,3,5,3,5,5},
                {2,4,5,0,0,2,3,4,0,1,3,3,3,2,1,1,4,5,3,2,5,3,6,7,5,7,5,3,1,5,5,1,2,2,5,7,5,7,4,6,6,5,3,7,2,5,7,5,5,6,2,7,4,8,6,8,6,4,5,4,7,8,3,5,7,7,8,4,7,6,6,6,4,5,3,7,4,5,4,6,4,6,2,6,1,6,4,1,3,1,0,3,1,3,3,2,1,0,0},
                {0,5,0,5,5,3,0,2,5,3,6,6,5,3,0,3,2,5,0,2,1,4,1,6,3,2,6,1,4,5,8,5,7,3,2,2,6,2,4,3,2,2,7,7,2,7,8,5,8,7,2,8,8,2,3,4,6,5,7,7,7,4,2,6,7,8,8,5,8,3,4,4,1,6,1,6,2,2,1,3,4,4,3,2,4,2,6,5,1,6,0,5,1,3,4,4,2,5,0},
                {1,5,2,3,1,1,5,2,1,0,0,1,5,4,5,4,3,5,3,4,4,3,7,6,1,1,5,4,6,4,3,2,6,4,5,7,8,4,5,8,4,8,4,5,4,4,3,7,4,4,4,4,3,2,7,6,3,8,4,4,3,4,3,7,4,4,4,6,7,3,5,8,5,7,4,5,2,2,5,1,3,4,5,0,3,1,4,3,0,2,0,6,1,5,0,3,4,5,2},
                {4,3,2,4,5,4,3,0,4,2,0,6,2,4,4,2,6,5,3,6,3,2,3,7,1,2,4,4,7,5,5,8,5,6,8,4,7,8,6,8,2,3,2,3,7,6,9,8,7,3,8,4,7,3,8,6,8,3,7,7,3,4,5,8,2,3,8,5,2,2,8,4,3,1,1,5,4,4,6,2,5,7,3,0,3,6,3,1,4,0,2,1,1,2,4,2,1,4,2},
                {4,5,4,0,2,0,4,5,3,3,1,3,4,3,3,4,4,3,5,3,7,6,5,7,6,3,4,3,6,4,8,2,3,2,7,7,4,6,6,4,4,9,6,5,8,4,9,3,3,7,4,8,6,4,7,6,8,8,9,9,4,5,7,2,6,8,6,6,5,3,7,7,2,4,3,7,6,5,7,2,6,5,6,7,4,6,4,0,2,1,3,6,3,4,4,5,2,5,0},
                {5,5,2,4,3,1,3,2,4,2,6,5,0,2,5,5,6,6,4,2,6,2,1,7,4,5,7,3,2,8,8,7,5,7,6,7,4,3,5,8,9,9,5,4,7,8,5,5,7,3,8,4,4,9,7,9,3,6,6,4,6,4,9,3,8,5,6,2,7,2,4,3,2,8,7,2,1,3,7,1,6,3,7,4,3,6,1,1,3,6,4,6,2,4,4,0,2,4,3},
                {0,3,1,0,5,2,1,3,6,2,5,6,2,2,3,5,2,5,4,4,2,1,6,7,2,4,6,8,7,3,5,5,7,6,6,7,7,7,3,5,6,8,8,9,3,4,4,8,3,4,3,9,9,9,8,9,3,5,4,9,6,9,8,8,6,5,7,4,3,5,2,6,3,2,7,8,5,6,6,1,2,2,5,5,3,1,5,5,2,4,0,1,2,1,0,2,0,0,0},
                {3,4,5,3,2,3,6,6,5,3,1,2,4,0,6,6,5,4,5,1,4,4,4,6,3,8,4,5,8,3,4,2,7,6,3,7,8,6,5,7,6,9,6,6,6,5,9,4,8,5,3,9,9,4,6,5,7,4,9,3,3,6,8,5,3,3,9,3,5,6,3,5,6,7,6,5,2,1,1,5,3,4,3,7,5,3,7,3,1,1,3,0,5,2,0,2,1,4,2},
                {3,4,2,1,1,0,2,4,4,0,5,0,5,6,1,1,7,5,4,7,6,1,5,2,8,7,7,3,7,8,3,7,4,9,4,5,6,5,3,3,7,6,8,8,9,6,4,9,9,7,8,6,5,9,5,3,8,7,5,9,4,3,5,7,4,8,3,3,4,8,7,2,2,6,6,5,7,4,6,6,5,1,3,1,7,6,3,0,6,6,0,1,5,1,5,0,4,1,4},
                {1,4,5,4,3,3,4,3,4,2,6,2,5,7,6,2,7,4,6,5,2,1,7,4,7,4,2,3,4,7,8,4,4,5,6,6,3,5,4,3,7,7,3,3,7,8,4,9,4,4,7,7,5,9,7,4,3,9,3,6,4,7,7,4,5,6,9,8,4,8,6,2,8,5,3,7,8,5,7,7,7,1,7,1,3,5,7,5,0,5,0,0,3,2,1,2,0,2,5},
                {3,1,0,2,4,2,5,2,3,1,1,3,4,7,2,1,7,5,7,6,5,2,5,7,3,7,4,5,8,2,4,8,9,5,9,8,8,7,6,7,9,7,9,8,5,6,4,9,4,5,4,6,6,6,8,6,5,3,3,3,7,7,6,5,5,4,3,3,9,3,4,8,2,4,2,8,8,2,8,3,3,1,7,5,4,1,1,7,6,2,3,4,4,6,5,2,0,1,2},
                {4,3,4,0,5,6,4,5,6,4,1,6,3,3,7,4,4,6,6,3,5,2,7,7,2,7,4,4,8,7,7,5,7,5,8,5,4,5,8,7,6,6,7,5,8,5,8,6,9,9,6,4,5,8,7,7,4,9,5,3,6,9,8,8,5,9,5,5,5,7,4,6,3,5,3,6,4,5,2,8,2,4,7,7,4,1,7,3,1,1,1,1,3,2,1,6,4,3,5},
                {0,4,5,0,2,1,1,0,5,5,0,0,3,7,6,2,6,7,4,7,8,5,3,6,8,2,7,8,3,6,3,5,5,5,9,7,6,7,7,9,7,5,7,8,6,4,9,8,8,9,6,6,7,8,5,8,7,6,8,4,8,5,8,5,3,6,7,4,4,5,3,9,7,7,6,2,7,5,4,6,5,2,2,7,5,6,7,6,7,5,1,5,3,2,5,1,4,0,5},
                {3,0,0,1,4,2,6,4,2,0,2,5,4,4,2,4,4,6,4,8,2,2,4,2,4,4,2,4,8,4,3,9,8,8,7,9,5,3,5,8,9,6,7,9,9,7,8,4,7,8,4,7,7,8,4,9,4,9,8,9,9,4,9,3,7,9,6,8,9,7,5,5,5,8,8,2,3,4,5,3,8,8,5,5,2,4,3,7,6,3,3,0,5,4,1,6,3,2,1},
                {3,5,3,6,2,2,6,3,6,6,3,3,6,5,1,7,6,5,2,5,4,4,5,7,6,4,2,3,3,9,4,5,4,6,8,7,9,4,6,8,9,6,6,9,7,5,4,8,4,8,5,4,8,7,7,6,5,6,4,6,9,9,8,6,6,6,9,9,5,5,6,7,5,6,7,7,2,6,8,3,7,2,7,7,5,5,6,7,4,7,6,2,4,3,6,3,4,5,1},
                {2,0,2,4,3,5,6,2,6,5,1,2,1,4,3,7,3,2,8,3,5,2,8,4,3,7,7,7,9,3,3,7,8,4,7,5,9,4,6,5,9,9,9,6,4,6,5,7,4,5,7,6,5,6,9,6,9,9,9,7,9,4,4,8,5,6,3,5,6,4,4,5,7,7,4,8,8,3,8,7,7,8,2,7,5,3,6,7,2,5,3,0,3,0,0,5,5,0,5},
                {1,3,5,6,5,0,0,3,4,6,4,6,4,6,4,1,5,6,7,4,4,6,4,7,5,2,6,4,9,8,4,5,8,6,4,7,5,4,9,4,6,4,9,4,5,5,4,9,7,6,5,9,7,4,5,4,4,8,8,9,6,6,5,7,6,5,6,5,9,8,8,4,7,9,9,6,4,2,2,6,3,7,3,3,4,2,1,2,1,4,4,2,6,3,6,2,6,2,4},
                {1,5,0,4,3,5,3,3,4,0,7,4,6,5,7,4,6,8,6,8,8,5,6,3,5,9,9,3,5,5,3,7,9,3,7,5,8,7,7,9,8,4,5,7,6,5,8,7,7,9,8,7,7,7,5,9,9,5,5,4,7,5,5,7,9,9,7,3,8,5,7,7,8,9,7,9,5,7,8,3,7,4,5,3,2,5,7,1,3,2,3,1,2,3,0,2,1,4,4},
                {5,1,2,4,4,3,5,3,6,4,2,2,7,5,4,2,5,2,5,7,7,2,3,4,4,3,4,9,6,9,9,9,4,8,5,6,5,8,6,4,5,6,5,4,5,8,8,9,9,8,8,8,6,5,7,8,5,9,5,8,8,8,7,6,6,7,6,6,6,3,8,4,4,3,5,5,6,3,2,4,6,2,6,2,3,3,1,2,3,5,4,2,0,1,1,1,1,4,3},
                {4,6,6,6,5,2,1,1,6,2,2,7,3,5,2,6,8,5,8,3,4,2,6,6,9,8,3,7,3,8,3,7,4,4,4,8,9,4,9,7,5,4,6,6,9,5,7,5,8,6,6,9,9,9,6,6,9,6,8,5,5,5,6,4,4,7,5,9,7,4,7,8,6,3,6,5,3,4,6,2,3,2,7,8,7,5,1,1,1,3,4,5,6,6,4,5,0,6,0},
                {0,2,1,1,6,4,5,0,6,1,3,2,1,4,5,5,3,4,6,5,3,7,3,5,8,6,5,9,9,6,4,3,7,9,9,6,9,4,9,6,6,9,6,8,5,7,8,8,7,7,7,9,7,5,5,5,6,6,5,7,7,9,7,4,4,5,8,4,7,4,7,4,9,5,5,9,6,5,5,5,8,2,4,6,2,5,3,5,3,3,6,1,1,0,2,4,6,4,2},
                {2,3,1,0,5,0,2,6,2,3,5,6,4,2,3,2,7,6,5,7,6,3,2,6,9,3,7,8,3,4,3,6,9,5,4,9,8,8,7,9,6,7,8,9,6,9,6,6,7,5,6,8,5,6,6,5,8,9,8,5,7,6,7,6,8,8,8,7,5,9,7,7,3,9,8,3,7,5,6,8,8,8,4,6,4,1,3,2,3,2,3,2,4,3,0,4,2,2,6},
                {4,3,5,2,1,2,1,0,6,6,5,3,3,2,4,7,6,8,2,7,3,7,6,9,6,7,8,9,7,8,8,7,9,6,7,9,9,5,4,7,8,6,7,6,6,9,7,6,9,9,9,5,5,8,6,5,6,7,7,7,9,5,9,9,5,5,8,6,9,5,7,7,4,5,8,8,3,4,8,8,3,7,7,4,8,4,5,4,6,7,2,4,7,5,0,6,2,1,4},
                {1,6,0,3,6,6,2,5,4,4,1,5,7,4,2,4,7,4,4,7,7,2,5,5,5,7,9,4,8,4,5,9,7,5,7,4,4,9,6,8,5,9,6,7,6,5,6,9,7,5,5,7,8,8,7,6,9,7,6,7,5,8,6,6,7,6,6,7,4,8,4,7,9,9,7,9,4,4,7,6,8,2,6,6,8,3,6,2,5,6,7,6,7,5,3,3,1,1,0},
                {2,4,1,5,1,3,3,1,2,2,2,7,3,7,3,5,5,2,8,5,2,6,7,5,4,9,7,8,7,6,8,6,8,8,5,8,7,4,9,5,7,6,8,7,8,5,5,5,7,7,8,6,5,8,5,5,7,5,8,9,7,8,7,6,6,6,9,7,5,9,5,5,7,5,4,5,5,9,6,6,2,7,6,3,7,8,4,1,6,4,7,1,1,0,6,0,0,6,4},
                {3,5,1,1,1,0,4,5,1,2,6,7,6,7,1,2,5,5,4,2,6,5,6,4,6,9,5,6,5,7,7,6,6,7,6,6,6,8,6,7,5,9,8,5,6,8,7,8,7,8,9,9,8,9,7,5,6,8,5,8,7,9,9,9,6,8,4,9,7,9,5,8,5,4,4,5,9,8,7,2,3,5,8,7,5,8,6,7,6,7,2,2,2,2,6,6,4,5,4},
                {3,1,6,0,6,2,1,5,3,2,5,2,2,2,5,3,5,4,6,6,2,4,6,4,6,5,8,7,8,5,7,8,6,5,6,5,4,8,8,8,5,8,6,8,7,8,8,6,7,7,8,7,7,7,9,6,7,5,6,6,5,9,5,8,9,8,5,5,4,5,9,6,9,3,6,4,8,9,7,5,3,5,4,7,4,5,6,3,1,7,1,2,1,2,1,4,4,2,4},
                {2,1,2,5,3,6,6,6,6,1,6,2,3,7,1,8,5,7,2,3,2,6,3,6,9,6,5,3,7,8,9,5,9,9,4,8,5,7,6,7,5,8,5,6,7,6,7,9,8,7,8,9,9,6,9,9,8,8,8,8,8,7,8,7,9,7,4,7,4,9,5,8,7,8,6,6,8,5,5,4,2,8,4,5,5,7,1,1,2,6,6,1,6,2,2,0,2,6,4},
                {5,1,0,0,2,4,5,4,6,2,6,1,1,4,3,7,8,7,2,7,2,3,4,9,4,6,6,8,3,7,7,9,9,6,4,9,5,6,8,6,6,5,6,5,8,8,6,6,9,8,8,8,7,8,6,9,8,5,6,5,8,6,5,8,9,4,7,4,6,9,8,5,3,7,7,7,4,9,7,8,7,5,7,2,3,4,1,5,7,7,6,1,1,3,4,4,1,6,6},
                {0,0,4,3,5,1,2,1,6,3,6,4,7,2,8,6,4,6,8,4,4,8,4,8,8,3,7,5,6,7,4,6,8,6,8,4,7,7,9,9,8,7,9,9,6,8,8,8,7,6,7,8,9,8,6,7,7,9,8,5,5,9,9,8,5,4,9,5,5,6,6,8,5,6,6,5,5,9,8,5,8,8,3,5,6,3,3,3,2,2,1,3,4,5,2,2,3,3,4},
                {3,3,6,4,6,3,4,3,6,4,4,7,7,5,7,3,6,3,5,4,3,6,4,9,9,4,9,9,4,7,5,6,4,6,7,8,7,8,6,9,8,8,5,8,8,9,8,8,8,6,6,8,7,7,9,6,8,7,5,8,9,8,9,6,6,5,4,7,7,8,6,6,6,5,7,9,4,5,4,4,7,5,6,4,6,2,7,3,5,7,1,2,6,6,5,3,1,4,6},
                {0,2,6,0,6,5,1,6,2,5,7,7,6,5,7,6,2,4,2,2,2,4,6,7,8,3,6,7,5,7,9,4,9,4,6,4,5,9,6,5,5,6,7,6,9,7,7,8,6,7,8,9,9,9,8,9,8,7,6,9,5,9,5,7,5,5,9,4,5,7,8,4,5,5,4,6,6,8,3,2,6,3,6,4,3,6,4,7,7,4,6,1,4,1,1,2,0,4,2},
                {5,0,3,2,2,1,6,3,1,5,7,7,5,1,4,7,7,7,5,3,3,4,4,5,3,6,5,4,6,8,5,8,8,4,5,5,6,5,8,9,8,6,8,8,7,6,8,7,6,8,7,7,8,9,8,6,7,9,7,5,7,5,8,8,8,5,7,4,8,8,8,9,8,5,7,3,9,6,4,2,3,8,2,5,7,7,6,2,5,3,1,5,6,7,3,6,6,5,0},
                {5,4,6,5,1,3,6,6,1,4,3,5,2,4,2,5,5,3,7,6,4,8,9,3,7,5,4,9,3,4,7,5,7,5,8,4,6,7,8,8,7,7,9,9,7,7,8,9,8,7,7,6,8,6,9,6,7,8,9,8,6,7,8,5,6,4,6,7,6,5,9,8,3,3,5,9,6,5,8,6,7,3,3,5,7,2,7,1,3,7,1,1,2,2,1,5,0,3,0},
                {6,1,1,2,6,5,6,4,6,5,7,6,7,6,3,5,3,2,3,3,6,7,7,7,6,5,9,4,9,9,8,5,8,6,9,4,8,9,7,8,7,7,7,5,8,9,9,9,6,7,8,8,7,6,9,7,9,9,7,7,8,8,7,8,7,4,7,6,8,5,6,6,8,9,9,5,6,8,3,3,6,4,2,4,4,8,4,4,2,1,4,6,1,1,5,3,4,2,6},
                {3,5,4,6,3,2,5,2,7,5,5,5,4,3,4,6,4,8,6,4,2,2,5,9,9,4,8,8,4,5,9,7,6,6,5,8,8,6,9,9,9,7,8,5,7,6,7,9,7,6,9,9,9,8,9,7,7,8,9,5,5,5,8,9,9,4,5,8,4,8,7,6,4,3,9,7,3,3,5,4,5,4,8,6,7,2,2,2,3,7,1,3,4,2,5,2,2,4,4},
                {5,2,0,0,6,6,3,6,5,1,4,2,4,7,7,5,2,6,3,4,7,7,7,4,7,8,6,4,7,9,4,9,4,5,4,8,6,8,7,8,7,9,7,6,9,9,9,7,6,6,9,7,8,6,9,9,8,6,7,8,9,7,8,8,9,5,4,4,5,8,8,6,9,6,7,3,4,6,7,5,6,3,5,4,2,8,6,7,2,7,2,2,2,1,2,4,5,0,3},
                {0,3,6,4,2,6,3,1,6,5,3,3,1,6,2,8,7,5,7,7,8,6,8,6,4,8,7,5,4,5,8,9,6,5,5,6,9,5,8,7,5,8,8,6,8,8,7,9,9,7,7,8,6,8,6,8,6,6,8,8,9,8,6,5,7,4,8,4,9,7,5,3,8,9,8,6,7,7,4,6,8,5,5,7,5,5,1,5,3,7,1,7,4,6,5,0,4,2,1},
                {5,6,0,2,6,5,6,3,4,5,7,5,4,6,3,7,2,4,5,6,7,5,8,8,7,8,8,7,6,7,8,5,6,6,6,7,4,5,7,6,9,9,5,5,8,7,5,5,5,9,6,6,5,7,5,6,6,6,7,9,5,8,6,7,9,8,7,8,8,8,7,3,4,3,7,5,3,3,6,6,3,7,4,6,2,3,1,7,2,4,1,5,5,1,2,5,5,2,5},
                {1,5,4,5,6,0,6,2,6,1,1,5,5,4,5,6,7,8,4,4,6,8,8,9,5,3,8,3,8,6,7,5,9,7,7,6,4,8,5,9,8,5,5,9,7,5,6,8,5,7,7,7,7,7,5,5,8,9,5,8,7,8,6,8,8,7,6,8,7,6,4,7,7,8,4,3,3,4,6,4,7,7,2,6,5,5,1,3,6,7,1,6,4,1,3,0,6,2,4},
                {4,3,5,6,0,4,6,1,4,2,6,7,6,6,1,3,4,5,6,2,2,8,3,9,3,6,5,9,4,3,3,8,4,7,5,9,5,5,7,6,9,9,6,7,9,8,8,5,7,6,7,7,5,9,5,9,9,5,6,6,7,9,6,9,6,4,5,5,6,4,6,4,6,3,5,8,8,3,2,2,4,8,5,5,5,1,7,7,4,3,2,5,4,4,4,2,6,1,0},
                {0,1,5,3,3,0,2,0,1,7,2,7,6,3,1,5,3,6,2,3,4,3,8,9,9,7,5,6,5,8,7,8,4,4,7,4,7,9,9,6,9,7,9,6,6,5,7,8,7,5,6,9,5,5,5,6,9,9,5,7,9,9,7,9,9,5,9,9,4,4,4,6,8,8,3,8,4,8,5,8,2,2,8,3,2,3,5,1,1,3,4,4,6,5,2,2,1,6,6},
                {3,5,2,2,3,0,0,3,3,4,7,6,4,5,6,6,4,8,6,3,6,5,3,2,7,6,4,8,3,5,9,5,7,8,9,9,7,4,9,5,8,8,8,8,5,6,6,8,5,6,5,6,7,7,9,5,8,5,9,8,5,9,5,5,8,5,8,8,4,5,3,6,8,4,3,5,6,6,6,3,5,2,3,5,5,3,5,1,4,3,2,3,3,4,0,0,3,6,2},
                {1,5,4,3,2,4,6,4,1,1,5,4,5,1,4,6,2,2,5,5,4,2,3,8,3,4,7,3,3,8,7,3,6,7,4,9,6,4,8,4,9,7,8,7,9,8,8,6,9,8,8,8,6,5,5,6,6,8,7,5,7,4,4,9,6,5,7,6,5,5,3,9,5,6,5,7,6,5,4,2,8,5,8,6,5,4,5,2,5,4,5,2,0,6,5,3,0,0,4},
                {4,5,3,0,3,6,5,2,1,3,2,6,2,1,6,3,3,8,2,2,5,8,5,2,5,6,6,8,5,6,8,6,9,7,5,5,5,8,9,9,9,5,5,5,7,8,8,6,6,7,8,5,6,6,5,7,8,4,5,4,8,7,6,5,9,5,6,7,7,3,7,4,6,6,7,7,2,6,3,8,3,5,5,4,2,3,5,6,4,4,7,3,3,0,4,3,1,2,0},
                {2,5,5,1,1,6,1,5,5,5,2,5,3,2,3,4,6,2,5,2,6,4,3,8,7,4,3,7,7,4,3,8,9,7,7,7,4,9,9,9,4,8,6,5,4,4,6,5,5,8,8,7,7,6,6,9,7,9,7,7,8,5,6,9,9,8,5,4,5,9,4,3,9,6,6,8,4,5,4,5,8,7,4,2,4,2,7,1,5,5,3,3,1,0,3,3,5,3,1},
                {0,2,5,5,6,0,6,2,5,4,1,3,4,7,7,1,2,3,3,7,6,6,8,3,4,3,8,6,3,4,5,6,6,7,3,8,5,6,5,6,6,5,4,5,5,9,5,4,7,7,9,5,7,5,6,5,8,5,4,6,6,5,4,7,4,5,5,6,7,6,6,5,9,3,5,2,7,2,4,7,3,2,2,6,1,7,6,3,4,3,4,6,6,2,3,0,2,3,4},
                {0,2,4,4,6,6,5,4,6,5,5,5,2,2,4,2,5,4,7,6,3,5,3,2,7,8,4,9,5,9,6,6,5,8,9,6,8,7,8,9,6,7,6,6,6,7,7,4,8,8,6,5,6,9,7,8,7,6,5,6,9,7,8,8,9,8,7,3,6,8,8,7,7,3,5,5,2,4,8,6,6,6,8,1,5,2,7,4,7,6,3,3,5,3,5,1,6,6,0},
                {4,3,1,5,5,3,6,0,5,0,0,3,4,6,2,6,3,2,3,5,5,4,3,5,2,8,7,7,8,5,6,5,3,3,7,9,4,6,7,5,6,5,8,6,5,8,4,6,9,5,7,7,6,4,7,6,9,9,5,7,7,8,5,8,9,8,8,4,9,7,4,6,5,8,3,8,7,7,4,6,2,5,1,1,5,4,3,6,7,5,4,0,1,3,0,4,3,3,1},
                {2,4,5,1,6,6,6,6,0,2,1,4,7,5,1,2,5,3,7,4,3,8,5,6,3,7,8,2,6,7,3,8,8,6,3,3,4,9,4,5,8,4,4,8,9,5,6,7,8,4,4,8,4,6,4,7,6,4,4,5,5,4,8,9,8,3,6,5,4,8,8,3,5,5,4,3,6,8,6,3,2,3,7,4,5,5,1,5,2,2,6,1,2,2,2,6,5,0,5},
                {5,4,0,3,3,5,1,4,6,2,3,3,5,4,5,6,2,6,2,5,2,3,8,7,4,8,7,8,7,3,5,4,5,4,9,5,6,7,5,6,6,5,7,4,8,4,4,8,7,4,5,9,7,9,9,4,9,5,9,4,7,3,5,6,5,4,8,9,5,9,4,3,3,6,8,8,6,7,2,3,7,1,3,3,4,4,7,4,4,3,1,2,6,4,3,3,4,1,4},
                {2,3,4,2,5,5,6,2,0,1,2,0,6,1,5,5,6,5,1,2,2,8,4,3,8,8,3,7,6,7,3,3,7,3,6,3,6,5,9,7,8,7,9,6,4,6,7,6,8,7,8,5,9,8,6,9,6,6,7,4,7,7,6,7,8,3,3,9,4,7,6,6,4,5,4,3,5,5,8,7,8,6,5,2,4,1,7,7,1,0,6,0,1,5,5,2,5,3,5},
                {1,5,5,2,3,4,4,2,5,6,2,0,2,6,3,2,6,4,5,3,6,2,5,2,3,8,5,7,6,5,5,6,4,3,5,7,7,3,3,8,5,7,6,3,4,8,5,7,5,8,4,6,6,9,6,7,8,7,6,3,4,7,7,7,7,8,5,9,9,6,2,6,8,3,5,4,5,6,6,7,7,3,5,7,1,5,1,6,4,0,1,1,5,6,0,1,4,0,2},
                {4,1,5,0,1,1,6,4,2,6,3,1,3,3,5,7,5,1,5,2,7,7,2,8,6,3,2,3,6,5,8,7,6,4,4,5,7,3,8,3,3,9,4,7,9,9,3,4,9,3,3,8,6,7,3,6,8,9,7,3,9,8,6,5,6,3,5,6,4,4,8,8,7,7,4,2,3,3,6,5,3,7,1,7,3,7,6,5,1,4,1,4,2,4,2,1,1,1,2},
                {3,3,1,5,5,3,1,2,4,6,6,5,6,4,7,7,7,7,7,5,1,5,1,4,4,3,6,3,5,6,4,4,3,4,7,7,7,4,9,8,8,5,8,7,9,5,9,6,5,6,3,7,9,3,4,8,4,8,9,7,3,5,3,6,8,7,4,6,6,4,3,7,2,3,3,3,3,8,4,7,5,2,3,7,5,2,3,1,6,1,1,1,0,1,4,2,0,5,1},
                {1,2,5,4,0,3,3,1,6,6,5,0,4,2,2,1,4,5,5,6,5,5,3,2,5,6,5,6,5,6,4,2,6,2,8,8,3,4,3,5,9,7,3,4,5,9,8,5,4,5,5,5,7,7,8,8,4,9,3,5,7,5,3,5,5,4,5,6,4,6,2,6,4,2,5,2,3,5,7,3,7,2,5,4,6,4,3,5,5,4,4,4,6,1,6,0,1,1,2},
                {2,4,4,2,4,5,2,2,1,0,4,0,3,2,5,6,6,7,5,2,4,5,3,4,3,4,4,8,4,3,7,6,7,8,4,5,6,4,6,4,5,7,9,5,7,7,3,7,9,5,5,9,6,3,8,7,4,7,5,3,7,7,6,7,7,5,4,8,4,2,5,5,7,8,5,4,4,7,7,2,5,7,3,1,3,5,6,4,3,5,2,1,2,2,1,3,4,1,4},
                {5,0,2,4,3,5,0,0,4,6,5,3,3,4,6,0,3,5,2,5,3,6,2,2,2,2,4,3,4,2,4,4,7,4,8,6,7,3,4,3,4,7,9,5,3,6,7,9,7,3,3,8,8,8,8,4,7,8,5,6,3,4,9,5,4,4,5,7,4,6,3,4,8,2,2,7,7,3,1,6,2,4,2,2,3,6,3,2,6,1,3,3,1,2,0,1,0,2,4},
                {3,2,4,2,2,2,0,4,4,3,0,6,5,0,5,0,1,6,4,2,6,4,4,5,5,5,7,8,2,7,2,6,6,7,6,2,3,7,3,5,3,7,5,8,8,8,5,5,8,8,3,7,7,9,6,9,3,9,5,3,5,6,4,5,8,7,8,4,6,8,3,2,6,8,7,1,2,1,4,2,2,6,2,1,3,3,4,6,4,3,5,0,4,0,4,1,2,1,4},
                {2,4,2,4,4,1,3,1,0,6,2,2,4,3,2,1,6,5,1,3,4,4,5,7,1,6,1,6,5,7,5,6,7,5,7,5,8,7,7,7,2,7,3,6,7,3,9,4,8,9,4,9,4,7,4,9,3,5,5,3,3,6,2,8,7,6,5,2,6,7,8,2,8,1,5,2,3,3,2,5,1,4,7,1,5,0,4,2,2,1,5,0,5,5,0,2,5,3,1},
                {4,0,4,4,1,3,1,3,1,4,6,4,1,4,1,6,0,6,6,7,7,3,1,7,6,4,4,5,7,5,7,3,5,7,5,3,5,3,6,5,4,8,4,3,3,8,3,4,4,7,7,7,3,4,3,8,4,6,5,5,7,5,2,5,6,5,3,8,2,4,6,5,7,6,3,3,3,7,7,3,5,5,1,3,1,5,4,5,6,4,1,4,0,3,4,2,2,5,1},
                {3,0,1,1,0,0,1,1,0,4,1,5,4,2,0,4,4,4,1,1,1,6,1,5,6,1,7,6,6,2,3,8,7,6,5,3,3,4,4,8,7,5,8,4,2,5,5,7,3,3,5,4,8,5,5,5,5,6,8,8,4,4,7,6,8,8,5,7,5,4,6,5,4,3,3,3,2,3,1,7,7,6,4,4,5,5,6,5,2,4,1,2,0,2,1,2,2,3,4},
                {1,2,1,3,5,0,1,5,3,0,5,2,6,6,2,1,3,2,3,2,2,7,2,1,1,1,6,1,3,4,2,1,6,5,5,3,8,8,3,5,6,6,3,6,8,4,2,6,2,5,2,8,7,8,4,6,8,2,7,4,8,2,7,5,6,3,8,5,5,6,5,4,4,3,7,7,6,3,1,5,4,1,5,2,6,5,2,5,2,0,0,5,5,5,3,5,4,1,3},
                {3,0,0,4,1,1,2,5,5,2,0,4,0,2,6,6,1,0,4,1,3,5,7,4,5,7,7,6,6,1,7,7,3,5,4,5,8,4,3,2,8,4,3,6,4,6,8,2,6,8,3,7,7,8,4,8,7,4,6,6,2,7,8,8,6,2,3,5,2,1,3,5,4,5,7,3,2,5,5,6,0,1,5,5,6,2,2,4,5,0,0,4,1,1,5,5,4,1,4},
                {2,0,1,5,1,3,1,5,0,2,4,3,4,4,4,3,6,2,4,1,5,0,1,7,7,5,6,3,1,7,3,3,3,1,7,6,4,5,5,6,7,7,8,7,6,8,2,5,3,3,8,6,8,6,2,8,8,3,3,2,2,4,6,4,3,6,6,5,3,5,1,1,3,3,2,6,2,3,5,2,5,0,2,3,5,3,5,3,3,5,0,1,0,2,5,1,4,0,3},
                {4,4,2,1,5,1,2,1,2,2,0,0,2,4,0,0,1,2,2,4,0,5,1,5,3,5,4,4,2,1,6,5,2,4,7,5,5,4,2,4,2,2,6,2,7,3,2,7,3,3,6,3,6,5,4,5,3,7,5,6,5,7,7,2,7,4,1,7,6,7,1,5,1,2,1,6,3,4,5,0,0,3,0,6,6,0,6,2,3,0,5,0,5,0,0,5,4,4,2},
                {2,2,1,1,4,2,3,5,5,1,1,4,0,2,4,1,3,6,3,3,1,5,3,2,3,4,4,4,2,2,6,7,1,2,1,5,7,5,7,5,7,6,7,4,5,7,7,3,4,8,3,8,4,7,6,8,6,5,3,5,2,5,2,1,4,1,7,4,5,1,3,2,1,6,1,1,2,3,1,4,0,2,3,4,1,5,5,4,4,4,0,2,2,1,4,1,3,4,3},
                {3,0,3,1,1,1,2,4,1,4,0,0,2,5,4,2,1,2,6,2,4,5,6,6,6,0,6,3,7,1,4,1,3,7,6,3,6,5,3,4,3,3,1,7,5,7,6,5,4,3,3,3,8,3,2,1,6,3,1,4,5,2,7,5,7,1,3,2,6,3,1,4,1,5,7,6,1,4,0,3,1,4,4,1,4,0,4,4,4,1,4,5,4,1,2,3,1,1,3},
                {2,4,4,2,0,0,5,0,2,5,3,4,2,4,2,2,6,1,3,6,2,2,0,2,2,1,5,0,5,3,6,7,4,4,3,4,6,2,2,1,7,4,3,5,1,2,5,2,4,6,4,5,5,4,7,5,6,2,1,7,5,3,4,6,5,7,6,7,6,5,7,5,6,3,1,2,4,0,1,6,6,5,5,6,6,5,5,4,2,1,2,4,5,2,3,4,0,2,2},
                {0,3,3,1,4,0,4,1,0,0,3,1,0,0,1,2,4,4,6,0,5,1,2,6,6,5,4,6,6,5,6,5,4,4,4,3,3,4,6,1,6,6,1,7,7,7,5,4,2,2,1,5,2,7,2,7,2,6,6,5,4,7,3,5,2,6,7,2,6,6,3,2,6,4,5,6,4,0,1,5,5,6,0,6,5,3,1,4,3,1,3,2,1,0,0,3,2,0,3},
                {3,3,3,1,4,4,0,2,0,2,0,3,5,4,0,3,4,4,4,6,0,2,1,2,0,3,4,6,0,1,4,4,3,6,6,2,3,2,7,7,7,5,1,7,5,6,2,7,4,3,2,4,6,1,4,2,1,4,6,7,2,7,5,2,5,3,7,4,1,6,5,2,5,3,4,2,2,1,4,5,1,5,4,1,1,5,0,3,4,4,3,2,4,1,2,2,4,3,2},
                {0,0,3,4,4,1,1,4,4,2,3,2,3,3,1,1,4,4,3,2,1,2,4,0,2,6,2,1,3,2,1,3,4,1,5,6,2,6,4,3,1,2,7,7,2,1,1,5,4,1,5,5,3,5,1,7,4,1,1,1,1,6,1,3,7,2,1,3,2,2,1,3,4,2,3,0,6,3,3,3,0,0,0,0,5,5,1,3,1,4,4,0,1,2,4,2,0,1,4},
                {4,3,1,2,4,4,2,1,3,0,3,4,1,4,0,5,3,2,3,4,5,0,2,0,0,1,4,0,1,6,3,0,2,2,1,3,4,6,7,2,5,1,2,7,7,7,4,4,7,1,2,1,5,1,7,3,3,4,2,6,3,3,4,2,6,5,0,4,2,0,0,0,6,2,5,1,0,5,5,4,1,4,0,2,4,2,3,3,1,4,4,2,4,0,4,2,4,4,2},
                {0,3,2,3,3,2,2,4,2,3,1,1,0,1,4,5,5,3,5,0,3,4,5,6,3,6,2,5,3,3,1,3,5,6,5,4,4,6,2,3,3,1,2,5,2,5,7,7,3,3,4,7,4,3,1,1,7,6,5,6,1,2,2,0,2,2,2,1,0,1,1,0,1,5,2,6,3,1,4,2,3,4,3,4,3,5,2,5,2,0,0,2,4,4,2,1,0,4,3},
                {1,0,2,1,0,3,0,0,2,3,2,2,0,5,2,4,5,1,1,0,0,2,3,0,3,4,0,4,2,0,1,6,1,6,3,5,5,5,6,6,4,2,5,0,3,2,2,6,2,7,1,2,4,6,6,5,2,0,5,3,2,0,2,3,5,1,0,5,4,2,2,2,6,3,4,6,1,3,5,3,2,4,3,4,2,5,5,2,0,2,4,3,4,1,3,1,2,4,2},
                {2,0,3,1,3,0,0,1,3,1,3,3,2,3,3,0,4,5,0,4,4,2,0,4,2,2,3,4,0,4,0,6,0,6,3,2,4,0,3,3,5,1,6,6,4,2,4,1,4,4,3,6,3,1,4,5,4,3,3,0,3,2,2,6,5,0,2,2,6,2,4,5,6,5,1,4,3,2,1,1,3,0,4,0,5,2,5,4,1,3,3,2,3,3,4,0,2,1,0},
                {0,3,0,1,2,4,2,0,0,3,4,0,2,2,1,2,3,0,2,4,1,2,2,5,5,2,1,4,4,6,4,5,2,6,5,2,3,1,3,2,4,1,1,1,4,0,6,4,2,2,6,5,5,2,0,5,4,3,3,2,1,6,5,2,3,3,4,4,1,3,6,6,2,2,1,2,2,1,0,3,5,4,2,1,1,3,2,4,1,4,1,2,4,3,1,4,4,0,2},
                {1,2,0,1,1,2,4,1,3,0,3,0,3,4,4,1,1,4,4,1,2,2,3,1,5,1,4,3,3,2,2,5,4,0,0,0,0,2,3,5,2,5,6,3,4,2,3,6,2,0,0,3,3,4,1,6,0,0,4,5,6,1,6,3,1,2,4,1,6,3,6,0,4,2,3,1,0,0,4,5,1,2,0,1,3,2,2,4,0,4,0,2,3,0,1,2,3,0,1},
                {2,1,2,0,1,1,3,1,2,0,1,1,2,3,3,1,3,0,5,2,3,1,1,1,5,2,5,1,2,1,3,0,3,4,1,4,2,4,4,1,2,1,3,2,0,6,4,4,0,5,0,3,4,3,2,1,1,5,5,6,3,3,6,2,4,6,4,6,3,2,1,5,4,4,4,0,2,5,3,3,1,4,1,1,0,0,2,0,4,1,4,1,2,1,2,2,1,2,1},
                {0,0,2,0,2,3,3,2,3,4,4,3,1,0,2,2,4,2,3,1,1,5,2,3,0,0,0,1,4,0,1,5,0,5,0,6,1,6,0,5,2,2,0,0,4,6,3,5,2,2,2,5,5,6,2,0,0,2,5,1,6,6,3,5,5,1,4,4,4,5,5,1,2,3,5,2,5,0,4,4,0,2,2,3,2,4,2,0,1,0,4,1,4,0,1,1,0,2,1}
        };

        int result = countVisibleTrees(treesMap);
        System.out.println("Number of visible trees : " + result);
    }
}
