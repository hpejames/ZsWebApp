package com.james.zs.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务异常
 *
 * @author james.gao
 * @version v1.0
 * @description TODO
 * @create date 17:56 2017/11/9
 * @modified by james.gao
 * @modify date 17:56 2017/11/9
 */
@Configuration
@ConditionalOnClass({FastJsonHttpMessageConverter.class}) //1 判断是否存在类
@ConditionalOnProperty( //2 使用spring.http.converters.preferred-json-mapper属性来启动功能
        name = {"spring.http.converters.preferred-json-mapper"},
        havingValue = "fastjson",
        matchIfMissing = true
)
public class FastJson2HttpMessageConverterConfiguration {

    protected FastJson2HttpMessageConverterConfiguration() {
    }

    // 当没有注册这个类时，自动注册Bean
    @Bean
    @ConditionalOnMissingBean({FastJsonHttpMessageConverter.class})
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        List<MediaType> mediaTypeList =  new ArrayList<MediaType>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(mediaTypeList);

        // 使用最新的官方推荐配置对象的方式来注入fastjson的序列化特征。
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteClassName,
                SerializerFeature.WriteMapNullValue
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);

        // 添加对json值的过滤，因为像移动APP，服务端在传json值时最好不要传null，而是使用“”，这是一个演示。
        ValueFilter valueFilter = new ValueFilter() {//5
            //o 是class
            //s 是key值
            //o1 是value值
            @Override
            public Object process(Object o, String s, Object o1) {
                if (null == o1){
                    o1 = "";
                }
                return o1;
            }
        };
        fastJsonConfig.setSerializeFilters(valueFilter);
        // 返回fast json转换对象
        return fastConverter;
    }
}
