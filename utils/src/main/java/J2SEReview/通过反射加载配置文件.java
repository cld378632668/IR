package J2SEReview;

/**
 * Created by ChenLD on 2018/4/3.
 *
 * @author ChenLD
 * @version 1.0
 */
public class 通过反射加载配置文件 {
//
//    加载：
//
//    public static void testLoadProperties() throws Exception {
//
//        Properties properties = new Properties();
//
//        InputStream in = new FileInputStream("E:/itcast/config.properties");
//
//        properties.load(in); // 加载
//
//        in.close();
//
//        System.out.println(properties);
//
//    }
//
//    写配置文件：
//
//    public static void testStoreProperties() throws Exception {
//
//// 准备配置信息
//
//        Properties properties = new Properties();
//
//        properties.setProperty("name", "李四");
//
//        properties.setProperty("age", "20");
//
//// 准备
//
//        OutputStream out = new FileOutputStream("d:/my.properties");
//
//        String comments = "这是我的配置文件";
//
//// 写出去
//
//        properties.store(out, comments);
//
//        out.close();
//
//    }
//
//    案例：使用properties读取配置文件，读取数据库的用户名、密码
//
//    public class DBUtil {
//
//        static Properties properties = new Properties();
//
//        static{
//
//            try {
//
//                Class clazz = DBUtil.class;
//
//                InputStreamReader fileReader =
//
//                        new InputStreamReader(clazz.getResourceAsStream("/db.properties"));
//
//                properties.load(fileReader);
//
//            } catch (IOException e) {
//
//                e.printStackTrace();
//
//            }
//
//        }
//
//        public static String getUserName(){
//
//            String userName =properties.getProperty("userName");
//
//            return userName;
//
//        }
//
//        public static String getPassword(){
//
//            return properties.getProperty("password");
//
//        }
//
//        public static void main(String[] args) {
//
//            System.out.println("用户名："+ getUserName());
//
//            System.out.println("密码: "+ getPassword());
//
//        }
//
//    }

}
