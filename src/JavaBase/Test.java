package JavaBase;

import JavaBase.pojo.Body;
import JavaBase.pojo.Data;
import JavaBase.pojo.Header;
import JavaBase.pojo.JsonRootBean;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author masuo
 * @data 24/12/2021 下午5:00
 * @Description TODO
 */

public class Test {

    @org.junit.Test
    public void test(){


        Body body = new Body("1.1","802211008001","Si","Si","",
                "","I","","","","","","",
                "","","","","","");

        Header header = new Header();
        List<Body> bodies = new ArrayList<>();
        bodies.add(body);
        Data data = new Data();
        data.setBody(bodies);
        List<Data> datas = new ArrayList<>();
        datas.add(data);
        JsonRootBean jrb = new JsonRootBean();
        jrb.setData(datas);
        //jrb.setHeader();
        //JSONObject PurMeaInfo=(JSONObject) JSONObject.toJSON(object);
        //JSONArray list = PurMeaInfo.getJSONArray("body");
    }
}
