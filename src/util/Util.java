package util;

public class Util {

    public static int[] convertToInt(String[] stringArray) {
        int[] intArray = new int[stringArray.length];
        for(int i = 0; i < intArray.length; i++)
            intArray[i] = Integer.valueOf(stringArray[i]);

        return intArray;
    }
}