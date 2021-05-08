package 集合;

import java.io.Serializable;

public class 手写ArrayList implements Serializable {

    //初始化空的元素数组
    private static final Object[] EMPTY_ELEMENT_ARRAY = {};

    //数组当前长度
    private int size;

    //数组默认初始化长度
    private static final int DEFAULT_SIZE = 10;

    //数组数据元素
    private transient Object[] element;

    public 手写ArrayList(){
        this.element = EMPTY_ELEMENT_ARRAY;
    }

    public 手写ArrayList(int capacity){
        if(capacity>0){
            this.element = new Object[capacity];
        }else if(capacity==0){
            this.element = new Object[DEFAULT_SIZE];
        }else{
            throw new IllegalArgumentException("数组长度异常！");
        }
    }

    public boolean add(Object e){
        //判断是否需要扩容
        ensureCapacityGrow(size+1);
        element[size++]=e;
        return true;
    }

    private void ensureCapacityGrow(int size) {

        if(this.element == EMPTY_ELEMENT_ARRAY){
            size = Math.max(DEFAULT_SIZE,size);
        }

        if(size - element.length > 0){
            int oldCapacity = element.length;
            int newCapacity = oldCapacity + (oldCapacity>>1);

            if(newCapacity - size < 0){
                newCapacity = size;
            }

            Object[] newElements = new Object[newCapacity];
            System.arraycopy(element,0,newElements,0,oldCapacity);

            this.element = newElements;
        }



    }
}
