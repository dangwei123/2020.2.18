import java.util.Stack;

/**
 * ClassName:Test
 * Package:PACKAGE_NAME
 * Description:
 *
 * @Date:2020/2/18 8:39
 * @Author:DangWei
 */
public class Test {
    public static void quickSort(int[] arr,int left,int right){
        if(left>=right){
            return;
        }
        int val=partion1(arr,left,right);
        quickSort(arr,left,val-1);
        quickSort(arr,val+1,right);
    }

    public static void quickSort(int[] arr){
        Stack<Integer> stack=new Stack<>();
        stack.push(arr.length-1);
        stack.push(0);
        while(!stack.empty()){
            int left=stack.pop();
            int right=stack.pop();
            if(left>=right){
                continue;
            }
            int val=partion3(arr,left,right);
            stack.push(val-1);
            stack.push(left);
            stack.push(right);
            stack.push(val+1);
        }
    }
    private static int partion1(int[] arr,int left,int right){
        int key=arr[left];
        int i=left;
        int j=right;
        while(i<j){
            while(i<j&&arr[j]>=key){
                j--;
            }
            while(i<j&&arr[i]<=key){
                i++;
            }
            if(i<j){
                swap(arr,i,j);
            }
        }
        swap(arr,left,i);
        return i;
    }
    private static int partion2(int[] arr,int left,int right){
        int key=arr[left];
        int i=left;
        int j=right;
        while(i<j){
            while(i<j&&arr[j]>=key){
                j--;
            }
            arr[i]=arr[j];
            while(i<j&&arr[i]<=key){
                i++;
            }
            arr[j]=arr[i];
        }
        arr[i]=key;
        return i;
    }
    private static int partion3(int[] arr,int left,int right){
        int d=left+1;
        int key=arr[left];
        for(int i=left+1;i<=right;i++){
            if(arr[i]<key){
                swap(arr,i,d);
                d++;
            }
        }
        swap(arr,d-1,left);
        return d-1;
    }
    private static void swap(int[] arr,int left,int right){
        int tmp=arr[left];
        arr[left]=arr[right];
        arr[right]=tmp;
    }



    public static void mergeSort(int[] arr,int low,int high){
        if(low>=high-1){
            return;
        }
        int mid=(low+high)/2;
        mergeSort(arr,low,mid);
        mergeSort(arr,mid,high);

        merge(arr,low,mid,high);
    }

    public static void mergeSort(int[] arr){
        for(int i=1;i<arr.length;i*=2){
            for(int j=0;j<arr.length;j+=i*2){
                int left=j;
                int mid=left+i;
                if(mid>=arr.length){
                    continue;
                }
                int right=mid+i;
                if(right>=arr.length){
                    right=arr.length;
                }

                merge(arr,left,mid,right);
            }
        }
    }
    private static void merge(int[] arr,int low,int mid,int high){
        int i=low;
        int j=mid;
        int len=high-low;
        int[] tmp=new int[len];
        int k=0;
        while(i<mid&&j<high){
            if(arr[i]<=arr[j]){
                tmp[k++]=arr[i++];
            }else{
                tmp[k++]=arr[j++];
            }
        }
        while(i<mid){
            tmp[k++]=arr[i++];
        }
        while(j<high){
            tmp[k++]=arr[j++];
        }
        System.arraycopy(tmp,0,arr,low,len);
    }


    public static void main(String[] args) {
        int[] arr={14,4,12,24,13,78,56,1};
        //quickSort(arr,0,arr.length-1);
        //quickSort(arr);
        //mergeSort(arr,0,arr.length);
        mergeSort(arr);
        for(int i:arr){
            System.out.printf("%d ",i);
        }
        System.out.println();
    }
}


class Card implements Comparable<Card>{
    public int rank;
    private String suit;
    public Card(int rank,String suit){
        this.rank=rank;
        this.suit=suit;
    }

     @Override
     public int compareTo(Card o) {
        if(o==null){
            return 1;
        }
         return rank-o.rank;
     }

    /*@Override
    public boolean equals(Object o){
        if(o==null||!(o instanceof Card)){
            return false;
        }
        Card c=(Card)o;
        return c.rank==rank&&c.suit.equals(suit);
    }*/
}

class Cardcomparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        if(o1==null){
            return -1;
        }
        if(o2==null){
            return 1;
        }
        return o1.rank-o2.rank;
    }
}

public static void main(String[] args) {
        Card c1=new Card(1,"♠");
        Card c2=new Card(2,"♠");
        Card c3=new Card(1,"♠");
        System.out.println(c1.compareTo(c2));
        System.out.println(c1.compareTo(c3));

        Cardcomparator a=new Cardcomparator();
        System.out.println(a.compare(c1,c2));
        System.out.println(a.compare(c1,c3));
    }
	
	
	