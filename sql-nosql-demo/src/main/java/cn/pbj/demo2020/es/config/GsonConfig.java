package cn.pbj.demo2020.es.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: GsonConfig
 * @Author: pbj
 * @Date: 2020/6/10 08:30
 * @Description: TODO
 */
@Configuration
public class GsonConfig {

    /**
     * {@link Gson}：一个操作json的对象，有比较好的json操作体验，相对于Alibaba的FastJson来说速度慢一些，但是FastJson在解析
     *              复杂的的json字符串时有可能会出现bug。
     * @return Gson
     */

    @Bean
    public Gson gson() {
        return new Gson();
    }

}
