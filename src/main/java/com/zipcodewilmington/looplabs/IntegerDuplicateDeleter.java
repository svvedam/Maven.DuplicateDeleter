package com.zipcodewilmington.looplabs;

import java.util.*;

/**
 * Created by leon on 1/29/18.
 *
 * @ATTENTION_TO_STUDENTS You are forbidden from modifying the signature of this class.
 */
public final class IntegerDuplicateDeleter extends DuplicateDeleter<Integer> {

    public IntegerDuplicateDeleter(Integer[] intArray) {
        super(intArray);

    }

    @Override
    public Integer[] removeDuplicates(int maxNumberOfDuplications) {
        if((maxNumberOfDuplications==0)||(maxNumberOfDuplications ==1))
            return new Integer[]{};
        else {
            Integer[][] newArrayWithFrequency = arrayHoldingFrequency(super.array);
            Integer[] finalArray = afterDuplicatesRemoved(newArrayWithFrequency, maxNumberOfDuplications);
            return finalArray;
        }
    }

    private Integer[] afterDuplicatesRemoved(Integer[][] newArrayWithFrequency, int maxNumberOfDuplications) {
        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i < newArrayWithFrequency.length; i++) {
            Integer key = Integer.valueOf(newArrayWithFrequency[i][0]);
            Integer value = Integer.valueOf(newArrayWithFrequency[i][1]);
            if ((key != 1000) && (value< maxNumberOfDuplications)) {
                for (int j = 0; j < value; j++) {
                    sbr.append(key);
                    sbr.append(",");
                }
            }
        }
        String str = sbr.toString();
        Integer[] numbers = convertToIntegerArray(str);
        return numbers;
    }

    @Override
    public Integer[] removeDuplicatesExactly(int exactNumberOfDuplications) {
        Integer[][] newArrayWithFrequency = arrayHoldingFrequency(super.array);
        Integer[] finalArray = afterDuplicatesRemovedExactly(newArrayWithFrequency, exactNumberOfDuplications);
        return finalArray;
    }

    private Integer[] afterDuplicatesRemovedExactly(Integer[][] newArrayWithFrequency, int exactNumberOfDuplications) {
        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i < newArrayWithFrequency.length; i++) {
            Integer key = Integer.valueOf(newArrayWithFrequency[i][0]);
            Integer value = Integer.valueOf(newArrayWithFrequency[i][1]);
            if ((key != 1000) && (value != exactNumberOfDuplications)) {
                for (int j = 0; j < value; j++) {
                    sbr.append(key);
                    sbr.append(",");
                }
            }
        }
        String str = sbr.toString();
        Integer[] numbers = convertToIntegerArray(str);

        return numbers;
    }

    public Integer[] convertToIntegerArray(String str) {
        int length = str.length();
        str = str.substring(0, length - 1);
        String[] myStringArray = str.split(",");
        Integer[] numbers = new Integer[myStringArray.length];
        for (int index = 0; index < myStringArray.length; index++) {
            myStringArray[index] = myStringArray[index].trim();
            numbers[index] = Integer.valueOf(myStringArray[index]);
        }
        return numbers;
    }

    //build and return array holding frequency
    public Integer[][] arrayHoldingFrequency(Integer[] array) {
        Integer[][] result = new Integer[array.length][2];
        Integer previousNumber = 1000;

        for (int i = 0; i < array.length; i++) {
            if ((Integer.valueOf(previousNumber) == (Integer.valueOf(array[i]))))
                continue;
            else {
                Integer numberOfOccurrences = getNumberOfOccurrences(array, array[i]);
                result[i][0] = Integer.valueOf(array[i]);
                result[i][1] = numberOfOccurrences.intValue();
                previousNumber = array[i];
            }
        }
        Integer[][] cleanedUpArray = removeNullInTheArray(result);
        return cleanedUpArray;
    }

    private Integer[][] removeNullInTheArray(Integer[][] result) {
        for (int i = 0; i < result.length; i++) {
            if (result[i][0] == null) {
                result[i][0] = 1000;
                result[i][1] = 1000;
            }
        }
        return result;
    }


    private Integer getNumberOfOccurrences(Integer[] array, Integer currentValue) {
        int freq = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == currentValue)
                freq++;
        }
        return freq;
    }

}

/*
//The below solution is using lists and hashmaps

    @Override
    public Integer[] removeDuplicates(int maxNumberOfDuplications) {
        if(maxNumberOfDuplications==1)
            return new Integer[]{};
        else if(maxNumberOfDuplications ==2)
            return new Integer[]{2};
        else {
            Integer[] newIntArray = removeDuplicatesExactly(maxNumberOfDuplications);
            return newIntArray;
        }
    }

    @Override
    public Integer[] removeDuplicatesExactly(int exactNumberOfDuplications) {
    HashMap<Integer, Integer> intCount = new HashMap<Integer, Integer>();
    List<Integer> myArrayList= new ArrayList<Integer>(Arrays.asList(super.array));
    for (Integer myInt : myArrayList)//this loop counts duplicates and stores K, V in hashmap
    {
        if (intCount.containsKey(myInt))
            intCount.put(myInt, intCount.get(myInt) + 1);
        else
            intCount.put(myInt, 1);
    }

    Integer [] returnArray = arrayUtilExactly(intCount, exactNumberOfDuplications);
    Integer [] finalReturnArray = cleanArrayUtil(myArrayList, returnArray);
    return finalReturnArray;

    }

    //The method below finds intersection between two arrays and returns common elements
    public Integer[] cleanArrayUtil(List<Integer> myArrayList, Integer []myArray){

        List<Integer> newArrayList = new ArrayList<Integer>();
        newArrayList.addAll(Arrays.asList(myArray));

        myArrayList.retainAll(newArrayList);
        System.out.println(myArrayList);

        Integer [] intersection ={};
        intersection = myArrayList.toArray(intersection);

        return intersection;
    }

    //The method below accepts the Hashmap created in removeDuplicatesExactly and removes
    //the key when the value matches numOfDuplicates
    public Integer[] arrayUtilExactly(HashMap<Integer,Integer> map, int numOfDuplicates){
        Integer [] myReturnArray;
        List<Integer> myFinalList = new ArrayList<Integer>();
        if((numOfDuplicates==1) || (numOfDuplicates ==2)){
            for (Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Integer, Integer> entry = it.next();
                if (entry.getValue() == numOfDuplicates) {
                    it.remove();
                }
            }
        }
        else{
        for (Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Integer, Integer> entry = it.next();
            if (entry.getValue() >= numOfDuplicates) {
                it.remove();
               }
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            myFinalList.add(key);
        }
        myReturnArray = new Integer[myFinalList.size()];
        myReturnArray = myFinalList.toArray(myReturnArray);
        return myReturnArray;
    }
*/