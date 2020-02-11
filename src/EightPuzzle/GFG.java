package EightPuzzle;

public class GFG {
    static int getInvCount(Integer[][] arr)
    {
        Integer count= 0;
        Integer[] array1D = new Integer[9];
        for(int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++) {
                array1D[count] = arr[i][j];
                count++;
            }
        }

        Integer inv_count = 0;
        for (int i = 0; i < array1D.length -1; i++){
            if(array1D[i] == 0)
                continue;
            for (int j = i+1; j < array1D.length ; j++) {
                // Value 0 is used for empty space
                if (array1D[j] == 0) {

                }
                else if(array1D[i] > array1D[j]){

                        inv_count++;

                }


                }


            }


        System.out.println(inv_count);
        return inv_count;
    }
    // This function returns true
    // if given 8 puzzle is solvable.
    static boolean isSolvable(Integer[][] puzzle)
    {
        // Count inversions in given 8 puzzle
        Integer invCount = getInvCount(puzzle);

        // return true if inversion count is even.
        return (invCount % 2 != 0);
    }
}
