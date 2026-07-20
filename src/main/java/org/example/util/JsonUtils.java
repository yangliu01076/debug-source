package org.example.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author duoyian
 * @date 2026/7/1
 */
public class JsonUtils {

    // 1. 创建全局唯一的 ObjectMapper，提升性能
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 2. 日期时间格式化常量
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    static {
        // ---------- 核心配置初始化 ----------

        // 序列化配置：忽略 null 值属性（不输出值为 null 的字段）
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 序列化配置：禁用把日期序列化为时间戳 (默认是 true)
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 反序列化配置：允许 JSON 中有 Java 对象没有的属性，忽略它而不是报错 (非常关键的容错机制)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // ---------- 处理 Java 8 时间 API (JSR310) ----------
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // LocalDateTime 序列化与反序列化
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        // LocalDate 序列化与反序列化
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));

        // 注册时间模块
        objectMapper.registerModule(javaTimeModule);

        // 传统 Date 类型的格式化 (如果你还在用 java.util.Date)
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_TIME_PATTERN));
    }

    private JsonUtils() {
        // 私有构造方法，防止被实例化
    }

    /**
     * 将 Java 对象序列化为 JSON 字符串
     *
     * @param obj 要序列化的对象
     * @return JSON 字符串
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 序列化失败", e);
        }
    }

    /**
     * 将 JSON 字符串反序列化为指定类型的 Java 对象
     *
     * @param json  JSON 字符串
     * @param clazz 目标类型的 Class 对象
     * @param <T>   泛型
     * @return 反序列化后的 Java 对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 反序列化失败", e);
        }
    }

    /**
     * 将 JSON 字符串反序列化为复杂的泛型集合对象 (如 List<User>, Map<String, User>)
     *
     * @param json       JSON 字符串
     * @param typeReference TypeReference 对象，用于保留泛型信息
     * @param <T>        泛型
     * @return 反序列化后的 Java 对象
     */
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 泛型反序列化失败", e);
        }
    }
}
