package en.najyau;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.util.Optional;

//use NumInput.txt file to pass the values
//The format of numbers in a file is integers separated by comma and a space

//TO-DO: consider migrating filtering function into an interface

interface SwapCallable {
    public int[] call (int arr[]);
};



/*
class AscSwapTest implements SwapCallable {
	public int[] call (int arr[]) {
		int[] tempArr = new int[2];
		if (arr[0] < arr[1]) {
			tempArr[0] = arr[0];
			tempArr[1] = arr[1];
			return tempArr;
		};
		tempArr[0] = arr[1];
		tempArr[1] = arr[0];
		return tempArr;
	};
};

class DescSwapTest implements SwapCallable {
	public int[] call (int arr[]) {
		int[] tempArr = new int[2];
		if (arr[0] < arr[1]) {
			tempArr[0] = arr[1];
			tempArr[1] = arr[0];
			return tempArr;
		};
		tempArr[0] = arr[0];
		tempArr[1] = arr[1];
		return tempArr;
	};
};
*/


//interface to choose between odd and even test in the body of the same method inside the NumberProcessing (main) class
interface OddEvenCallable {
    public boolean call(int num);
};

class OddTest implements OddEvenCallable {
    public boolean call(int num) {
        if (num % 2 == 1) {
            return true;
        } else {
            return false;
        }
    }
};

class EvenTest implements OddEvenCallable {
    public boolean call(int num) {
        if (num % 2 == 1) {
            return false;
        } else {
            return true;
        }
    }
};

class BlankTestOddEven implements OddEvenCallable {
    public boolean call(int num) {
        return true;
    };
};


//interface to choose between the "or" or "and" division test in the body of the same method inside the NumberProcessing (main) class
interface DivOrAndCallabele {
    public boolean call(int num1, int divNum1, int divNum2);
};

class DivOrTest implements DivOrAndCallabele {
    public boolean call(int num1, int divNum1, int divNum2) {
        if ((num1 % divNum1 == 0) || (num1 % divNum2 == 0)) {
            return true;
        } else {
            return false;
        }
    }
};

class DivAndTest implements DivOrAndCallabele {
    public boolean call(int num1, int divNum1, int divNum2) {
        if ((num1 % divNum1 == 0) && (num1 % divNum2 == 0)) {
            return true;
        } else {
            return false;
        }
    }
};

class DivBlankTest implements DivOrAndCallabele {
    public boolean call(int num1, int num2, int divNum) {
        return true;
    };
};

//failed implementation of GCD/LCM interface, possible to redo, but the similar task was solved twice already with or/and and odd/even interfaces
interface GCDCallable {
    public int call(int num1, int num2) throws Exception;
};

class GCDTest implements GCDCallable {
    public int call(int num1, int num2) throws Exception {
        int tempMax = 0;
        int tempMin = 0;

        //wrapping the case for zeroes and negative values
        if (num1 == 0 || num2 == 0) {
            throw new Exception("Error in a call of euclAlgGCD method: can't pass zeroes as arguments");
        };

        //ordering the passed arguments in order to invoke Euclidean algorithm
        //(maybe a boolean condition would work better?)
        if (num1 > num2) {
            tempMax = num1;
            tempMin = num2;
        } else {
            tempMax = num2;
            tempMin = num1;
        };

        //getting rid of the case where one number is a multiple of another
        if (tempMax % tempMin == 0) {
            //int[] outArr = {tempMin, tempMax};
            return Math.abs(tempMin);
        };

        //working through the case where GCD gets found in 1 iteration
        int r1 = tempMax % tempMin;
        int r2 = tempMin % r1;
        if (r2 == 0) {
            //int[] outArr = {r1, (tempMax*tempMin)/r1};
            return Math.abs(r1);
        };

        //initializing a boolean var as a failsafe
        boolean flag = false;

        /* writing a code block that implements the algorithm
         * and tries to snatch the final remainder */
        while (!flag) {
            r1 = r1 % r2;
            if (r1 == 0) {
                flag = true;
                //int[] outArr = {r2, (tempMax*tempMin)/r2};
                return Math.abs(r2);
            };
            r2 = r2 % r1;
            if (r2 == 0) {
                flag = true;
                //int[] outArr = {r1, (tempMax*tempMin)/r1};
                return Math.abs(r1);
            };
        };

        throw new Exception("euclAlgGCD method didn't work");

    }
};

interface LCMCallable {
    public int call(int num1, int num2) throws Exception;
};

class LCMTest implements LCMCallable {
    public int call(int num1, int num2) throws Exception {
        int tempMax = 0;
        int tempMin = 0;
        //wrapping the case for zeroes and negative values
        if (num1 == 0 || num2 == 0) {
            throw new Exception("Error in a call of euclAlgLCM method: can't pass zeroes as arguments");
        };

        //ordering the passed arguments in order to invoke Euclidean algorithm
        //(maybe a boolean condition would work better?)
        if (num1 > num2) {
            tempMax = num1;
            tempMin = num2;
        } else {
            tempMax = num2;
            tempMin = num1;
        };

        //getting rid of the case where one number is a multiple of another
        if (tempMax % tempMin == 0) {
            //int[] outArr = {tempMin, tempMax};
            return Math.abs(tempMax);
        };

        //working through the case where GCD gets found in 1 iteration
        int r1 = tempMax % tempMin;
        int r2 = tempMin % r1;
        if (r2 == 0) {
            //int[] outArr = {r1, (tempMax*tempMin)/r1};
            return Math.abs((tempMax*tempMin)/r1);
        };

        //initializing a boolean var as a failsafe
        boolean flag = false;

        /* writing a code block that implements the algorithm
         * and tries to snatch the final remainder */
        while (!flag) {
            r1 = r1 % r2;
            if (r1 == 0) {
                flag = true;
                //int[] outArr = {r2, (tempMax*tempMin)/r2};
                return Math.abs((tempMax*tempMin)/r2);
            };
            r2 = r2 % r1;
            if (r2 == 0) {
                flag = true;
                //int[] outArr = {r1, (tempMax*tempMin)/r1};
                return Math.abs((tempMax*tempMin)/r1);
            };
        };

        throw new Exception("euclAlgLCM method didn't work");
    };
};


//this is to sort numbers by frequency in descending order (task 10)
class BeanRecord {

    int value;
    int reps;

    public BeanRecord() {
        this.value = 0;
        this.reps = 0;
    };

    public BeanRecord(int val) {
        this.value = val;
        this.reps = 1;
    };

    public BeanRecord(int val, int rep) {
        this.value = val;
        this.reps = rep;
    };

    public int getValue() {
        return this.value;
    };

    public int getReps() {
        return this.reps;
    };

    public void setValue (int val) {
        this.value = val;
    };

    public void setReps (int rep) {
        this.reps = rep;
    };

    public void incrReps () {
        this.reps = this.reps + 1;
    };

    public boolean equals(Object o) {

        //if the object is compared to itself
        if (o == this)
            return true;

        //if the object isn't even an instance of BeanRecord
        if(!(o instanceof BeanRecord))
            return false;

        //typecasting the object to compare the data members
        BeanRecord bean = (BeanRecord) o;

        //comparing the data members and returning a boolean value
        return Integer.compare(this.value, bean.value) == 0;
    };

};

public class NumberProcessing {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int[] arr = numberInitFile();
        if (arr.length == 0) {
            System.out.println("File does not contain any numbers");
            scan.close();
            return;
        };

        System.out.println("Choose an operation:");
        System.out.println("[1]. Odd and even numbers");
        System.out.println("[2]. Min and max values");
        System.out.println("[3]. Numbers that divide by 3 or 9");
        System.out.println("[4]. Numbers that divide by 5 and 7");
        System.out.println("[5]. Bubble sorted elemenents, by absolute values in descending order");
        System.out.println("[6]. All 3-digit numbers without repeating digits");
        System.out.println("[7]. LCD and LCM of numbers inputted");
        System.out.println("[8]. Prime numbers");
        System.out.println("[9]. Sorted numbers, either in ascending or descending order");
        System.out.println("[10]. Numbers sorted by frequency, in descending order");
        System.out.println("[11]. \"Happy\" numbers");
        System.out.println("[12]. Pick out Fibonacci numbers");
        System.out.println("[13]. Numbers-palindromes");
        System.out.println("[14]. Elements that are equal to the mean average of neighboring elements");
        System.out.println("[15]. Period of a decimal fraction p = m/n for the first two positive");
        System.out.println("      integers n and m, which are also neighboring elements");
        System.out.println("[16]. Build a Pascal's triangle for a first positive integer");

        String myChoice = scan.next();
        switch (myChoice.toLowerCase()) {
            case "1":
                try {
                    System.out.println("Odd numbers: ");
                    intArrOutput(filterOddEvenFun(arr, filterOddEvenChoices.ODD));
                    System.out.println();
                    System.out.println("Even numbers: ");
                    intArrOutput(filterOddEvenFun(arr, filterOddEvenChoices.EVEN));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "2":
                System.out.print("Displaying min number: ");
                System.out.println(minValue(arr));
                System.out.print("Displaying max number: ");
                System.out.println(maxValue(arr));
                break;
            case "3":
                try {
                    System.out.println("Displaying number that divide either by 3 or 9: ");
                    intArrOutput(filterOrAnd(arr, filterOrAndChoices.OR, 3, 9));
                    System.out.println();
                } catch (Exception ex) {
                    ex.printStackTrace();
                };
                break;
            case "4":
                try {
                    System.out.println("Displaying number that divide either by 3 or 9: ");
                    intArrOutput(filterOrAnd(arr, filterOrAndChoices.AND, 5, 7));
                    System.out.println();
                } catch (Exception ex) {
                    ex.printStackTrace();
                };
                break;
            case "5":
                System.out.println("Displaying bubble sorted array in descending order: ");
                intArrOutput(weirdSortDescAbs(arr));
                System.out.println();
                break;
            case "6":
                int sameDigitCountDigits = 3;
                System.out.println("Displaying elements of array that have " + sameDigitCountDigits + " digits: ");
                intArrOutput(sameDigitCount(arr, sameDigitCountDigits));
                System.out.println();
                break;
            case "7":
                //invoking GCD through try-catch block
                System.out.println("Displaying GCD of all 3-digit numbers:");
                try {
                    System.out.println(arrGCD(sameDigitCount(arr, 3)));
                } catch (Exception ex) {
                    ex.printStackTrace();
                };

                //invoking LCM through try-catch block
                System.out.println("Displaying LCM of all 3-digit numbers:");
                try {
                    System.out.println(arrLCM(sameDigitCount(arr, 3)));
                } catch (Exception ex) {
                    ex.printStackTrace();
                };
                break;
            case "8":
                //Eratosthenes's sieve
                //it's necessary only to check division by primes less than or equal to sqrt(n), where n stands for the largest number to sieve
                System.out.println("Displaying primes: ");
                try {
                    intArrOutput(eraSieve(arr));
                } catch (IOException iex) {
                    iex.printStackTrace();
                };
                break;
            case "9":
                System.out.print("Displaying initial array: ");
                intArrOutput(arr);
                System.out.println();
                System.out.print("Bubblesorted array in ascending order: ");
                intArrOutput(bubbleSortAsc(arr));
                System.out.println();
                System.out.print("Bubblesorted array in descending order: ");
                intArrOutput(bubbleSortDesc(arr));
                System.out.println();
                System.out.print("Quicksorted array in ascending order: ");
                intArrOutput(quickSortAsc(arr));
                System.out.println();
                System.out.print("Quicksorted array in descending order: ");
                intArrOutput(quickSortDesc(arr));
                break;
            case "10":
                System.out.println("Numbers sorted by frequency in descending order: ");
                intArrOutput(sortFreq(arr));
                break;
            case "11":
                System.out.println("Happy numbers: ");
                intArrOutput(happyNums(arr));
                break;
            case "12":
                int fibNum = 25;
                System.out.println("Displaying first " + fibNum +" fibonacci numbers:");
                intArrOutput(fib(fibNum));
                break;
            case "13":
                System.out.println("Numbers-palindromes: ");
                intArrOutput(paliArrCheck(arr));
                break;
            case "14":
                System.out.println("Elements that are mean average of neighboring elements: ");
                intArrOutput(meanAvArrCheck(arr));
                break;
            case "15":
                System.out.println("Displaying the fraction: ");
                try {
                    System.out.println(fracPeriod(intLook(arr)));
                } catch (Exception ex) {
                    ex.printStackTrace();
                };
                break;
            case "16":
                int pascNum = 1;
                int pascOrder = 20;
                int[][] pascRes = pascalBuild(pascNum, pascOrder);
                intArrArrOut(pascRes, intArrArrMaxLength(pascRes));
                break;
            case "exit":
                System.out.println("Exiting...");
                break;
            default: System.out.println("Invalid choice value");
                break;
        };

        scan.close();
        System.out.println();
        System.out.println("Aborting...");

    };

    private static int[] numberInitFile() {

        String token1 = "";
        int i = 0;

        List<String> temps = new ArrayList<String>();

        try {
            File file1 = new File("src/NumInput.txt");
            System.out.print("File existing: ");
            System.out.println(file1.exists());
            Scanner inFile1 = new Scanner(file1).useDelimiter(",\\s*");


            while (inFile1.hasNext()) {
                token1 = inFile1.next();
                temps.add(token1);
            };

            inFile1.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        };

        String[] tempsArray = temps.toArray(new String[0]);
        int[] intArray = new int[tempsArray.length];

        System.out.println("Displaying the read string-class array:");
        for (i = 0; i < tempsArray.length; i++) {
            System.out.print(tempsArray[i]+" ");
            intArray[i] = Integer.parseInt(tempsArray[i]);
        }

        System.out.println();

        return intArray;

    };

    enum filterOddEvenChoices {
        ODD,
        EVEN,
        BLANK
    };

    public static boolean invokeOddEven (OddEvenCallable callable, int num) {
        return callable.call(num);
    };

    public static int[] filterOddEvenFun (int[] arr, filterOddEvenChoices inVal) throws Exception {
        OddEvenCallable cmdFilter = new BlankTestOddEven();
        if (filterOddEvenChoices.ODD == inVal) {
            cmdFilter = new OddTest();
        } else if (filterOddEvenChoices.EVEN == inVal) {
            cmdFilter = new EvenTest();
        } else throw new Exception("filterOddEvenFun method doesn't recognize the passed method");

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (invokeOddEven(cmdFilter, arr[i]))
                list.add(arr[i]);
        };

        int[] arrOut = list.stream().mapToInt(Integer::intValue).toArray();
        return arrOut;

    };


    private static int minValue (int[] arr) {
        int i = 0;
        int temp = arr[0];
        for (i = 0; i < arr.length; i++ ) {
            if (arr[i] < temp)
                temp = arr[i];
        };
        return temp;
    };

    private static int maxValue (int[] arr) {
        int i = 0;
        int temp = arr[0];
        for (i = 0; i < arr.length; i++ ) {
            if (arr[i] > temp)
                temp = arr[i];
        };
        return temp;
    };

    //just in case it's possible to compare absolute values of numbers through the following code
	/*
	private static int maxAbsValue (int[] arr) {
		int i = 0;
		int temp = arr[0];
		for (i = 0; i < arr.length; i++ ) {
			if (Math.abs(arr[i]) > Math.abs(temp))
				temp = arr[i];
		};
		return temp;
	};
	*/



    enum filterOrAndChoices {
        OR,
        AND,
        BLANK
    };

    public static boolean invokeOrAnd (DivOrAndCallabele callable, int num1, int divBy1, int divBy2) {
        return callable.call(num1, divBy1, divBy2);
    };

    public static int[] filterOrAnd (int[] arr, filterOrAndChoices inVal, int divBy1, int divBy2) throws Exception {
        DivOrAndCallabele cmdFilter = new DivBlankTest();
        if (filterOrAndChoices.OR == inVal) {
            cmdFilter = new DivOrTest();
        } else if (filterOrAndChoices.AND == inVal) {
            cmdFilter = new DivAndTest();
        } else throw new Exception("filterOrAnd method doesn't recognize the passed method");

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (invokeOrAnd(cmdFilter, arr[i], divBy1, divBy2))
                list.add(arr[i]);
        };

        int[] arrOut = list.stream().mapToInt(Integer::intValue).toArray();;
        return arrOut;

    };

    //method that finds the maximum element from a given position
    private static int maxEl (int[] arr, int index) {
        int j = index;
        int maxInd = index;
        for (j = index; j < arr.length; j++) {
            if (Math.abs(arr[j]) > Math.abs(arr[maxInd]))
                maxInd = j;
        };
        return maxInd;
    };

    private static int[] weirdSortDescAbs (int[] arr) {

        //null case
        if (arr == null)
            return null;

        //1-element case
        if (arr.length == 1)
            return arr;

        int i = 0;
        int[] tempArr = Arrays.copyOf(arr, arr.length);
        int tempInd = 0;
        int replMax = tempArr[0];
        //int replInd = 0;

        for (i = 0; i < tempArr.length - 1; i++) {

            //finding the maximum value
            tempInd = maxEl(tempArr, i);

            //swapping the elements
            replMax = tempArr[i];
            tempArr[i] = tempArr[tempInd];
            tempArr[tempInd] = replMax;

        };

        return tempArr;

    };

    private static int[] sameDigitCount (int[] arr, int digitCount) {

        //iterator variable for the first loop
        int i = 0;

        //using ArrayList object in stand of dynamic array to filter out 3-digits
        ArrayList<Integer> arrList = new ArrayList<>();
        for (i = 0; i < arr.length; i++ ) {
            if ((Math.abs(arr[i]) % Math.floor(Math.pow(10, digitCount - 1)) < Math.abs(arr[i])) && (Math.abs(arr[i]) % Math.floor(Math.pow(10, digitCount)) == Math.abs(arr[i])) && !repNums(Math.abs(arr[i])))
                arrList.add(arr[i]);
        };

        int[] resArr = arrList.stream().mapToInt(Integer::intValue).toArray();

        return resArr;
    };

    //this function will simplify the condition check at threeDigits method and deal with repeating integers
    private static boolean repNums (int inValue) {

        //checking for 1-digit case
        if (Math.abs(inValue) < 10)
            return false;

        //declaring a char array to work with
        String tempStr = Integer.toString(inValue);
        char[] chArr = tempStr.toCharArray();

        //the implementation is similar to bubblesort, the idea is to fix an element and iterate through all elements that FOLLOW it, checking the equality
        //boolean flag = false;
        //char temp = chArr[0];
        for (int i = 0; i < chArr.length - 1; i++) {
            for (int j = i + 1; j < chArr.length; j++) {
                if (chArr[i] == chArr[j])
                    return true;
            };
        };

        return false;
    };

    private static void intArrOutput (int[] arr) {
        int i = 0;
        System.out.println("Displaying contents of array: ");
        for (i = 0; i < arr.length; i++ ) {
            System.out.print(arr[i] + " ");
        };
    };

    public static int invokeGCD (GCDCallable gcdcallable, int num1, int num2) throws Exception {
        return gcdcallable.call(num1, num2);
    };

    public static int invokeLCM (LCMCallable lcmcallable, int num1, int num2) throws Exception {
        return lcmcallable.call(num1, num2);
    };

    //reduce array to GCD method
    public static int arrGCD (int[] inpArr) throws Exception {
        GCDCallable cmd = new GCDTest();

        //0 elements
        if (inpArr.length == 0) {
            throw new Exception ("No numbers to return GCD from");
        };

        //1 element
        if (inpArr.length == 1) {
            return inpArr[0];
        };

        //2 and more elements
        int i = 0;
        int temp = inpArr[0];
        for (i = 0; i < inpArr.length - 1; i++) {
            temp = invokeGCD(cmd, temp, inpArr[i+1]);
        };
        return temp;
    };

    //reduce array to LCM method
    public static int arrLCM (int[] inpArr) throws Exception {
        LCMCallable cmd = new LCMTest();

        //0 elements
        if (inpArr.length == 0) {
            throw new Exception ("No numbers to return LCM from");
        };

        //1 element
        if (inpArr.length == 1) {
            return inpArr[0];
        };

        //2 and more elements
        int i = 0;
        int temp = inpArr[0];
        for (i = 0; i < inpArr.length - 1; i++) {
            temp = invokeLCM(cmd, temp, inpArr[i+1]);
        };
        return temp;
    };

    //Eratosthenes's sieve method
    //Note: it's necessary to define a zero-filtering method, and also cast off negative values since primes are defined only for natural numbers
    public static int[] eraSieve (int[] inpArr) throws IOException {
        //defining a filtered array
        int[] filtArray = zeroNegOneFilter(inpArr);

        if (filtArray == null)
            return null;

        if (maxValue(filtArray) == 2)
            return filtArray;

        //iMax is the value to which it's necessary to check the division
        int iMax = Integer.valueOf((int) Math.floor(Math.sqrt(maxValue(filtArray))));

        //wrapping the creation of the sieveArr in a try/catch block
        try {
            int[] sieveArr = primeCreate(iMax);
            for (int i = 0; i < sieveArr.length; i++) {
                filtArray = multipleFilter(filtArray, sieveArr[i]);
            };
            return zeroNegOneFilter(filtArray);
        } catch (IOException iex) {
            iex.printStackTrace();
            //TODO: handle exception
        };

        throw new IOException ("eraSieve method error: try/catch block failed");

    };

    public static int[] zeroNegOneFilter (int[] arr) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 1)
                list.add(arr[i]);
        };

        int[] arrOut = list.stream().mapToInt(Integer::intValue).toArray();
        return arrOut;

    };

    public static int[] multipleFilter (int[] arr, int mult) {

        int[] arrOut = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < arr.length; i++) {
            if ((arrOut[i] != mult) && (arrOut[i] % mult == 0))
                arrOut[i] = 0;
        };

        return arrOut;

    };

    public static int[] primeCreate (int max) throws IOException {

        if (max < 2)
            throw new IOException ("primeCreate method error: max can't be less than 2");

        //creating and filling the array of consecutive numbers
        int[] arr = new int[max - 1];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i+2;
        };

        //prime filtering the numbers
        int primeMax = Integer.valueOf((int) Math.floor(Math.sqrt(maxValue(arr))));


        for (int j = 0; j < primeMax - 1; j++) {
            if (arr[j] != 0)
                arr = multipleFilter(arr, arr[j]);
        };

        int[] zerolessArr = zeroNegOneFilter(arr);


        return zerolessArr;

    };

    //it's necessary to consider the null and 1-element cases; for 2-element and 3-element arrays it's enough to use bubblesort
    public static int[] quickSortAsc (int[] inArr) {

        int[] arr = Arrays.copyOf(inArr, inArr.length);

        //for references that aren't explicitly set the default value will be null
        if ((arr == null) || (arr.length == 0))
            return null;

        //1-element case
        if (arr.length == 1)
            return arr;

        //2-element and 3-element case
        if ((arr.length == 2) || (arr.length == 3)) {
            return bubbleSortAsc(arr);
        };

        // the quicksort thingy itself
        int pivotInd = arr.length - 1;
        int tempInd = 0;
        int tempEl = arr[pivotInd];
        int tempEl2 = arr[pivotInd - 1];
        int tempEl3 = arr[pivotInd];
        while (tempInd < pivotInd) {

            //case when the pivot and running index are neighboring
            if (arr[pivotInd] < arr[tempInd] && arr[pivotInd] == arr[tempInd + 1]) {

                //swap
                tempEl3 = arr[pivotInd];
                arr[pivotInd] = arr[tempInd];
                arr[tempInd] = tempEl3;

                //decrementing the pivot index because the element itself moved 1 index left during the swap
                pivotInd--;

                //case when the pivot and running index are not neighboring
            } else if (arr[pivotInd] < arr[tempInd]) {

                //convoluted swap with pivot element, before-pivot element and running index element getting swapped in succession
                tempEl = arr[pivotInd];
                arr[pivotInd] = arr[tempInd];

                tempEl2 = arr[pivotInd - 1];
                arr[pivotInd - 1] = tempEl;
                arr[tempInd] = tempEl2;

                //decrementing the pivot index because the element itself moved 1 index left during the swap
                pivotInd--;
            } else {
                //since the element at the running index doesn't need to be moved (because the both "if" conditions failed), the running index gets incremented
                tempInd++;
            };

        };
        //packing the pivot value into array
        int[] tempArr = new int[1];
        tempArr[0] = arr[pivotInd];

        //recursive call of quicksort, the if condition is to avoid dealing with zero-length arrays in the array concat method
        //TO-DO: maybe make the concat method deal with them?
        if (pivotInd == 0) {
            return concat (tempArr, quickSortAsc(Arrays.copyOfRange(arr, pivotInd + 1, arr.length)));
        } else if (pivotInd == arr.length - 1) {
            return concat (quickSortAsc(Arrays.copyOfRange(arr, 0, pivotInd)), tempArr);
        };
        return concatAll(quickSortAsc(Arrays.copyOfRange(arr, 0, pivotInd)), tempArr, quickSortAsc(Arrays.copyOfRange(arr, pivotInd + 1, arr.length)));

    };

    //see the quickSortAsc comments, the code is analogous for the descending variation of the method
    public static int[] quickSortDesc (int[] inArr) {

        int[] arr = Arrays.copyOf(inArr, inArr.length);

        //for references that aren't explicitly set the default value will be null
        if ((arr == null) || (arr.length == 0))
            return null;

        //1-element case
        if (arr.length == 1)
            return arr;

        //2-element and 3-element case
        if ((arr.length == 2) || (arr.length == 3)) {
            return bubbleSortDesc(arr);
        };

        int pivotInd = arr.length - 1;
        int tempInd = 0;
        int tempEl = arr[pivotInd];
        int tempEl2 = arr[pivotInd - 1];
        int tempEl3 = arr[pivotInd];
        while (tempInd < pivotInd) {

            if (arr[pivotInd] > arr[tempInd] && arr[pivotInd] == arr[tempInd + 1]) {

                tempEl3 = arr[pivotInd];
                arr[pivotInd] = arr[tempInd];
                arr[tempInd] = tempEl3;

                pivotInd--;

            } else if (arr[pivotInd] > arr[tempInd]) {

                tempEl = arr[pivotInd];
                arr[pivotInd] = arr[tempInd];

                tempEl2 = arr[pivotInd - 1];
                arr[pivotInd - 1] = tempEl;
                arr[tempInd] = tempEl2;

                pivotInd--;
            } else {
                tempInd++;
            };

        };
        int[] tempArr = new int[1];
        tempArr[0] = arr[pivotInd];

        if (pivotInd == 0) {
            return concat (tempArr, quickSortDesc(Arrays.copyOfRange(arr, pivotInd + 1, arr.length)));
        } else if (pivotInd == arr.length - 1) {
            return concat (quickSortDesc(Arrays.copyOfRange(arr, 0, pivotInd)), tempArr);
        };
        return concatAll(quickSortDesc(Arrays.copyOfRange(arr, 0, pivotInd)), tempArr, quickSortDesc(Arrays.copyOfRange(arr, pivotInd + 1, arr.length)));

    };
/*
	public static int[] ascSwap (int[] arr) {
		int[] tempArr = new int[2];
		if (arr[0] < arr[1]) {
			tempArr[0] = arr[0];
			tempArr[1] = arr[1];
			return tempArr;
		};
		tempArr[0] = arr[1];
		tempArr[1] = arr[0];
		return tempArr;
	};

	public static int[] descSwap (int[] arr) {
		int[] tempArr = new int[2];
		if (arr[0] > arr[1]) {
			tempArr[0] = arr[0];
			tempArr[1] = arr[1];
			return tempArr;
		};
		tempArr[0] = arr[1];
		tempArr[1] = arr[0];
		return tempArr;
	};
*/

    //fibonacci numbers in int
    public static int[] fib(int ofNum) {
        if (ofNum == 1) {
            int[] arr = {0};
            return arr;
        } else if (ofNum == 2) {
            int[] arr = {1};
            return concat(fib(1), arr);
        };
        int[] temp = fib(ofNum-1);
        int[] arr = {temp[temp.length-2] + temp[temp.length-1]};
        return concat(temp, arr);
    };

    //generic-type function for concatting arrays
    public static <T> T[] concat (T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    };

    public static int[] concat (int[] first, int[] second) {
        int[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    };


    //concat an arbitrary finite amount of arrays
    public static <T> T[] concatAll (T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        };

        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest)	 {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        };
        return result;
    };

    public static int[] concatAll (int[] first, int[]... rest) {
        int totalLength = first.length;
        for (int[] array : rest) {
            totalLength += array.length;
        };

        int[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (int[] array : rest)	 {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        };
        return result;
    };

    //bubblesort for small arrays in quicksort
    public static int[] bubbleSortAsc(int[] inArr) {
        int[] arr = Arrays.copyOf(inArr, inArr.length);
        int temp = arr[0];
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                };
            };
        };

        return arr;
    };

    public static int[] bubbleSortDesc(int[] inArr) {
        int[] arr = Arrays.copyOf(inArr, inArr.length);
        int temp = arr[0];
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] < arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                };
            };
        };

        return arr;
    };

    //TO-DO				: sort array numbers by frequency, in descending order
    //idea 				: use or model 2d objects (records, 2d arrays, 2d lists?) that store both the unique array value, and the count for the each one
    //alternative idea	: it's possible to try using sort methods to simplify counting the same elements
    //output			: don't reduce the amount of numbers
    //2d record idea	: it's worth trying to define a class with two fields (i.e. the record), and then an ArrayList that would store the individual class objects

    public static int[] sortFreq (int[] arr) {

        ArrayList<BeanRecord> list = new ArrayList<>();
        BeanRecord token = new BeanRecord();

        int temp = 0;

        for (int i = 0; i < arr.length; i++) {
            token = new BeanRecord(arr[i]);
            temp = list.indexOf(token);
            if (temp == -1) {
                list.add(token);
            } else {
                token = list.get(temp);
                token.incrReps();
                list.set(temp, token);
            };
        };

        BeanRecord[] arrUnique = new BeanRecord[list.size()];
        arrUnique = list.toArray(arrUnique);

        arrUnique = bubbleSortDescBeanRec(arrUnique);

        int[] result = beanRecToInt(arrUnique);
        return result;

    };

    public static BeanRecord[] bubbleSortDescBeanRec (BeanRecord[] inArr) {
        BeanRecord[] arr = Arrays.copyOf(inArr, inArr.length);
        BeanRecord temp = arr[0];
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j].getReps() < arr[j+1].getReps()) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                };
            };
        };

        return arr;
    };

    public static int[] beanRecToInt (BeanRecord[] inArr) {

        int[] arr = new int[inArr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = inArr[i].getValue();
        };

        return arr;

    };

    //to find happy numbers, one way is to use a perfect digital invariant function, where p = 2, and b = 10
    //it's possible to calculate the floored part of logarithm of n by the base of b = 10, where n is the number that gets checked
    //to deal with the java's natural log function and convert it to a different base, it's possible to calculate ln(n)/ln(b), which is equal to log of n by base b
    //the logarithm is calculated to know how much the number should get divided by 10 if the "for" loop gets implemented
    //but it's possible to get the job done with the "while" loop

    public static int pdiFunc (int num, int base, int power) {

        int numClone = Integer.valueOf(num);

        double sum = 0d;
        double temp = 0d;

        //the loop calculates the sum of squares of digits
        //note the exit condition: numClone will be equal to zero once every digit got extracted
        while (numClone > 0) {
            temp = numClone % base;
            sum += Math.pow(temp, power);
            numClone = (int) (numClone - temp) / 10;
        };

        return Integer.valueOf((int) sum);

    };

    //after pdiFunc got defined, it's possible to define a boolean function that will check for happiness
    //it will keep adding numbers for as long as they're not present in the "occur" list
    //and if it finds a number equal to one, the number is happy
    //otherwise the number will loop sooner or later, which will flag it as being unhappy
    public static boolean isHappy (int num, int base, int power) {

        ArrayList<Integer> occur = new ArrayList<>();

        int temp = pdiFunc(num, base, power);

        while (occur.indexOf(temp) == -1) {
            occur.add(temp);
            if (temp == 1)
                return true;
            temp = pdiFunc(temp, base, power);
        };

        return false;

    };

    //and finally, the filter function gets implemented, which uses boolean isHappy to reduce the input array to an array of happy numbers only
    public static int[] happyNums (int[] arr) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (isHappy(arr[i], 10, 2))
                list.add(arr[i]);
        };

        int[] arrOut = list.stream().mapToInt(Integer::intValue).toArray();
        return arrOut;

    };

    //to find numbers-palindromes, it's necessary to check equality of numbers until the floored half of length from either side

    public static boolean paliCheck (int num) {

        if (num < 0) return false;

        if (num < 10) return true;

        char[] arr = String.valueOf(num).toCharArray();

        int halfLength = (int) Math.floor(arr.length/2);

        for (int i = 0; i < halfLength; i++) {
            if (arr[i] != arr[arr.length - 1 - i]) {
                return false;
            };
        };

        return true;

    };

    public static int[] paliArrCheck (int[] arr) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (paliCheck(arr[i]))
                list.add(arr[i]);
        };

        int[] arrOut = list.stream().mapToInt(Integer::intValue).toArray();
        return arrOut;

    };

    //to filter out elements that are the mean average of the neighboring ones, we write a method that iterates through inputted array and ignores fringe values

    public static int[] meanAvArrCheck (int[] arr) {

        if (arr.length < 3) return null;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < arr.length - 1; i++) {
            if ( arr[i] == (arr[i-1] + arr[i+1]) / 2 )
                list.add(arr[i]);
        };

        int[] arrOut = list.stream().mapToInt(Integer::intValue).toArray();
        return arrOut;

    };

    //first it's necessary to find two consecutive positive integers
    //note: it returns either null or int[2] array

    public static int[] intLook (int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        if (arr.length < 2)
            return null;

        //2-element case gets skipped by the loop
        if (arr.length == 2) {
            if (arr[0] > 0 && arr[1] > 0) {
                int[] result = {arr[0], arr[1]};
                return result;
            };
        };

        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] > 0 && arr[i + 1] > 0) {
                int[] result = {arr[i], arr[i+1]};
                return result;
            };
        };

        return null;

    };

    //after the intLook method fetches the integers, it's necessary to work with the fraction
    //the idea is to look for at least a single repetition of a sequence of numbers in the fractional part
    //the algorithm should go as follows:
    //1. The first immediate digit gets picked out.
    //	 If there's no repetition of this integer later on, the algorithm immediately goes to the next one.
    //	 If there is a repetition, the algorithm estimates the distance between the integers, and fetches a substring containing the first digit and all digits in between
    //   the same digits.
    //2. Then this substring gets compared to the rest of the digit sequence, truncating the part that gets compared to the last sequence which will likely be partially cut off
    //	 If the check passes, we return the substring
    //	 If it doesn't, the method will keep going through substring, ultimately returning either null or error if it still doesn't find anything when it reaches str.length - 3

    static String fracPeriod (int[] nums) throws Exception {

        double frac = (double) nums[0] / (double) nums[1];

        String str = String.valueOf(frac);

        System.out.println("frac to str conversion result is " + str);

        str = str.substring(str.indexOf(".") + 1, str.length() - 1);

        //System.out.println("str whole part formatting result is " + str);

        String temp = "";
        String temp2 = "";

        String tempStr = "";
        String tempStr2 = "";

        for (int i = 0; i < str.length() - 4; i++) {



            temp = str.substring(i, i + 1);

            //System.out.println("i loop; temp value is " + temp);

            for (int j = i + 1; j < str.length() - 1; j++) {

                temp2 = str.substring(j, j + 1);

                //System.out.println("j loop; temp2 value is " + temp2);

                if (temp.equals(temp2)) {

                    tempStr = str.substring(i, j);

                    //System.out.println("first if condition; tempStr value is " + temp);

                    tempStr2 = str.substring(j, str.length());

                    //System.out.println("first if condition; tempStr2 value is " + temp2);

                    int count = 0;

                    while ((tempStr2.length() > tempStr.length()) && count < 30) {
                        if (!tempStr2.startsWith(tempStr))
                            throw new Exception("fracPeriod method error 1");

                        tempStr2 = tempStr2.substring(tempStr.length(), tempStr2.length());
                        //System.out.println("while loop; tempStr2 value is " + tempStr2);

                        count++;
                    };

                    if (tempStr.startsWith(tempStr2))
                        return tempStr;

                };

            };


        };

        throw new Exception("fracPeriod method error 2");

    };


    static int[][] pascalBuild (int num, int order) {

        //trivial cases
        if (order < 0)
            return null;

        if (order == 1) {
            return null;
        };

        //the triangle the method will try to build
        String[] result = new String[order];

        for (int i = 0; i < result.length; i++) {
            result[i] = new String("");
        }


        //initializing a triangle array
        int[][] arr = new int[order][];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[arr.length - i];
            //initializing the left side of triangle with num value
            arr[0][i] = num;
        };

        //filling up the rest
        for (int i = 1; i < arr.length; i++) {

            for (int j = 0; j < arr.length - i; j++) {

                if (j == 0) {

                    arr[i][j] = num;

                } else {

                    arr[i][j] = arr[i][j-1] + arr[i-1][j];

                };

            };

        };

        return arr;

    };


    public static void intArrArrOut (int[][] arr, int numLength) {

        int temp;

        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr[i].length; j++) {

                System.out.print(arr[i][j] + " ");
                temp = (int) Math.log10( (double) arr[i][j] ) + 1;

                for (int j2 = 0; j2 < numLength - temp; j2++) {

                    System.out.print(" ");

                };

            };

            System.out.println();

        };

    };

    public static int intArrArrMaxLength (int[][] arr) {

        int max = arr[0][0];

        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr[i].length; j++) {

                if (arr[i][j] > max)
                    max = arr[i][j];

            };


        };

        return (int) Math.log10( (double) max ) + 1;

    };



};
