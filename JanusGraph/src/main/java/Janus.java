import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphFactory;


public class Janus{
    
    public static  void firsrTest(){

        JanusGraph graph = JanusGraphFactory.build().set("storage.backend", "inmemory").open();

        //创建顶点
        Vertex v1 = graph.addVertex("USER");
        v1.property("uid", "100");
        System.out.println(v1.property("uid").value());
        v1.property("uid", "1001");
        System.out.println(v1.property("uid").value());
        v1.property("name", "张三");
        v1.property("age", 23);

        Vertex v2 = graph.addVertex("PHONE");
        v2.property("phone", "13811111111");

        //创建边
        Edge e12 = v1.addEdge("USER_PHONE", v2);
        e12.property("create_time", "2018-08-08");




        graph.tx().commit();

        graph.close();
        
    }

    public static void main(String[] args) {
        firsrTest();
    }
    
}
