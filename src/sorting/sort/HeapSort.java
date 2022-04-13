package sorting.sort;

import org.junit.Test;


/**
 * @author masuo
 * @data 2021/9/14 11:14 - 9/15 13.36
 * 实际用时达到5小时左右，主要是了解堆得建立以及上调下调，占据了大部分时间，排序只用了5分钟，因为堆本来就是一个很好的排序工具
 * @Description 堆排序
 * 首先，堆是一种数据结构，是一种非线性结构，类似于二叉树，本质是用数组实现的二叉树。
 * 堆在纸面上的表现形式有多种，一种是画一个二叉树来表示堆，
 * 也可以写一个数组代表堆，数组有前序，中序，后序三种写法，这里的前中后指的是堆顶在数组中的位置，左右是不会改变的。
 * 其中的节点有一个父类节点，有两个子类节点，堆顶父节点为null，
 * 叶子节点没有子节点，但是有父节点。
 * 由于他的结构特点，他比较适合用于寻找 top(n)/热点 之类的(堆顶及其儿子节点就是啦)
 * <p>
 * 堆和数的区别：https://www.jianshu.com/p/6b526aa481b1
 * <p>
 * 大顶堆：子节点的值总是满足不大于父节点得值
 * 小顶堆：子节点的值总是满足不小于父结点的值
 * 寻找非叶子节点：【0，数组长度/2-1】，例数组长度为10，则叶子节点区间为【0，4】
 * 寻找最后一个非叶子节点：最后一个叶子节点 =（数组长度/2）-1
 * 叶子节点与其子节点之间的关系：左节点下标 = 叶子节点下标*2 + 1，右节点下标 = 叶子节点下标*2 + 2
 */


public class HeapSort {

    //构建堆的过程

    //1.先按照无序数组搭建一个无序堆，
    //2.从最后一个非叶子节点开始，从下往上调整，
    // 找非叶子节点及其叶子中最大的作为非叶子节点，就是三个点选最大的作为父节点
    //3.此时构建的堆就是一个大顶堆，将根节点与最后一个节点交换，
    // 此时，数组尾部是最大元素，剩下的节点因为交换可能不再满足大顶堆的条件，所以需要重构大顶堆
    //4.重复 2，3 两步
    // https://www.cnblogs.com/sunshineliulu/p/12995910.html。

    @Test
    public void sort() {

        int[] unsort = {1, 2, 4, 5, 8, 9, 7, 4, 85, 0};
        //此时是一个无序堆，先构建大顶堆
        int length = unsort.length;
        //首先找到最后一个非叶子节点，然后从下往上推
        // 寻找最后一个非叶子节点的公式为：（length/2-1）
        int lastNoLeaf = length / 2 - 1;
        //大顶堆
        // buildBigPileTop(unsort, lastNoLeaf, length);
        //小顶堆
        buildSmallCapPile(unsort, lastNoLeaf, length);

        for (int i : unsort) {
            System.out.println(i);
        }


        //建堆完成之后对其进行排序，排序根据情况选择是使用大根堆还是小根堆

        //我们先使用大顶堆排序
        //首先，最大点是根，所以我们需要输出第一个值，之后“删除根”，对剩余的节点重新调整
        // 这里的删除并不是真正意义上的删除，只是在调整的时候不在调整这个节点，
        // 将根节点放在尾部，之后不再调整

        for (int i = unsort.length - 1; i >= 0; i--) {
            swap(unsort, 0, i);
            //重新调整
            //“删除后”每次调整都是从0开始
            // shiftDown(unsort,0,i);
            shiftDownSmall(unsort, 0, i);
        }
        for (int i : unsort) {
            System.out.print(i + "  ");
        }
    }

    /**
     * 构建小顶堆
     *
     * @param unsort     未排序数组
     * @param lastNoLeaf 最后一个非叶子节点
     * @param length     数组长度
     */
    private void buildSmallCapPile(int[] unsort, int lastNoLeaf, int length) {
        while (lastNoLeaf >= 0) {
            shiftDownSmall(unsort, lastNoLeaf--, length);
        }
    }

    /**
     * 小根堆下调
     */
    private void shiftDownSmall(int[] unsort, int nonLeaf, int length) {
        int left = nonLeaf * 2 + 1;
        if (left >= length) return;
        //左叶子节点
        if (unsort[left] < unsort[nonLeaf]) {
            swap(unsort, left, nonLeaf);
            shiftDownSmall(unsort, left, length);
        }

        int right = nonLeaf * 2 + 2;
        if (right >= length) return;
        //右叶子节点
        if (unsort[right] < unsort[nonLeaf]) {
            swap(unsort, right, nonLeaf);
            shiftDownSmall(unsort, right, length);
        }
    }

    /**
     * 构建大顶堆
     *
     * @param unsort     堆数组
     * @param lastNoLeaf 非叶子节点下标,从某一个非叶子节点开始上调，初始是从最后一个非叶子节点
     * @param length     数组长度
     */
    private void buildBigPileTop(int[] unsort, int lastNoLeaf, int length) {

        while (lastNoLeaf >= 0) {
            //上调
            shiftUp(unsort, lastNoLeaf, length);
            --lastNoLeaf;
        }
    }

    private void shiftUp(int[] unsort, int noLeaf, int length) {

        //先与左子节点相比，
        // 在堆中，一个结点的左子节点下标为：（index*2+1），右子节点的下标为：（index*2+2）
        int left = noLeaf * 2 + 1;
        if (unsort[left] > unsort[noLeaf]) {
            //左子节点大于父节点，交换
            swap(unsort, left, noLeaf);
            //int temp = unsort[left];
            //unsort[left] = unsort[noLeaf];
            //unsort[noLeaf] = temp;


            //交换完之后需要进行下调，此时上调已经不合适，因为对上面无影响，所以我们需要下调
            //下调
            shiftDown(unsort, left, length);
        }
        //在与右子节点对比
        int right = noLeaf * 2 + 2;
        //最后一个非叶子节点不一定有右子节点，需要判断
        if (right < length) {
            if (unsort[right] > unsort[noLeaf]) {
                //左子节点大于父节点，交换
                swap(unsort, right, noLeaf);
                //int temp = unsort[right];
                //unsort[right] = unsort[noLeaf];
                //unsort[noLeaf] = temp;
                shiftDown(unsort, right, length);
            }
        }

    }

    //下调
    private void shiftDown(int[] unsort, int noLeaf, int length) {

        int left = noLeaf * 2 + 1;
        if (left >= length) return;
        if (unsort[left] > unsort[noLeaf]) {
            swap(unsort, left, noLeaf);
            shiftDown(unsort, left, length);
        }
        int right = noLeaf * 2 + 2;
        if (right >= length) return;
        if (unsort[right] > unsort[noLeaf]) {
            swap(unsort, right, noLeaf);
            shiftDown(unsort, right, length);
        }

    }

    private void swap(int[] unsort, int index, int noLeaf) {
        //最简单的交换,利用中间变量
        int temp = unsort[index];
        unsort[index] = unsort[noLeaf];
        unsort[noLeaf] = temp;

        //其次还可以通过加减法来实现
        // unsort[index] = unsort[index] + unsort[noLeaf];
        // unsort[noLeaf] = unsort[index] - unsort[noLeaf];//=index
        // unsort[index] = unsort[index] - unsort[noLeaf];//=noLeaf

        //使用异或，利用的是二进制的特点，加和使用的是十进制的特点
        // unsort[index] = unsort[index] ^ unsort[noLeaf];
        // unsort[noLeaf] = unsort[index] ^ unsort[noLeaf];
        // unsort[index] = unsort[index] ^ unsort[noLeaf];

    }


}
