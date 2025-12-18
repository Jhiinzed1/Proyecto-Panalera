package arg.org.centro35.Panalera.test;

public class TestProperties {
    public static void main(String[] args) {
        System.getProperties().forEach((k,v)->System.out.println(k+": "+v));
    }
}
