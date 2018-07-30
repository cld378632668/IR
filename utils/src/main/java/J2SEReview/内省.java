package J2SEReview;

/**
 * Created by ChenLD on 2018/4/3.
 *
 * @author ChenLD
 * @version 1.0
 */
public class 内省 {

//    内省
//
//    开发框架时，经常需要使用java对象的属性来封装程序的数据，每次都使用反射技术完成此类操作过于麻烦，所以sun公司开发了一套API，专门用于操作java对象的属性。
//
//    内省是用于操作java对象的属性的，那么以下问题我们必须要清楚。
//
//    问题一： 什么是Java对象的属性和属性的读写方法？
//
//    问题二: 如何通过内省访问到javaBean的属性 ?
//
//            1.通过PropertyDescriptor类操作Bean的属性.
//
//    public static void testPropertyDescriptor() throws Exception{
//
//        Person p = new Person();
//
//        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("id",Person.class);
//
////获取属性的写的方法。
//
//        Method writeMethod = propertyDescriptor.getWriteMethod();
//
//        Method readMethod = propertyDescriptor.getReadMethod();
//
//        propertyDescriptor.getReadMethod();
//
//        writeMethod.invoke(p, 12);
//
//        System.out.println(readMethod.invoke(p, null));
//
//    }
//
//2.通过Introspector类获得Bean对象的 BeanInfo，然后通过 BeanInfo 来获取属性的描述器（ PropertyDescriptor ），通过这个属性描述器就可以获取某个属性对应的 getter/setter 方法，然后通过反射机制来调用这些方法。
//
//    public static void testIntrospector() throws Exception{
//
//        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
//
//        PropertyDescriptor[] descriptor = beanInfo.getPropertyDescriptors();
//
//        for(PropertyDescriptor itemProperty : descriptor){
//
//            System.out.println(itemProperty.getReadMethod().getName());
//
//        }
//
//    }
//
//    存在的问题： sun公司的内省API过于繁琐，所以Apache组织结合很多实际开发中的应用场景开发了一套简单、易用的API操作Bean的属性——BeanUtils。
//
//    public static void main(String[] args) throws Exception {
//
//        Person p = new Person();
//
//        ConvertUtils.register(new Converter() {
//
//            @Override
//
//            public Object convert(Class type, Object value) {
//
//                try {
//
//                    if(value!=null){
//
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
//
//                        Date d = dateFormat.parse((String) value);
//
//                        return d;
//
//                    }
//
//                } catch (ParseException e) {
//
//// TODO Auto-generated catch block
//
//                    e.printStackTrace();
//
//                }
//
//                return null;
//
//            }
//
//        }, Date.class);
//
//        BeanUtils.setProperty(p,"id","110");
//
//        BeanUtils.setProperty(p,"name","狗娃");
//
//        BeanUtils.setProperty(p, "birthDay","1992 12 12");
//
//        System.out.println(p.getId() +"=="+ p.getName()+"======"+p.getBirthDay());
//
//    }
//
//    Properties
//
//    Properties类对应.properties文件。文件内容是键值对，键值对之间使用”=”或空格隔开。开头是”#”的表示注释
//
//    Properties类在加载.properties文件时使用的iso8859-1的编码。所以这个文件中的中文要特殊处理：如果这个配置文件中有中文就必须要进行转义，使用native2ascii.exe命令操作:
//
//    native2ascii d:/my.properties d:/my2.properties
//
//    使用Properties类中的load(InputStream) 方法可以加载配置文件，使用其中的store(OutputStream) 方法可以保存配置到指定文件。




}
