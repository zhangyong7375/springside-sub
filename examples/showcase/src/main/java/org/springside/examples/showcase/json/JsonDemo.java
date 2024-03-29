package org.springside.examples.showcase.json;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.type.TypeReference;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springside.modules.utils.encode.JsonBinder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 测试Jackson对Object,Map,List,数组等的持久化.
 * 
 * @author calvin
 */
public class JsonDemo {

	private static JsonBinder binder = JsonBinder.buildNonDefaultBinder();

	public static void main(String[] args) {
		try {
			JsonDemo demo = new JsonDemo();
			demo.toJson();
			demo.fromJson();
			demo.nullAndEmpty();
			demo.threeTypeBinders();
			demo.enumAndDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 序列化对象/集合到Json字符串.
	 */
	@Test
	public void toJson() throws Exception {
		//Bean
		TestBean bean = new TestBean("A");
		String beanString = binder.toJson(bean);
		System.out.println("Bean:" + beanString);
		assertEquals("{\"name\":\"A\"}", beanString);

		//Map
		Map<String, Object> map = Maps.newLinkedHashMap();
		map.put("name", "A");
		map.put("age", 2);
		String mapString = binder.toJson(map);
		System.out.println("Map:" + mapString);
		assertEquals("{\"name\":\"A\",\"age\":2}", mapString);

		//List<String>
		List<String> stringList = Lists.newArrayList("A", "B", "C");
		String listString = binder.toJson(stringList);
		System.out.println("String List:" + listString);
		assertEquals("[\"A\",\"B\",\"C\"]", listString);

		//List<Bean>
		List<TestBean> beanList = Lists.newArrayList(new TestBean("A"), new TestBean("B"));
		String beanListString = binder.toJson(beanList);
		System.out.println("Bean List:" + beanListString);
		assertEquals("[{\"name\":\"A\"},{\"name\":\"B\"}]", beanListString);

		//Bean[]
		TestBean[] beanArray = new TestBean[] { new TestBean("A"), new TestBean("B") };
		String beanArrayString = binder.toJson(beanArray);
		System.out.println("Array List:" + beanArrayString);
		assertEquals("[{\"name\":\"A\"},{\"name\":\"B\"}]", beanArrayString);
	}

	/**
	 * 从Json字符串反序列化对象/集合.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void fromJson() throws Exception {
		//Bean
		String beanString = "{\"name\":\"A\"}";
		TestBean bean = binder.fromJson(beanString, TestBean.class);
		System.out.println("Bean:" + bean);

		//Map
		String mapString = "{\"name\":\"A\",\"age\":2}";
		Map<String, Object> map = binder.fromJson(mapString, HashMap.class);
		System.out.println("Map:");
		for (Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

		//List<String>
		String listString = "[\"A\",\"B\",\"C\"]";
		List<String> stringList = binder.getMapper().readValue(listString, List.class);
		System.out.println("String List:");
		for (String element : stringList) {
			System.out.println(element);
		}

		//List<Bean>
		String beanListString = "[{\"name\":\"A\"},{\"name\":\"B\"}]";
		List<TestBean> beanList = binder.getMapper().readValue(beanListString, new TypeReference<List<TestBean>>() {
		});
		System.out.println("Bean List:");
		for (TestBean element : beanList) {
			System.out.println(element);
		}
	}

	/**
	 * 测试传入空对象,空字符串,Empty的集合,"null"字符串的结果.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void nullAndEmpty() {
		// toJson测试 //

		//Null Bean
		TestBean nullBean = null;
		String nullBeanString = binder.toJson(nullBean);
		assertEquals("null", nullBeanString);

		//Empty List
		List<String> emptyList = Lists.newArrayList();
		String emptyListString = binder.toJson(emptyList);
		assertEquals("[]", emptyListString);

		// fromJson测试 //

		//Null String for Bean
		TestBean nullBeanResult = binder.fromJson(null, TestBean.class);
		assertNull(nullBeanResult);

		nullBeanResult = binder.fromJson("null", TestBean.class);
		assertNull(nullBeanResult);

		//Null/Empty String for List
		List nullListResult = binder.fromJson(null, List.class);
		assertNull(nullListResult);

		nullListResult = binder.fromJson("null", List.class);
		assertNull(nullListResult);

		nullListResult = binder.fromJson("[]", List.class);
		assertEquals(0, nullListResult.size());
	}

	/**
	 * 测试三种不同的Binder.
	 */
	@Test
	public void threeTypeBinders() {
		//打印全部属性
		JsonBinder normalBinder = JsonBinder.buildNormalBinder();
		TestBean bean = new TestBean("A");
		assertEquals("{\"nullValue\":null,\"name\":\"A\",\"defaultValue\":\"hello\"}", normalBinder.toJson(bean));

		//不打印nullValue属性
		JsonBinder nonNullBinder = JsonBinder.buildNonNullBinder();
		assertEquals("{\"name\":\"A\",\"defaultValue\":\"hello\"}", nonNullBinder.toJson(bean));

		//不打印默认值未改变的nullValue与defaultValue属性
		JsonBinder nonDefaultBinder = JsonBinder.buildNonNullBinder();
		assertEquals("{\"name\":\"A\"}", nonDefaultBinder.toJson(bean));
	}

	/**
	 * 测试对枚举与日期的序列化.
	 */
	@Test
	public void enumAndDate() {
		//Enum会以名称(name)序列化.
		assertEquals("\"One\"", binder.toJson(TestEnum.One));

		//Enum可以名称(name)或从0开始的顺序号(ordinal)反序列化.
		assertEquals(TestEnum.One, binder.fromJson("0", TestEnum.class));
		assertEquals(TestEnum.One, binder.fromJson("\"One\"", TestEnum.class));

		DateTime jodaDate = new DateTime();

		//日期默认以Timestamp方式存储
		Date date = new Date(jodaDate.getMillis());
		String tsString = String.valueOf(jodaDate.getMillis());

		assertEquals(tsString, binder.toJson(date));

		assertEquals(date, binder.fromJson(tsString, Date.class));

		//以设定日期格式存储
		String dateString = "\"" + jodaDate.toString("yyyy-MM-dd") + "\"";
		binder.setDateFormat("yyyy-MM-dd");

		assertEquals(dateString, binder.toJson(date));

		Date dateResult = binder.fromJson(dateString, Date.class);
		System.out.println(dateResult);
		assertEquals(new DateTime(dateResult).getDayOfYear(), jodaDate.getDayOfYear());

	}

	//此annoation为了截断对象的循环引用.
	@JsonIgnoreProperties( { "parent" })
	public static class TestBean {

		private String name;
		private String defaultValue = "hello";
		private String nullValue = null;
		private TestBean parent;

		public TestBean() {
		}

		public TestBean(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDefaultValue() {
			return defaultValue;
		}

		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}

		public String getNullValue() {
			return nullValue;
		}

		public void setNullValue(String nullValue) {
			this.nullValue = nullValue;
		}

		public TestBean getParent() {
			return parent;
		}

		public void setParent(TestBean parent) {
			this.parent = parent;
		}

		@Override
		public String toString() {
			return "TestBean [defaultValue=" + defaultValue + ", name=" + name + ", nullValue=" + nullValue + "]";
		}
	}

	public static enum TestEnum {
		One, Two, Three
	}
}
