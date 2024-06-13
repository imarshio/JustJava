package creation.ProtoType;

import org.junit.Test;

/**
 * @author masuo
 * @data 2021/9/6 11:54
 * @Description Shallow or Deep clone
 */

public class SOD {

    public static void main(String[] args) {

        //

        SOD sod = new SOD();
        sod.shallow();
        sod.deep();
    }

    @Test
    public void shallow() {
        ShallowClone sc1 = new ShallowClone(0, new Node("shallow node"));
        try {
            ShallowClone sc2 = sc1.clone();

            System.out.println(sc1.toString());
            System.out.println(sc2.toString());
            // ShallowClone{count=0, SOD='S', node=designModel.Creational.ProtoType.Node@506c589e}
            // ShallowClone{count=0, SOD='S', node=designModel.Creational.ProtoType.Node@506c589e}

            // 对其属性进行更改
            sc2.setCount(1);
            sc2.setNode(new Node("0000"));
            System.out.println(sc1.toString());
            System.out.println(sc2.toString());
            // ShallowClone{count=0, SOD='S', node=designModel.Creational.ProtoType.Node@506c589e}
            // ShallowClone{count=1, SOD='S', node=designModel.Creational.ProtoType.Node@69d0a921}


            // 当我们使用另一种方式去调用的时候
            System.out.println("other way");
            Node node1 = new Node("new node");
            ShallowClone sc3 = new ShallowClone(2, node1);
            ShallowClone sc4 = sc3.clone();
            Node node2 = sc4.getNode();
            node2.setMessage("new new node");
            System.out.println(sc3.toString());
            System.out.println(sc4.toString());
            // ShallowClone{count=2, SOD='S', node=designModel.Creational.ProtoType.Node@446cdf90}
            // ShallowClone{count=2, SOD='S', node=designModel.Creational.ProtoType.Node@446cdf90}
            // 可以看到，两个是一样的。从数据看，但是我设置的第二个应该和第一个不一样，
            // 但是在我设置第二个的信息的时候将第一个的信息也重置了，
            // 这就是浅克隆的缺点吧算是。
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void deep() {
        DeepClone dc1 = new DeepClone(0, new Node("deep 1"));

        try {
            DeepClone dc2 = dc1.clone();

            System.out.println(dc1.toString());
            System.out.println(dc2.toString());
            // ShallowClone{count=0, SOD='D', node=Node{message='deep 1'}}
            // ShallowClone{count=0, SOD='D', node=Node{message='deep 1'}}

            // 使用方式2
            Node node1 = new Node("deep 3");
            DeepClone dc3 = new DeepClone(3, node1);
            DeepClone dc4 = dc3.clone();
            Node node2 = dc4.node;
            node2.setMessage("deep 4");

            System.out.println(dc3.toString());
            System.out.println(dc4.toString());
            // ShallowClone{count=3, SOD='D', node=Node{message='deep 3'}}
            // ShallowClone{count=3, SOD='D', node=Node{message='deep 4'}}

            // 实现深克隆的重点在于将非基础对象实现clone，并返回其clone的对象
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


}


// 浅克隆
class ShallowClone implements Cloneable {
    int count;
    static final String SOD = "S";
    Node node;

    @Override
    protected ShallowClone clone() throws CloneNotSupportedException {
        return (ShallowClone) super.clone();
    }

    @Override
    public String toString() {
        return "ShallowClone{" +
                "count=" + count +
                ", SOD='" + SOD + '\'' +
                ", node=" + node.toString() +
                '}';
    }

    public ShallowClone(int count, Node node) {
        this.count = count;
        this.node = node;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSOD() {
        return SOD;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }


}

// 深克隆
class DeepClone implements Cloneable {


    int count;
    static final String SOD = "D";
    Node node;


    @Override
    protected DeepClone clone() throws CloneNotSupportedException {
        // 这一步是实现深克隆的重点：clone
        DeepClone dc = (DeepClone) super.clone();
        dc.setNode(dc.node.clone());
        return dc;
    }

    @Override
    public String toString() {
        return "ShallowClone{" +
                "count=" + count +
                ", SOD='" + SOD + '\'' +
                ", node=" + node.toString() +
                '}';
    }

    public DeepClone(int count, Node node) {
        this.count = count;
        this.node = node;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSOD() {
        return SOD;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }


}


class Node implements Cloneable {
    String message;

    public Node() {
    }

    Node(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Node{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public Node clone() throws CloneNotSupportedException {
        return (Node) super.clone();
    }
}