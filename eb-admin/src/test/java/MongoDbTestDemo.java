import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.print.Doc;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Filter;


public class MongoDbTestDemo {

    private MongoClient mongoClient = null;

    private MongoCollection<Document> students = null;

    @Before
    public void initMongoDb(){
        //1:通过MongoClient链接上MongoDB数据库
        mongoClient = new MongoClient("127.0.0.1",27017);
        //2:获取将要操作的库
        MongoDatabase db= mongoClient.getDatabase("k10");
        //3:找到将要操作的集合
        students = db.getCollection("students");
    }
    @After
    public void close(){
        //5:关闭流
        mongoClient.close();
    }

    @Test
    public void insertOne(){
        //4:开始操作
        Document stuDoc = new Document();
        stuDoc.append("stuName","Kate");
        stuDoc.append("age", 17);
        stuDoc.append("sex","female");
        System.out.println(stuDoc);
        students.insertOne(stuDoc);
    }

    @Test
    public void insertOneByMap(){
        //4:开始操作
        Map<String,Object> stuMap = new HashMap<>();
        stuMap.put("stuName","John");
        stuMap.put("age",16);
        stuMap.put("sex","male");
        Document stuDoc = new Document(stuMap);
        students.insertOne(stuDoc);
    }

    @Test
    public void insertMulti(){
        Document fDoc = new Document();
        fDoc.append("stuName","Ausigu");
        fDoc.append("age",20);
        fDoc.append("sex","male");
        Document sDoc = new Document();
        sDoc.append("stuName","yuan").append("age",30).append("sex","male");
        Document tDoc = new Document();
        tDoc.append("stuName","Maria").append("age",28).append("sex","female");
        students.insertMany(Arrays.asList(fDoc,sDoc,tDoc));
    }

    /**
     * 查询所有与分页
     */
    @Test
    public void get(){
        //查询所有
        FindIterable<Document> documents = students.find();
        //分页
        documents.skip(0);
        documents.limit(8);
        //对结果进行遍历输出
        MongoCursor<Document> iterator = documents.iterator();
        iterator.forEachRemaining(temp-> System.out.println(temp));
    }

    //按照条件查询(查找年龄>18岁的)
    @Test
    public void getInCondition(){
        //Condition:查找成年人：db.students.find({"age":{$gte:18}});
        //方式1：
//        Document doc1 = new Document("$gte",18);
//        Document doc2 = new Document("age",doc1);
//        FindIterable<Document> result = students.find(doc2);
        //方式2：
//        Document doc3 = Document.parse("{\"age\":{$gte:18}}");
//        FindIterable<Document> result = students.find(doc3);
        //方式3：
        Bson age = Filters.gte("age", 18);
        FindIterable<Document> result = students.find(age);
        MongoCursor<Document> iterator = result.iterator();
        iterator.forEachRemaining(temp-> System.out.println(temp));
    }

    //多重条件查找
    @Test
    public void getInMultiCondions(){
        //Condition: 18<age<=30:db.students.find({$and:[{"age":{$gt:18}},{"age":{$lte:30}}]});
        //方式1：
//        Document doc1 = Document.parse("{$and:[{\"age\":{$gt:18}},{\"age\":{$lte:30}}]}");
//        FindIterable<Document> result = students.find(doc1);
        //方式2
        Bson age1 = Filters.gt("age", 18);
        Bson age2 = Filters.lte("age", 30);
        Bson age3 = Filters.and(age1, age2);
        FindIterable<Document> result = students.find(age3);
        MongoCursor<Document> iterator = result.iterator();
        iterator.forEachRemaining(temp-> System.out.println(temp));
    }

    //删除
    public void remove(){
//        DeleteResult deleteResult = students.deleteMany();
//        DeleteResult deleteResult1 = students.deleteOne();
    }

    //修改
    public void modify(){
//        UpdateResult updateResult = students.updateMany();
//        UpdateResult updateResult1 = students.updateOne();

    }
}
