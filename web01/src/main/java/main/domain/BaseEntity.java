package main.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Administrator on 2016/3/31 0031.
 */

/**
 * 可以使用生命周期批注（请参阅 生命周期事件批注）指定实体中的方法，
 * 这些方法在指定的生命周期事件发生时执行您的逻辑。

 使用 @EntityListeners 批注将一个或多个实体监听程序类与 @Entity 或 @MappedSuperclass 关联，条件是您需要在指定的生命周期事件发生时执行逻辑
 */
@EntityListeners(EntityListeners.class)
@MappedSuperclass
public  class BaseEntity implements Serializable{

    private  static  final String ID_PROPERTY_NAME = "id";

    private static final String CREATE_DATE = "crate_date";

    private static final String MODIFY_DATE = "modify_date";

    private Long id;

    private Date createDate;

    private Date modifyDate;

    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @JsonProperty
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(nullable = false, updatable = false)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonProperty
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(nullable = false,updatable = false)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @PrePersist
    public void prePersist()
    {
        if (this.getCreateDate()==null)
            this.setCreateDate(new Date());
        if(this.getModifyDate()==null){
            this.setModifyDate(new Date());
        }
    }

    public BaseEntity() {

    }

    public BaseEntity(Long id) {
        this.id = id;
    }

    public BaseEntity(String id) {
        System.out.println("id = [" + id + "]");
    }

    public  String doSomething(){
        System.out.println("doSomething");
        return  "doSomething";
    }
    /*public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {

        String classPath = "main.domain.BaseEntity";

        Class aClass =Class.forName(classPath);
        Method[] methods = aClass.getMethods();
        Method method = aClass.getMethod("setId", Long.class);

        Constructor constructor = aClass.getConstructor(String.class);

        System.out.println("method = "+method);
        System.out.println("constructor = "+constructor);

        Method doSomething = aClass.getMethod("doSomething");
        System.out.println("args = [" + doSomething + "]");
        System.out.println("doSomething = " + doSomething);

        BaseEntity baseEntity = (BaseEntity) constructor.newInstance("id");

        Class<?>[] parameterTypes = doSomething.getParameterTypes();

        Object returnValue = doSomething.invoke(baseEntity);

        System.out.println("returnValue = " + returnValue);


        for (Method methodTemp:methods
             ) {
            if (isGetter(methodTemp)) System.out.println("getter = " + methodTemp);
            if (isSetter(methodTemp)) System.out.println("setter = " + methodTemp);
        }










    }*/

    public static boolean isGetter(Method method){
        if (!method.getName().startsWith("get"))return false;
        if (method.getParameterTypes().length!=0)return false;
        return !void.class.equals(method.getReturnType());
    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set"))return false;
        if(method.getParameterTypes().length!=1)return true;
        return true;
    }
}
