package J2SEReview;

/**
 * Created by ChenLD on 2018/4/2.
 *
 * @author ChenLD
 * @version 1.0
 *          泛型复习练习
 */

/**
 * 创建对象的时候要指定泛型的具体类型，除非你希望你使用默认的Object类型。
 */
public class GenericType {

    /**
     * 函數泛型
     */
    public <T> T getData(T data){
        return data;
    }


}

/**
 * 类泛型 和 非静态函数泛型， 静态成员函数的泛型
 *
 * 类上面声明的泛型<T>只能应用于非静态成员函数，如果静态函数需要使用泛型，
 * 那么需要在函数上独立声明（另外使用符号E声明，我的理解是不能和原有的T重名）。见下面代码。
 *
 * @param <T>
 */
 class GenericClass<T>{

    /**
     *  因为类名后面已经标注<T>了， 所以类的函数的修饰符中不用标注<T> 也即下面不用写成 public <T> T getData(T data) 了**
     * @param data
     * @return
     */
     public T getData(T data){
         return data;
     }

    public T  getData2(T data){  //因为类上面已经有<T>了， 所以这里不用写成 public<T> T getData(T data) 了**
        return data;
    }

    public static void main(String[] args) {
//        System.out.println(getData(100)); //报错， 静态方法不可以使用类中定义的泛型（函数）
//        System.out.println(getData2(100)); //报错， 静态方法不可以使用类中定义的泛型（函数）

    }
}

