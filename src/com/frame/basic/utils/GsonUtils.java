package com.frame.basic.utils;

import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 居于Gson的JSON工具类
 * @author lizhixian
 * @version 1.0
 * @create_date 2015-7-30 下午3:15:12
 */
public class GsonUtils {

	/** 空的 JSON 数据 - "{}" */
	public static final String EMPTY_JSON = "{}";
	/** 空的 JSON 数组(集合)数据 - "[]" */
	public static final String EMPTY_JSON_ARRAY = "[]";
	/** 默认的 JSON 日期/时间字段的格式化模式 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";
	
	private GsonUtils(){
		super();
	}
	
	/**
	 * 将给定的目标对象根据{@code GsonBuilder} 所指定的条件参数转换成 {@code JSON} 格式的字符串。
	 * <p />
	 * <ul>
	 * <li>该方法转换发生错误时，不会抛出任何异常。若发生错误时，{@code JavaBean} 对象返回 <code>"{}"</code>；</li>
	 * <li>集合或数组对象返回 <code>"[]"</code>。 其本基本类型，返回相应的基本值。</li>
	 * </ul>
	 * @param target 目标对象。
	 * @param targetType 目标对象的类型。
	 * @param builder 可定制的{@code Gson} 构建器。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.1
	 */
	public static String toJson(Object target, Type targetType,
			GsonBuilder builder) {
		if (target == null)
			return EMPTY_JSON;
		Gson gson = null;
		if (builder == null) {
			gson = new Gson();
		} else {
			gson = builder.create();
		}
		String result = EMPTY_JSON;
		try {
			if (targetType == null) {
				result = gson.toJson(target);
			} else {
				result = gson.toJson(target, targetType);
			}
		} catch (Exception ex) {
			throw new RuntimeException("目标对象 " + target.getClass().getName()
					+ " 转换 JSON 字符串时，发生异常！", ex);
		}
		return result;
	}
	/**
	 * 将给定的目标对象根据指定的条件参数转换成 {@code JSON} 格式的字符串。
	 * <p />
	 * <strong>该方法转换发生错误时，不会抛出任何异常。若发生错误时，曾通对象返回 <code>"{}"</code>； 集合或数组对象返回
	 * <code>"[]"</code> </strong>
	 * 
	 * @param target 目标对象。
	 * @param targetType 目标对象的类型。
	 * @param isSerializeNulls 是否序列化 {@code null} 值字段。
	 * @param version 字段的版本号注解。
	 * @param datePattern 日期字段的格式化模式。
	 * @param excludesFieldsWithoutExpose 是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target, Type targetType,
			boolean isSerializeNulls, Double version, String datePattern,
			boolean excludesFieldsWithoutExpose){
		if (target == null)
			return EMPTY_JSON;
		GsonBuilder builder = new GsonBuilder();
		//支持Map的key为复杂对象的形式  
		builder.enableComplexMapKeySerialization();
		
		if (isSerializeNulls)
			builder.serializeNulls();
		
		if (version != null)
			builder.setVersion(version.doubleValue());
		
		//时间转化为特定格式 
		if (StringUtils.isBlank(datePattern))
			datePattern = DEFAULT_DATE_PATTERN;
		builder.setDateFormat(datePattern);
		
		//不导出实体中没有用@Expose注解的属性
		if (excludesFieldsWithoutExpose)
			builder.excludeFieldsWithoutExposeAnnotation();
		
		return toJson(target, targetType, builder);
	}
	
	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。
	 * <strong>此方法只用来转换普通的 {@code JavaBean} 对象。</strong>
	 * <ul>
	 * <li>该方法转换所有的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target) {
		return toJson(target,null,false,null,null,false);
	}
	
	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。
	 * <strong>此方法只用来转换普通的 {@code JavaBean}对象。</strong>
	 * <ul>
	 * <li>该方法根据参数 判断 是否转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}；</li>
	 * </ul>
	 * 
	 * @param target 要转换成 {@code JSON} 的目标对象。
	 * @param excludesFieldsWithoutExpose 是否只转换标有 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target,boolean excludesFieldsWithoutExpose) {
		return toJson(target,null,false,null,null,excludesFieldsWithoutExpose);
	}
	
	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。
	 * <strong>此方法只用来转换普通的 {@code JavaBean}对象。</strong>
	 * <ul>
	 * <li>该方法转换所有字段</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * </ul>
	 * 
	 * @param target 要转换成 {@code JSON} 的目标对象。
	 * @param datePattern 日期字段的格式化模式。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target,String datePattern) {
		return toJson(target, null, false, null, datePattern,false);
	}
	
	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。
	 * <strong>此方法只用来转换普通的 {@code JavaBean}对象。</strong>
	 * <ul>
	 * <li>该方法根据参数 判断 是否转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * </ul>
	 * 
	 * @param target 要转换成 {@code JSON} 的目标对象。
	 * @param datePattern 日期字段的格式化模式。
	 * @param excludesFieldsWithoutExpose 是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target,String datePattern,boolean excludesFieldsWithoutExpose) {
		return toJson(target, null, false, null, datePattern,excludesFieldsWithoutExpose);
	}
	
	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。
	 * <strong>此方法只用来转换普通的 {@code JavaBean}对象。</strong>
	 * <ul>
	 * <li>该方法转换所有字段</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}；</li>
	 * </ul>
	 * 
	 * @param target 要转换成 {@code JSON} 的目标对象。
	 * @param version 字段的版本号注解({@literal @Since})。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target, Double version) {
		return toJson(target, null, false, version, null, true);
	}
	
	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。
	 * <strong>此方法只用来转换普通的 {@code JavaBean} 对象。</strong>
	 * <ul>
	 * <li>该方法根据参数 判断 是否转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSS}；</li>
	 * </ul>
	 * 
	 * @param target 要转换成 {@code JSON} 的目标对象。
	 * @param version 字段的版本号注解({@literal @Since})。
	 * @param excludesFieldsWithoutExpose 是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target, Double version,
			boolean excludesFieldsWithoutExpose) {
		return toJson(target, null, false, version, null,
				excludesFieldsWithoutExpose);
	}
	
	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。
	 * <strong>此方法通常用来转换使用泛型的对象。</strong>
	 * <ul>
	 * <li>该方法转换所有的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSSS}；</li>
	 * </ul>
	 * 
	 * @param target 要转换成 {@code JSON} 的目标对象。
	 * @param targetType 目标对象的类型。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target, Type targetType) {
		return toJson(target, targetType, false, null, null, false);
	}
	
	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。
	 * 
	 * @param <T> 要转换的目标类型。
	 * @param json 给定的 {@code JSON} 字符串。
	 * @param token {@code com.google.gson.reflect.TypeToken} 的类型指示类对象，
	 * 			如：new TypeToken<List<User>>(){}。
	 * @param datePattern 日期格式模式，默认的 日期 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSSS}。
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 * @since 1.0
	 */
	public static <T> T fromJson(String json, TypeToken<T> token,String datePattern) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		GsonBuilder builder = new GsonBuilder();
		if (StringUtils.isBlank(datePattern)) {
			datePattern = DEFAULT_DATE_PATTERN;
		}
		Gson gson = builder.create();
		try {
			return gson.fromJson(json,token.getType());
		} catch (Exception ex) {
			throw new RuntimeException(json + " 无法转换为 " + token.getRawType().getName()
					+ " 对象!", ex);
		}
	}
	
	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。
	 * 
	 * @param <T> 要转换的目标类型。
	 * @param json 给定的 {@code JSON} 字符串。
	 * @param token {@code com.google.gson.reflect.TypeToken} 的类型指示类对象。
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 * @since 1.0
	 */
	public static <T> T fromJson(String json, TypeToken<T> token) {
		return fromJson(json, token, null);
	}
	
	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。
	 * <strong>此方法通常用来转换普通的 {@code JavaBean}对象。</strong>
	 * 
	 * @param <T> 要转换的目标类型。
	 * @param json 给定的 {@code JSON} 字符串。
	 * @param clazz 要转换的目标类。
	 * @param datePattern 日期格式模式，默认的 日期 格式化模式 - {@code yyyy-MM-dd HH:mm:ss SSSS}。。
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 * @since 1.0
	 */
	public static <T> T fromJson(String json, Class<T> clazz, String datePattern) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		GsonBuilder builder = new GsonBuilder();
		if (StringUtils.isBlank(datePattern)) {
			datePattern = DEFAULT_DATE_PATTERN;
		}
		Gson gson = builder.create();
		try {
			return gson.fromJson(json, clazz);
		} catch (Exception ex) {
			throw new RuntimeException(json + " 无法转换为 " + clazz.getName() + " 对象!", ex);
		}
	}

	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。
	 * <strong>此方法通常用来转换普通的 {@code JavaBean} 对象。</strong>
	 * 
	 * @param <T> 要转换的目标类型。
	 * @param json 给定的 {@code JSON} 字符串。
	 * @param clazz 要转换的目标类。
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 * @since 1.0
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		return fromJson(json, clazz, null);
	}
}
