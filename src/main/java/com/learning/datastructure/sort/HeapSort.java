package com.learning.datastructure.sort;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class HeapSort {

    //构建大根堆：将array看成完全二叉树的顺序存储结构
    private int[] buildMaxHeap(int[] array){
        //从最后一个节点array.length-1的父节点（array.length-1-1）/2开始，直到根节点0，反复调整堆
        for(int i=(array.length-2)/2;i>=0;i--){
            adjustDownToUp(array, i,array.length);
        }
        return array;
    }

    //将元素array[k]自下往上逐步调整树形结构
    private void adjustDownToUp(int[] array,int k,int length){
        int temp = array[k];
        for(int i=2*k+1; i<length-1; i=2*i+1){    //i为初始化为节点k的左孩子，沿节点较大的子节点向下调整
            if(i<length && array[i]<array[i+1]){  //取节点较大的子节点的下标
                i++;   //如果节点的右孩子>左孩子，则取右孩子节点的下标
            }
            if(temp>=array[i]){  //根节点 >=左右子女中关键字较大者，调整结束
                break;
            }else{   //根节点 <左右子女中关键字较大者
                array[k] = array[i];  //将左右子结点中较大值array[i]调整到双亲节点上
                k = i; //【关键】修改k值，以便继续向下调整
            }
        }
        array[k] = temp;  //被调整的结点的值放人最终位置
    }

    //堆排序
    public int[] heapSort(int[] array){
        array = buildMaxHeap(array); //初始建堆，array[0]为第一趟值最大的元素
        for(int i=array.length-1;i>1;i--){
            int temp = array[0];  //将堆顶元素和堆低元素交换，即得到当前最大元素正确的排序位置
            array[0] = array[i];
            array[i] = temp;
            adjustDownToUp(array, 0,i);  //整理，将剩余的元素整理成堆
        }
        return array;
    }

    //删除堆顶元素操作
    public int[] deleteMax(int[] array){
        //将堆的最后一个元素与堆顶元素交换，堆底元素值设为-99999
        array[0] = array[array.length-1];
        array[array.length-1] = -99999;
        //对此时的根节点进行向下调整
        adjustDownToUp(array, 0, array.length);
        return array;
    }

    //插入操作:向大根堆array中插入数据data
    public int[] insertData(int[] array, int data){
        array[array.length-1] = data; //将新节点放在堆的末端
        int k = array.length-1;  //需要调整的节点
        int parent = (k-1)/2;    //双亲节点
        while(parent >=0 && data>array[parent]){
            array[k] = array[parent];  //双亲节点下调
            k = parent;
            if(parent != 0){
                parent = (parent-1)/2;  //继续向上比较
            }else{  //根节点已调整完毕，跳出循环
                break;
            }
        }
        array[k] = data;  //将插入的结点放到正确的位置
        return array;
    }

    @Test
    public void test() {
        int[] array = {2,3,9,10,20000,399,400,800,84,34,32};
        print(heapSort(array));
    }

    public void print(Object obj) {
        System.out.println(JSON.toJSONString(obj));
    }
}
