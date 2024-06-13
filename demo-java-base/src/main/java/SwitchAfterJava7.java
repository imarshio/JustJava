package JavaBase;

/**
 * @author masuo
 * @create 2021/7/8 23:50
 * @Description 在Java7以后，switch支持使用String类型
 */
public class SwitchAfterJava7 {
    public static void main(String[] args) {
        switchAfterJava7();
    }

    private static void switchAfterJava7() {
        String s1 = "1";
        switch (s1) {
            case "1":
                System.out.println(s1);
            case "2":
                System.out.println(s1);
        }

    }
}
