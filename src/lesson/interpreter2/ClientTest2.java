package lesson.interpreter2;

public class ClientTest2 {

    public static void main(String[] args) {
        String str = "a,b,c";
        CsvParser2 parser = new CsvParser2();
        CsvData ret = parser.parse(str);
        System.out.println(ret);
    }

}
