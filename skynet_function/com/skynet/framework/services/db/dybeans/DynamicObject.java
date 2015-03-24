package com.skynet.framework.services.db.dybeans;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.skynet.framework.services.function.StringToolKit;

public class DynamicObject extends HashMap {
	public DynamicObject() {
		super();
	}

	// private HashMap attributes = new HashMap();

	public DynamicObject(Map map) {
		Iterator names = map.keySet().iterator();
		while (names.hasNext()) {
			String name = ((String) names.next()).toLowerCase();
			if (map.get(name) == null) {
				setAttr(name, new String());
			} else {
				setAttr(name, String.valueOf(map.get(name)));
			}

		}
	}

	public DynamicObject(String attrname, String attrvalue) {
		super();
		setAttr(attrname, attrvalue);
	}

	public DynamicObject(String[] attrnames, String[] attrvalues) {
		super();
		if (attrnames != null && attrvalues != null) {
			for (int i = 0; i < attrnames.length; i++) {
				setAttr(attrnames[i], attrvalues[i]);
			}
		}
	}

	public List getAttrNames() {
		List attrnames = new ArrayList();
		Iterator names = this.keySet().iterator();
		while (names.hasNext()) {
			attrnames.add((String) names.next());
		}
		return attrnames;
	}

	public List getAttrValues() {
		List attrvalues = new ArrayList();
		Iterator names = this.keySet().iterator();
		while (names.hasNext()) {
			attrvalues.add(this.get((String) names.next()));
		}
		return attrvalues;
	}

	public String[] getFormatAttrArray(String[] names) {
		int len = names.length;
		String[] values = new String[len];
		for (int i = 0; i < len; i++) {
			values[i] = getFormatAttr(names[i]);
		}
		return values;
	}

	public void setAttr(String attrName, String attrValue) {
		if (attrValue == null) {
			attrValue = new String();
		} else {
			attrValue = attrValue.trim();
		}

		this.put(attrName, attrValue);
	}

	public void setAttrs(String attrNames, String attrValues) {
		if (attrNames != null && attrValues != null) {
			String[] names = StringToolKit.split(attrNames, ",");
			String[] values = StringToolKit.split(attrValues, ",");
			for (int i = 0; i < names.length; i++) {
				this.put(names[i], values[i]);
			}
		}
	}

	public void setAttr(String attrName, int attrValue) {
		this.put(attrName, String.valueOf(attrValue));
	}

	public void setAttr(String attrName, float attrValue) {
		this.put(attrName, String.valueOf(attrValue));
	}

	public void setAttrs(String[] attrNames, String[] attrValues) {
		int len = attrNames.length;
		for (int i = 0; i < len; i++) {
			String attrValue = attrValues[i];
			String attrName = attrNames[i];

			if (attrValue != null) {
				attrValue = attrValue.trim();
			}

			this.put(attrName, attrValue);
		}
	}

	public String getAttr(String attrName) {
		String attrValue = (String) this.get(attrName);
		if (attrValue != null) {
			attrValue = attrValue.trim();
		}
		return attrValue;
	}

	public String renAttr(String attrName, String attrName_n) {
		String attrValue = (String) this.get(attrName);
		this.put(attrName_n, attrValue);
		this.remove(attrName);
		return attrValue;
	}

	public String getFormatAttr(String attrName) {
		String attrValue = (String) this.get(attrName);
		if (attrValue != null) {
			attrValue = attrValue.trim();
		} else {
			attrValue = "";
		}
		return attrValue;
	}

	public void removeAttrs() {
		this.clear();
	}

	public void copyAttr(Map map, String attrName) {
		this.setAttr(attrName, (String) map.get(attrName));
	}

	public void copyAttr(Map map, String attrName, String attrTagName) {
		this.setAttr(attrTagName, (String) map.get(attrName));
	}

	public void copyObj(Map map, String attrName, String attrTagName) {
		this.setObj(attrTagName, map.get(attrName));
	}

	public void setObj(String attrName, Object attrValue) {
		this.put(attrName, attrValue);
	}

	public Object getObj(String attrName) {
		Object attrValue = (Object) this.get(attrName);
		return attrValue;
	}

	public DynamicObject getDyObj(String attrName) {
		DynamicObject attrValue = (DynamicObject) this.get(attrName);
		if (attrValue == null) {
			attrValue = new DynamicObject();
		}
		return attrValue;
	}

	public static DynamicObject transBean(Object obj) {
		DynamicObject map = new DynamicObject();

		if (obj == null) {
			return map;
		}

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if(value==null)
					{
						map.put(key, "");
					}
					else
					{
						map.put(key, value);
					}
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;
	}
}
