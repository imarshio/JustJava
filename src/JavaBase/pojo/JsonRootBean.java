/**
 * Copyright 2021 bejson.com
 */
package JavaBase.pojo;

import java.util.List;

/**
 * Auto-generated: 2021-12-24 17:17:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private List<Data> data;
    private Header header;

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Header getHeader() {
        return header;
    }

}