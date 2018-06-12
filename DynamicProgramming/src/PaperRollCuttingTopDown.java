/*
    Leslie McCarthy CS4
 */


public class PaperRollCuttingTopDown {
    private static int rollLength = 4;  // Try changing this parameter. What happens with very long rods ?
    private static double prices[] = {0.0, 1.2, 3.0, 5.8, 0.0, 10.1};
    private static int cuts[] = new int[rollLength +1];
    private static double optimalPrices[] = new double[rollLength + 1 ];

    private static double rollCut(int length) {
        if(optimalPrices[length] >= 0) { // checking the optimal sol. for a roll of length=length
            System.out.print("optimalPrices for length [" + length+"] -> " + optimalPrices[length] + "  ");
            return optimalPrices[length]; // Note that we avoid computing this rollCut because
        }                                 // the result was previously stored in the array

        double bestPrice = -1000.0;
        if(length != 4) {
            System.out.println("Computation for roll of length=" + length + ":");
            for (int i = 1; i <= length; i++) {  // i iterates over all the possible lengths
                if (i < prices.length ) {
                    double firstCutPrice = prices[i];
                    double leftOverRollPrice = rollCut(length - i);
                    System.out.print("prices[" + i + "] -> " + prices[i] + "  ");
                    double totalPrice = firstCutPrice + leftOverRollPrice;
                    System.out.println("total=" + totalPrice);
                    if (totalPrice > bestPrice) {
                        bestPrice = totalPrice;
                        cuts[length] = i;  // We store the best piece-length (i) for a roll of length=length
                    }
                }
            }

            optimalPrices[length] = bestPrice; // storing the optimal sol. for a roll of length=length
            printArrays();
            System.out.print("Best price for length [" + length + "] -> " + optimalPrices[length] + "  ");
        }
        return bestPrice;
    }

    public static void printArrays(){

        System.out.print("optimalPrices=[");
        for(int i=0; i <  optimalPrices.length; i++)
            System.out.print(optimalPrices[i] + ", ");
        System.out.print("]    ");

        System.out.print("cuts=[");
        for(int i=0; i <  cuts.length; i++)
            System.out.print(cuts[i] + ", ");
        System.out.println("]");
    }

    public static void main(String[] args) {
        printArrays();
        optimalPrices[0]=0; // revenue of piece of length 0 is 0
        cuts[0]=0;
        optimalPrices[1]=prices[1]; // only a piece of length 1 is left for cutting, then, no need of cutting
        cuts[1]=1;
        for(int i=2;i<=rollLength;i++)
        {
            optimalPrices[i]=-1;
            cuts[i]=-1;
        }
        printArrays();

        int length = rollLength;

        if(length != 4){
            System.out.println("\nBEST PRICE=" + rollCut(rollLength) + " for a roll of length " + rollLength);
        }
        if(length == 4){
            System.out.println("No Price for Length " + length);
        }
        System.out.println("\nPIECES CUT:");
        while (length > 0 && length !=4) {
            int item = cuts[length];
            System.out.println("a piece of length " + item + " with price " + prices[item] );
            length -= item;
        }
    }
}

