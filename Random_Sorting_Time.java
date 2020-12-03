package project11_2;

import java.util.Scanner;

public class Random_Sorting_Time {
	
	//삽입 정렬
	private static void insertionSort(int[] data) {
		// TODO Auto-generated method stub
		int i,j,tmp = 0;
		for(i = 1; i<data.length;i++) {
			tmp = data[i];
			for(j = i; j> 0;j--) {
				if(data[j-1]>tmp)
					data[j] = data[j-1];
				else
					break;
			}
			data[j] = tmp;
		}	
	}

	//선택 정렬
	private static void selectionSort(int[] data) {
		int size = data.length;

        int min; //최소값을 인덱스 저장 변수
        int temp;
        
        for(int i=0; i<size-1; i++){ // size-1 : 마지막 요소는 자연스럽게 정렬됨
            min = i;
            for(int j=i+1; j<size; j++){
                if(data[min] > data[j]){
                    min = j;
                }
            }
            temp = data[min];
            data[min] = data[i];
            data[i] = temp;
        }
		
	}

	//버블 정렬
	private static void bubbleSort(int[] data) {
		int a = 0;
		for(int i = 0 ; i < data.length -1 ; i ++) {
			for(int j = data.length -1 ; j > i ; j --) {
				if(data[j]<data[j-1]) {
					a = data[j];
					data[j] = data[j-1];
					data[j-1] = a;
				}
			}
		}
	}

	//퀵 정렬
	private static void quickSort(int[] items, int first, int last) {
		if(first<last)
		{
			int pivotIndex = partition(items, first, last);
			
			quickSort(items, first, pivotIndex-1);  // 왼쪽 분할을 퀵정렬
	        quickSort(items, pivotIndex+1, last);  // 오른쪽 분할을 퀵정렬
		}	
	}

	private static int partition(int[] data, int first, int last) {
		// TODO Auto-generated method stub
		swap(data, first, (first+last)/2);
		int pivot = data[first];
		int tmp = first;
		first++;
		while (first <= last) {
			while (first < last && data[first] < pivot)
				first++;
			while (data[last] > pivot)
				last--;
			if (first < last)
				swap(data, first++, last--);
			else
				break;
		}
		swap(data, tmp, last);
		return last;
	}

	//힙 정렬
	public static void heapSort(int[] items) {
		int n = items.length;
		// 힙 생성
		for (int i = n/2 - 1; i >= 0; i--)
			reheap(items, i, n-1);	// moveDown과 같은 메소드

		// 루트에 있는 제일 큰 값을 배열의 끝부터 삽입하기 위해 	//데이터 교환, 교환 후 힙성질을 만족하도록 힙을 재구성
		for (int i = n-1; i >= 1; i--) {
			swap(items, 0, i);
			reheap(items, 0, i-1);
		}
	}


	private static void reheap(int[] items, int f, int l) {
		int maxChild, leftChild, rightChild;
		leftChild = 2*f + 1;
		while(leftChild <= l) {
			if(leftChild == l)
				maxChild = leftChild;
			else {
				rightChild = 2*f +2;
				if(items[leftChild] <= items[rightChild])
					maxChild=rightChild;
				else
					maxChild=leftChild;
			}
			if(items[f]<items[maxChild]) {
				swap(items, f, maxChild);
				f = maxChild;
				leftChild = 2*f +1;
			}
			else
				break;
		}
	}

	private static void swap(int[] items, int i, int j) {
		int tmp = items[i];
		items[i] = items[j];
		items[j] = tmp;
	}

	//난수 발생
	public static void generateItems(int[] items, int n) {
		for (int i = 0; i < items.length; i++)
			items[i] = (int)(n*Math.random());
	}

	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long startTime, endTime, elapsed;
		while (true) {
			System.out.print("\nEnter a number to sort: ");
			int n = in.nextInt();
			if (n <= 0) {
				System.out.println("Sorting done!");
				break;
			}
			else if (n <= 100000){
				int[] tmpItems = new int[n];
				generateItems(tmpItems, n);
				int[] items = new int[n]; //정렬 전 복사
				items = tmpItems.clone();
				
				startTime = System.currentTimeMillis();
				heapSort(items);
				endTime = System.currentTimeMillis();
				elapsed = endTime - startTime;
				System.out.println("Heap Sort Time = " + elapsed);
				//정렬 결과 20개 출력
				int t1 = 0;
				for (int a : items) {
					System.out.print(a + " ");
					t1++;
					if (t1 == 20)
						break;
				}
				System.out.println();

				items = tmpItems.clone();
				startTime = System.currentTimeMillis();
				quickSort(items, 0, items.length-1);
				endTime = System.currentTimeMillis();
				elapsed = endTime - startTime;
				System.out.println("QuickSort Time = " + elapsed);
				int t2  = 0;
				for (int a : items) {
					System.out.print(a + " ");
					t2++;
					if (t2 == 20)
						break;
				}
				System.out.println();
				
				items = tmpItems.clone();
				startTime = System.currentTimeMillis();
				bubbleSort(items);
				endTime = System.currentTimeMillis();
				elapsed = endTime - startTime;
				System.out.println("Bubble Sort Time = " + elapsed);

				items = tmpItems.clone();
				startTime = System.currentTimeMillis();
				selectionSort(items);
				endTime = System.currentTimeMillis();
				elapsed = endTime - startTime;
				System.out.println("Selection Sort Time = " + elapsed);

				items = tmpItems.clone();
				startTime = System.currentTimeMillis();
				insertionSort(items);
				endTime = System.currentTimeMillis();
				elapsed = endTime - startTime;
				System.out.println("Insertion Sort Time = " + elapsed);
			}
			else {
				int[] tmpItems = new int[n];
				generateItems(tmpItems, n);
				int[] items = new int[n];
				items = tmpItems.clone();

				startTime = System.currentTimeMillis();
				heapSort(items);
				endTime = System.currentTimeMillis();
				elapsed = endTime - startTime;
				System.out.println("Heap Sort Time = " + elapsed);
				//정렬 결과 20개 출력
				int t3  = 0;
				for (int a : items) {
					System.out.print(a + " ");
					t3++;
					if (t3 == 20)
						break;
				}
				System.out.println();
				
				items = tmpItems.clone();
				startTime = System.currentTimeMillis();
				quickSort(items, 0, items.length-1);
				endTime = System.currentTimeMillis();
				elapsed = endTime - startTime;
				System.out.println("QuickSort Time = " + elapsed);
				//정렬 결과 20개 출력
				int t4  = 0;
				for (int a : items) {
					System.out.print(a + " ");
					t4++;
					if (t4 == 20)
						break;
				}
				System.out.println();
				
			}
		}
		in.close();
	}

}
