import java.util.HashMap;
import java.util.Map;

public class TestDemo {
    public static void main(String[] args) {
        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("username", "LeeLei");
        testMap.put("username","HanMeiMei");
        testMap.put("pwd","112435");
        StringBuilder hashStr = new StringBuilder();
        for(Map.Entry<String,String> set: testMap.entrySet()){
            String keyStr = set.getKey();
            int keyHash = set.getKey().hashCode();
            String valStr = set.getValue();
            hashStr.append("key:").append(keyStr).append(";keyhash:").append(keyHash).append(";value:").append(valStr);
        }
        System.out.println(hashStr);
        System.out.println(1 << 4);

        String strA = new String("hello");
        String strB  =strA;
        System.out.println("strA.hashcode=" + strA.hashCode());

        System.out.println("strB.hashcode=" + strB.hashCode());
        System.out.println(strA.equals(strB));
        System.out.println(strA==strB);
    }
}
