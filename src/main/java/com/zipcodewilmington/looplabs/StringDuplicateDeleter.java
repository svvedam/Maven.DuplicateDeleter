package com.zipcodewilmington.looplabs;

import java.util.*;

/**
 * Created by leon on 1/28/18.
 * @ATTENTION_TO_STUDENTS You are forbidden from modifying the signature of this class.
 */
public final class StringDuplicateDeleter extends DuplicateDeleter<String> {
    public StringDuplicateDeleter(String[] stringArray) {
        super(stringArray);
    }

    @Override
    public String[] removeDuplicates(int maxNumberOfDuplications) {

        if((maxNumberOfDuplications==0)||(maxNumberOfDuplications ==1))
            return new String[]{};
        else {
            String[][] newArrayWithFrequency = arrayHoldingFrequency(super.array);
            String[] finalArray = afterDuplicatesRemoved(newArrayWithFrequency, maxNumberOfDuplications);
            return finalArray;
        }
    }
    private String[] afterDuplicatesRemoved(String[][] newArrayWithFrequency, int maxNumberOfDuplications) {
        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i < newArrayWithFrequency.length; i++) {
            String key = newArrayWithFrequency[i][0];
            if(key.equalsIgnoreCase("A"))
                continue;
            Integer value = Integer.valueOf(newArrayWithFrequency[i][1]);
            if ((key != "A") && (value< maxNumberOfDuplications)) {
                for (int j = 0; j < value; j++) {
                    sbr.append(key);
                    sbr.append(",");
                }
            }
        }
        String str = sbr.toString();
        String[] strings = str.split(",");
        return strings;
    }


    @Override
    public String[] removeDuplicatesExactly(int exactNumberOfDuplications) {
        String[][] newArrayWithFrequency = arrayHoldingFrequency(super.array);
        String[] finalArray = afterDuplicatesRemovedExactly(newArrayWithFrequency, exactNumberOfDuplications);
        return finalArray;

    }

    private String[] afterDuplicatesRemovedExactly(String[][] newArrayWithFrequency, int exactNumberOfDuplications) {
        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i < newArrayWithFrequency.length; i++) {
            String key = newArrayWithFrequency[i][0];
            if(key.equalsIgnoreCase("A"))
                continue;
            Integer value = Integer.valueOf(newArrayWithFrequency[i][1]);
            if ((key != "A") && (value != exactNumberOfDuplications)) {
                for (int j = 0; j < value; j++) {
                    sbr.append(key);
                    sbr.append(",");
                }
            }
        }
        String str = sbr.toString();
        String[] stringArray = convertToStringArray(str);

        return stringArray;
    }

    public String[] convertToStringArray(String str) {
        int length = str.length();
        str = str.substring(0, length - 1);
        String[] myStringArray = str.split(",");
        return myStringArray;
    }


    //build and return array holding frequency
    public String[][] arrayHoldingFrequency(String[] array) {
        String[][] result = new String[array.length][2];
        String previousString = "A";

        for (int i = 0; i < array.length; i++) {
            if (previousString.equalsIgnoreCase(array[i]))
                continue;
            else {
                Integer numberOfOccurrences = getNumberOfOccurrences(array, array[i]);
                result[i][0] = array[i];
                result[i][1] = String.valueOf(numberOfOccurrences);
                previousString = array[i];
            }
        }

        String[][] cleanedUpArray = removeNullInTheArray(result);
        return cleanedUpArray;
    }

    private String[][] removeNullInTheArray(String[][] result) {
        for (int i = 0; i < result.length; i++) {
            if ((result[i][0]==null)){
                result[i][0] = "A";
                result[i][1] = "A";
            }
        }
        return result;
    }


    private Integer getNumberOfOccurrences(String[] array, String currentValue) {
        int freq = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(currentValue))
                freq++;
        }
        return freq;
    }
}
