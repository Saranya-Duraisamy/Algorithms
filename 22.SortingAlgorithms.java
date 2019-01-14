class Solution{
    // In-Place, Stable, Space-O(1),
    // Time(Worst)-O(n^2)-descending order
    // Time(Best)-O(n)-ascending order
    static void bubbleSort(int[] arr){
        int length = arr.length;
        for(int i=0; i<length-1; i++){
            boolean swap = false;
            for(int j=i; j<length-i-1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swap = true;
                }
            }
            if(swap == false)
                break;
        }
    }

    // In-Place, Stable, Space-O(1),
    // Time(Worst)-O(n^2)-descending order
    static void insertionSort(int[] arr){
        int length = arr.length;
        for(int i=1; i<length; i++){
            int cur = arr[i];
            int j=i-1;
            for(; j>=0 && (arr[j] > cur); j--){
                arr[j+1] = arr[j];
            }
            arr[j+1] = cur;
        }
    }

    // Not Stable, to make it stable- move all elements from i to minIndex to i+1 to minIndex
    // and then store min in ith index
    static void selectionSort(int[] arr){
        int length = arr.length;
        for(int i=0; i<length-1;i++){
            int min = arr[i];
            int minIndex = i;
            for(int j=i+1; j<length; j++){
                if(min > arr[j]){
                    minIndex = j;
                    min = arr[j];
                }
            }
            if(i != minIndex) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

    static void merge(int[] arr, int start, int mid, int end){
        int left = start;
        int right = mid+1;
        int temp[] = new int[arr.length];
        int index = start;
        while((left <= mid) && (right <= end)) {
            if (arr[left] <= arr[right]) {
                temp[index++] = arr[left++];
            }
            else{
                temp[index++] = arr[right++];
            }
        }
        while(left <= mid){
            temp[index++] = arr[left++];
        }
        for(int loop=start; loop<index; loop++){
            arr[loop] = temp[loop];
        }
    }

    // Time Complexity-O(n log n), Space Complexity-O(n)
    static void mergeSort(int[] arr, int start, int end){
        if(start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid+1, end);
            merge(arr, start, mid, end);
        }
    }

    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(int[] arr, int start, int end) {
        int pivot = start;
        int i = start+1;
        int j = end;
        while(i < j){
            while((i <= end) && (arr[pivot] > arr[i])){
                i++;
            }
            while( (j > start) && (arr[pivot] < arr[j])) {
                j--;
            }
            if(i < j  && i <= end) {
                swap(arr, i, j);
            }
        }
        swap(arr, pivot, j);
        pivot = j;
        return pivot;
    }
    // Time Complexity- Average-O(n log n),Worst-O(n^2); Space Complexity-O(1)
    static void quickSort(int[] arr, int start, int end){
        if(start < end) {
            int pivot = partition(arr, start, end);
            quickSort(arr, start, pivot-1);
            quickSort(arr, pivot+1, end);
        }
    }

    // To sort in ascending order, max heap should be used
    static void heapify(int[] arr, int n, int i){
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if(left<n && arr[left]>arr[largest]){
            largest = left;
        }
        if(right<n && arr[right]>arr[largest]){
            largest = right;
        }
        if(i != largest){
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            // Heapify the modified subtree
            heapify(arr, n, largest);
        }
    }
    // Time Complexity-O(n log n); Space Complexity-O(1);
    // In-place sorting, Not stable
    static void heapSort(int[] arr){
        int n = arr.length;

        //Heapify all nodes with children
        for(int i= n/2-1; i>=0; i--){
            heapify(arr, n, i);
        }

        //Save the elements in ascending order and call heapify
        for(int i=n-1; i>=0; i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }

    }

    public static void main(String[] args){
        int arr[]= {6,2,3,5,4};
        //int arr[]= {6,5,4,3,2,1};
        //bubbleSort(arr);
        //insertionSort(arr);
        //selectionSort(arr);
        //mergeSort(arr, 0, arr.length-1);
        //quickSort(arr, 0, arr.length-1);
        heapSort(arr);
        for(int a: arr)
            System.out.print(a+",");
    }
}