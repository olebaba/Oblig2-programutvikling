//1.1
public class Rekursjon {
    static int sum(int x) {
        if (x <= 1) {
            return 1;
        }
        return x * sum(x - 1);
    }
//1.2
    static int pow(int base, int exponent) {
        if (exponent == 0)
            return 1;
        int temp = pow(base, exponent / 2);
        if (exponent % 2 == 0)
            return temp * temp;
        else
            return (base * temp * temp);
    }
//1.3
    static void reverseArray(int arr[], int start, int end) {
        int temp;
        if (start >= end)
            return;
        temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        reverseArray(arr, start + 1, end - 1);
    }

    static void printArray(int arr[], int size) {
        for (int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
        System.out.println("");
    }
//1.4
    public static int minstVerdi(int[] tall, int start, int last) {

        if (start == last) {
            return tall[start];
        }
        if (tall[start] <= tall[last]) {
            last -= 1;
        } else {
            start += 1;
        }
        return minstVerdi(tall, start, last);
    }

    public static int minsttall(int[] array, int teller){
        if(array.length==teller){
            return 0;
        }
        return Math.min(array[teller],minsttall(array, ++teller));
    }

    static int arr[] = {2, 3, 5, 20, 3};

  //1.5
    static int Heltall(int arr[], int l, int r, int x) {
        if (r < l)
            return -1;
        if (arr[l] == x)
            return l;
        if (arr[r] == x)
            return r;
        return Heltall(arr, l + 1, r - 1, x);
    }


    public static void main(String[] args) {
        System.out.println("Oppgave 1.1");
        System.out.println(sum(10));

        System.out.println("Oppagve 1.2");
        System.out.println(pow(3, 1));

        arr = new int[]{10, 72, 3, 4, 5, 6};
        reverseArray(arr, 0, 5);
        System.out.println("Oppgave 1.3");
        printArray(arr, 6);

        int x = 4;
        int element = Heltall(arr, 0, arr.length - 1, x);
        if (element != -1) System.out.println("Oppgave 1.5: " + "Element x er " + element);
        else System.out.println("Finner ikke element x");



    }
}