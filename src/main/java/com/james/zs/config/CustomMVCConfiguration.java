//package com.james.zs.config;
//
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.util.MimeTypeUtils;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 业务异常
// *
// * @author james.gao
// * @version v1.0
// * @description TODO
// * @create date 13:27 2017/11/8
// * @modified by james.gao
// * @modify date 13:27 2017/11/8
// */
//@Configuration
//public class CustomMVCConfiguration extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        List<MediaType> mediaTypeList =  new ArrayList<MediaType>();
//        mediaTypeList.add(MediaType.APPLICATION_JSON);
//        fastConverter.setSupportedMediaTypes(mediaTypeList);
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastConverter);
//    }
//}
