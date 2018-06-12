/*
    Leslie McCarthy CS4
 */

import java.util.Arrays;

public class PaperRollCuttingBottonUp {

    private static int rollLength = 8;  // Try changing this parameter. What happens with very long rods ?
    private static Double prices[] = {0.0, 1.2, 3.0, 5.8, 0.0, 10.1};//p
    private static int cuts[] = new int[rollLength +1];//s
    private static Double optimalPrices[] = new Double[rollLength + 1 ];

    private static double rollCut(int length ){

        for (int j = 1; j <= length; j++) {
            double bestPrice = -1000;//q

            for (int i = 1; i <= j; i++) {
                if(i < prices.length) {
                    if (bestPrice < prices[i] + optimalPrices[j - i]) {
                        bestPrice = prices[i] + optimalPrices[j - i];
                        cuts[j] = i;
                    }
                }
            }

            optimalPrices[j] = bestPrice;
        }

        printArrays();

        return optimalPrices[length];
    }

    public static void printArrays(){
        System.out.println("Optimal prices " + Arrays.toString(optimalPrices));
        System.out.println("Cuts "+ Arrays.toString(cuts));

        if(rollLength == 4){
            System.out.println("There is no price for Paper roll of length of [ " + rollLength + " ]");
        }
    }

    public static void main(String[] args) {

        optimalPrices[0]=0.0;// revenue of piece of length 0 is 0
        cuts[0] = 0;
        optimalPrices[1] = prices[1]; // only a piece of length 1 is left for cutting, then, no need of cutting
        cuts[1] = 1;

        for (int i = 2; i <= rollLength; i++) {
            optimalPrices[i] = -1.0;
            cuts[i] = -1;
        }

        printArrays();

        //we do not cut for length of 4 so i am not printing this.
        if (rollLength != 4) {

            System.out.println("The optimal price for length " + rollLength + " price " + rollCut(rollLength));
            int length = rollLength;

            //this array of length passed in will store
            int allCuts[] = new int[prices.length];

            while (length > 0) {
            int lengthOfCut = cuts[length];
            //Using print statement to see optimal cuts taken from roll length entered.
                System.out.println("Print length of cut "+ lengthOfCut);
            allCuts[lengthOfCut]++;

            length -= lengthOfCut;
            }

            for (int i = 0; i < allCuts.length; i++) {

                if (allCuts[i] != 0) {
                    System.out.println("Cut [ " + allCuts[i] + " ] piece of paper roll of length[ " + i + " ] Cost of cut -> [ " + prices[i]+ " ] ");
                }
            }
            System.out.println("The optimal price for length " + rollLength + " price " + rollCut(rollLength));
        }

    }
}

