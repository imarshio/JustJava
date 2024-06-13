package JavaBase;

/**
 * @author masuo
 * @data 20/4/2022 下午2:38
 * @Description 枚举
 */
public class EnumDemo {

    // 只有枚举变量
    public enum SexEnum {
        MAIL, FEMALE;
    }

    public enum ColorEnum {
        RED, YELLOW, GREEN,
        ;
    }

    // 带属性枚举变量
    public enum HttpEnum {
        // 客户端应继续其请求
        CONTINUE(100, "客户端继续请求"),
        // 服务器根据客户端的请求切换协议。只能切换到更高级的协议，例如，切换到 HTTP 的新版本协议
        SWITCHING_PROTOCOLS(101, "切换协议"),

        // 一般用于 GET 与 POST 请求
        SUCCESS(200, "成功"),
        // 成功请求并创建了新的资源
        CREATED(201, "创建成功"),
        // 已经接受请求，但未处理完成
        ACCEPTED(202, "接收成功"),
        // 请求成功。但返回的 meta 信息不在原始的服务器，而是一个副本
        NON_AUTHORITATIVE_INFORMATION(203, "非授权信息"),

        NOT_FOUND(404, "找不到资源"),
        INTERNAL_SERVER_ERROR(500, "系统内部服务错误"),
        BAD_GATEWAY(502, "无效响应"),
        GATEWAY_TIMEOUT(504, "响应超时"),
        ;

        private int code;
        private String msg;

        HttpEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
