import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SortExec  {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("ソートしたい数値の個数を入力してください");
        int arrayLength = scan.nextInt();
        System.out.println("マージ結果を出力する場合は1を入力してください");
        int outputFlag = scan.nextInt();
        scan.close();

        int[] randomArray = new int[arrayLength];
        Random rnd = new Random();

        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = rnd.nextInt(100);
        }

        // バブルソート実行
        long start = System.currentTimeMillis();
        int[] bubbleSortedArray =  bubbleSort(randomArray);
        long end = System.currentTimeMillis();
        System.out.println("バブルソート計算時間：" + (end - start)  + "ms");

        // マージソート実行
        start = System.currentTimeMillis();
        int[] mergeSortedArray = mergeSort(randomArray);
        end = System.currentTimeMillis();
        System.out.println("マージソート計算時間：" + (end - start)  + "ms");
        if (outputFlag == 1) {
            outputResult("バブルソート実行結果:", bubbleSortedArray);
            outputResult("マージソート実行結果", mergeSortedArray);
        }
    };

    public static void outputResult(String name, int[] array) {
        System.out.print(name + ":");
        for (int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i]);
        }
        System.out.println("");
    }

    public static int[] bubbleSort(int[] array){
        int tempNumber = 0;
        for(int i = 0; i < array.length - 1; i++){ // 配列内の数値全て
            for(int j = 0; j < array.length - 1; j++){ // 比較対象の数値
                if (array[j] > array[j + 1]){
                    tempNumber = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tempNumber;
                }
            }
        };
        return array;
    }

    public static int[] mergeSort(int[] array){
        if (array.length <= 1) {
            // 分割終了
            return array;
        }

        int size = array.length;
        int halfSize = size / 2;

        int[] leftArray = Arrays.copyOfRange(array, 0, halfSize);
        int[] rightArray = Arrays.copyOfRange(array, halfSize, size);

        // 分割
        mergeSort(leftArray);
        mergeSort(rightArray);

        // 統合
        return merge(array, leftArray, rightArray);
    }

    public static int[] merge(int[] array, int[] leftArray, int[] rightArray){
        int count = 0;
        int leftCount = 0;
        int rightCount = 0;

        while (leftCount < leftArray.length && rightCount < rightArray.length) {
            if (leftArray[leftCount] <= rightArray[rightCount]){
                array[count++] = leftArray[leftCount++];
            }
            else{
                array[count++] = rightArray[rightCount++];
            }
        }

        // 各配列に残っていれば追加
        while (leftCount < leftArray.length) {
            array[count++] = leftArray[leftCount++];
        }
        while (rightCount < rightArray.length) {
            array[count++] = rightArray[rightCount++];
        }

        return array;
    }
}
