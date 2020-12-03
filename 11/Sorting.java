package project11_1;

public class Sorting {
	//삽입 정렬
	static void insertionSort1(int[] data) {
		int i,j,tmp = 0;
		int b = 1;
		for(i = 1; i<data.length;i++) {
			tmp = data[i];
			for(j = i; j> 0;j--) {
				if(data[j-1]>tmp)
					data[j] = data[j-1];
				else
					break;
			}
			data[j] = tmp;
			
			System.out.printf("pass%d: ", b);  //패스 출력
			for (int a : data) {
				System.out.print(a + " ");
			}
			System.out.println(" ");
			b++;
		}
		
		
	}
	
	//버블 정렬
	static void bubbleSort1(int[] data) {
		int a = 0;
		int b = 1;
		for(int i = 0 ; i < data.length -1 ; i ++) {
			for(int j = data.length -1 ; j > i ; j --) {
				if(data[j]<data[j-1]) {
					a = data[j];
					data[j] = data[j-1];
					data[j-1] = a;
				}
			}
			System.out.printf("pass%d: ", b);  //패스 출력
			for (int c : data) {
				System.out.print(c + " ");
			}
			System.out.println(" ");
			b++;
		}
	}
	

	//선택 정렬
	static void selectionSort1(int[] data) {
		int size = data.length;
		int b = 1;
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
            
        	System.out.printf("pass%d: ", b);  //패스 출력
			for (int c : data) {
				System.out.print(c + " ");
			}
			System.out.println(" ");
			b++;
		}
       }
	
	
	public static void main(String[] args) {
		int[] data = {24, 15, 29, 11, 47, 12};
		int[] tmp = data.clone();	// 배q열의 데이터를 복사
		System.out.println("Before Sorting");
		for (int x : tmp) 
			System.out.print(x + " ");
		System.out.println("\n");
		System.out.println("After Bubble Sorting");
		bubbleSort1(tmp);	// pass를 삽입한 bubbleSort
		
		tmp = data.clone();
		System.out.println("\nBefore Sorting");
		for (int x : tmp) 
			System.out.print(x + " ");
		System.out.println("\n");
		
		System.out.println("After Selection Sorting");
		selectionSort1(tmp);	// pass를 삽입한 selectionSort

		tmp = data.clone();
		System.out.println("\nBefore Sorting");
		for (int x : tmp) 
			System.out.print(x + " ");
		System.out.println("\n");
		
		System.out.println("After Insertion Sorting");
		insertionSort1(tmp);	// pass를 삽입한 insertionSort

	}

}
