public class TestInnerClassDemo {
    InnerA innerA = new InnerA();
    public TestInnerClassDemo(){
        System.out.println("A");
    }
    static{System.out.println("B");}

    public static void main(String[] args) {
        System.out.println("C");
        TestInnerClassDemo demo = new TestInnerClassDemo();
    }

    class InnerA{
        public InnerA(){
            System.out.println("D");
        }
    }
}
