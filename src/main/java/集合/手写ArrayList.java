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

    public 手写ArrayList() {
        this.element = EMPTY_ELEMENT_ARRAY;
    }

    public 手写ArrayList(int capacity) {
        if (capacity > 0) {
            this.element = new Object[capacity];
        } else if (capacity == 0) {
            this.element = new Object[DEFAULT_SIZE];
        } else {
            throw new IllegalArgumentException("数组长度异常！");
        }
    }

    public boolean add(Object e) {
        //判断是否需要扩容
        ensureCapacityGrow(size + 1);
        element[size++] = e;
        return true;
    }

    public Object get(int index) {
        rangeCheck(index);
        return element[index];
    }

    public Object set(int index, Object e) {
        rangeCheck(index);
        Object oldValue = element[index];
        element[index] = e;
        return oldValue;
    }

    public Object remove(int index){
        rangeCheck(index);
        Object oldValue = element[index];
        int removeNum = size - index - 1;
        if(removeNum>0){
            System.arraycopy(element,index+1,element,index,removeNum);
        }

        element[--size]=null;

        return oldValue;
    }

    public int size(){
        return this.size;
    }

    /**
     * 检查下标是否越界
     *
     * @param index
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("数组越界");
        }
    }

    /**
     * 判断数组是否需要扩容
     *
     * @param size
     */
    private void ensureCapacityGrow(int size) {

        if (this.element == EMPTY_ELEMENT_ARRAY) {
            size = Math.max(DEFAULT_SIZE, size);
        }

        if (size - element.length > 0) {
            int oldCapacity = element.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);

            if (newCapacity - size < 0) {
                newCapacity = size;
            }

            Object[] newElements = new Object[newCapacity];
            System.arraycopy(element, 0, newElements, 0, oldCapacity);

            this.element = newElements;
        }
    }
}
