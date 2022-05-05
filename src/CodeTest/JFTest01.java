package CodeTest;

import com.alibaba.fastjson.JSONObject;

/**
 * @author masuo
 * @date: 2022/05/04/ 下午2:20
 * @description 捷丰数据 01
 */
public class JFTest01 {

    public static void main(String[] args) {
        JSONObject jo = new JSONObject();
        jo.put("number",10);

        // 接口接受 json对象 ，返回 json对象
        JSONObject rt = getSum(jo);

        Integer sum = rt.getInteger("result");
    }

    private static JSONObject getSum(JSONObject jo) {
        Integer num = jo.getInteger("number");
        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum += i;
        }
        JSONObject rt = new JSONObject();
        rt.put("result",sum);
        return rt;
    }
}
