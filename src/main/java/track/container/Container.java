package track.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.lang.reflect.Type;
import java.lang.reflect.Method;


import track.container.config.Bean;
import track.container.config.ValueType;
import track.container.config.Property;


/**
 * Основной класс контейнера
 * У него определено 2 публичных метода, можете дописывать свои методы и конструкторы
 */
public class Container {

    private List<Bean> beans;
    private Map<String, Object> objByName;
    private Map<String, Object> objByClass;

    // Реализуйте этот конструктор, используется в тестах!
    public Container(List<Bean> beans) {
        this.beans = beans;
        objByName = new HashMap<>();
        objByClass = new HashMap<>();

    }


    Map<String, Object> map = new HashMap<>();

    // Реализуйте этот конструктор, используется в тестах!
    public Container(List<Bean> beans) throws Exception {

    }

    public static void main(String[] args) throws Exception {


    private Object createObject(Bean bean) throws ReflectiveOperationException {
        Class<?> clazz = Class.forName(bean.getClassName());
        Object newObject = clazz.newInstance();
        objByName.put(bean.getId(), newObject);
        objByClass.put(bean.getClassName(), newObject);
        for (Map.Entry<String, Property> entry : bean.getProperties().entrySet()) {
            String value = entry.getValue().getValue();
            ValueType type = entry.getValue().getType();
            if (type.equals(ValueType.REF)) {
                Object refObject = getById(value);
                Class<?> refObjectClass = refObject.getClass();
                Method method = clazz.getMethod("set" + entry.getKey().substring(0,1).toUpperCase() +
                        entry.getKey().substring(1), refObjectClass);
                method.invoke(newObject, refObject);
            } else {

                Class<?> objectType = clazz.getDeclaredField(value).getType();
                Object objectValue = toType(value, objectType);
                Method method = clazz.getMethod("set" + entry.getKey().substring(0,1).toUpperCase() +
                        entry.getKey().substring(1), objectType);
                method.invoke(newObject, objectValue);
            }
        }

        return newObject;
    }

    private Object toType(String value, Type valueType) {
        if (valueType == Integer.TYPE) {
            return Integer.parseInt(value);
        }

        if (valueType == Double.TYPE) {
            return Double.parseDouble(value);
        }

        if (valueType == Boolean.TYPE) {
            return Boolean.parseBoolean(value);
        }

        if (valueType == Long.TYPE) {
            return Long.parseLong(value);
        }

        if (valueType == Short.TYPE) {
            return Short.parseShort(value);
        }

        if (valueType == Byte.TYPE) {
            return Byte.parseByte(value);
        }

        return value;
    }

    /**
     *  Вернуть объект по имени бина из конфига
     *  Например, Car car = (Car) container.getById("carBean")
     */
    public Object getById(String id) throws ReflectiveOperationException {
        Object object = objByName.get(id);
        if (object == null) {
            for (Bean bean : beans) {
                if (bean.getId().equals(id)) {
                    object = createObject(bean);
                }
            }
        }
        return object;
    }

    /**
     * Вернуть объект по имени класса
     * Например, Car car = (Car) container.getByClass("track.container.beans.Car")
     */
    public Object getByClass(String className) throws ReflectiveOperationException {
        Object object = objByClass.get(className);
        if (object == null) {
            for (Bean bean : beans) {
                if (bean.getClassName().equals(className)) {
                    object = createObject(bean);

                }
            }
        }
        return object;
    }
}
