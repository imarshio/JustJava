package iterable.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Date;

/**
 * @author masuo
 * @data 26/4/2022 下午3:38
 * @Description 实现Json对象的深拷贝
 */

public class JsonDeepClone {

    @Test
    public void test() {
        JSONObject jo = new JSONObject();
        jo.put("head", "This is ms");
        jo.put("time", new Date());
        jo.put("null值", null);
        JSONArray ja = new JSONArray();
        Student student = new Student(1, "ms", null, new Date(), null);
        ja.add(student);
        jo.put("body", ja);

        JSONObject lm = new JSONObject();
        lm.putAll(jo);
        lm.put("head", "this is lm");
        JSONArray lmbody = lm.getJSONArray("body");
        lmbody.forEach((stu) -> {
            Student stu1 = (Student) stu;
            stu1.setName("lm");
        });
        System.out.println(lm.toString());
        System.out.println(jo.toString());
        // DeepClone

        // 先将JSONObject转换成String
        String s = jo.toString();

        JSONObject newjo = JSONObject.parseObject(s);
        newjo.put("head", "this is lx");
        JSONArray body = newjo.getJSONArray("body");
        body.forEach((stu) -> {
            JSONObject object = ((JSONObject) stu);
            object.put("name", "lx");
        });
        System.out.println(newjo.toString());
        System.out.println(jo.toString());
    }

    static final class Student {
        private Integer id;
        private String name;
        private String sex;
        private Date birth;
        private Date workStart;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Date getBirth() {
            return birth;
        }

        public void setBirth(Date birth) {
            this.birth = birth;
        }

        public Date getWorkStart() {
            return workStart;
        }

        public void setWorkStart(Date workStart) {
            this.workStart = workStart;
        }

        public Student(Integer id, String name, String sex, Date birth, Date workStart) {
            this.id = id;
            this.name = name;
            this.sex = sex;
            this.birth = birth;
            this.workStart = workStart;
        }
    }
}
