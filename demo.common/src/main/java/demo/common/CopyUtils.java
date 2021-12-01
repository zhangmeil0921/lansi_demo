package demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 实现两个类的复制
 * @author Zml
 * @createDate 2021-07-29
 */
public class CopyUtils {

    private final Logger logger  = LoggerFactory.getLogger(this.getClass());

    // 该方法实现对Customer对象的拷贝操作
    public Object copy(Object object) throws Exception
    {
        Class<?> classType = object.getClass();

        /* 生成新的对象的讨论
        // 获得Constructor对象,此处获取第一个无参数的构造方法的
        Constructor cons = classType.getConstructor(new Class[] {});//不带参数，所以传入一个为空的数组
        // 通过构造方法来生成一个对象
        Object obj = cons.newInstance(new Object[] {});

        // 以上两行代码等价于：
        Object obj11 = classType.newInstance();  // 这行代码无法处理构造函数有参数的情况

        //用第二个带参数的构造方法生成对象
        Constructor cons2 = classType.getConstructor(new Class[] {String.class, int.class});
        Object obj2 = cons2.newInstance(new Object[] {"ZhangSan",20});

        */

        Object objectCopy = classType.getConstructor(new Class[]{}).newInstance(new Object[]{});

        //获得对象的所有成员变量
        Field[] fields = classType.getDeclaredFields();
        for(Field field : fields)
        {
            //获取成员变量的名字
            String name = field.getName();    //获取成员变量的名字，此处为id，name,age
            //System.out.println(name);

            //获取get和set方法的名字
            String firstLetter = name.substring(0,1).toUpperCase();    //将属性的首字母转换为大写
            String getMethodName = "get" + firstLetter + name.substring(1);
            String setMethodName = "set" + firstLetter + name.substring(1);
            //System.out.println(getMethodName + "," + setMethodName);

            //获取方法对象
            Method getMethod = classType.getMethod(getMethodName, new Class[]{});
            Method setMethod = classType.getMethod(setMethodName, new Class[]{field.getType()});//注意set方法需要传入参数类型

            //调用get方法获取旧的对象的值
            Object value = getMethod.invoke(object, new Object[]{});
            //调用set方法将这个值复制到新的对象中去
            setMethod.invoke(objectCopy, new Object[]{value});

        }
        return objectCopy;
    }

    /**
     * 拷贝对象方法（适合同一类型的对象复制）
     *
     * @param objSource 源对象
     * @param clazz 目标类
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public  <T> T copy(Object objSource,Class<T> clazz) throws InstantiationException, IllegalAccessException{

        if(null == objSource){
            return null;//如果源对象为空，则直接返回null
        }

        T objDes = clazz.newInstance();

        // 获得源对象所有属性
        Field[] fields = clazz.getDeclaredFields();

        // 循环遍历字段，获取字段对应的属性值
        for ( Field field : fields )
        {
            // 如果不为空，设置可见性，然后返回
            field.setAccessible( true );

            try
            {
                // 设置字段可见，即可用get方法获取属性值。
                field.set(objDes, field.get(objSource));

            }
            catch ( Exception e )
            {
                logger.error("执行{}类的{}属性的set方法时出错。{}",clazz.getSimpleName(),field.getName(),e);
            }
        }
        return objDes;
    }

    /**
     * 拷贝对象方法（适合不同类型的转换）<br/>
     * 前提是，源类中的所有属性在目标类中都存在
     *
     * @param objSource 源对象
     * @param clazzSrc 源对象所属class
     * @param clazzDes 目标class
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public  <T, K> T copy(K objSource,Class<K> clazzSrc,Class<T> clazzDes) throws InstantiationException, IllegalAccessException{

        if(null == objSource){
            return null;//如果源对象为空，则直接返回null
        }
        T objDes = clazzDes.newInstance();

        // 获得源对象所有属性
        Field[] fields = clazzSrc.getDeclaredFields();
        // 循环遍历字段，获取字段对应的属性值
        for ( Field field : fields )
        {
            // 如果不为空，设置可见性，然后返回
            field.setAccessible( true );

            try
            {
                String fieldName = field.getName();// 属性名
                String firstLetter = fieldName.substring(0, 1).toUpperCase();// 获取属性首字母

                // 拼接set方法名
                String setMethodName = "set" + firstLetter + fieldName.substring(1);
                // 获取set方法对象
                Method setMethod = clazzDes.getMethod(setMethodName,new Class[]{field.getType()});
                // 对目标对象调用set方法装入属性值
                setMethod.invoke(objDes, field.get(objSource));
            }
            catch ( Exception e )
            {
                logger.error("执行{}类的{}属性的set方法时出错。{}",clazzDes.getSimpleName(),field.getName(),e);
            }
        }
        return objDes;
    }

}
