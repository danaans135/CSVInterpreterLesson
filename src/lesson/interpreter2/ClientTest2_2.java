package lesson.interpreter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ClientTest2_2 {

    private String text;
    private int index;
    private CsvData csvData;
    private List<String> tmpRecord;

    public static void main(String[] args) {
        ClientTest2_2 instance = new ClientTest2_2();
    }



    public ClientTest2_2() {
        String str = "a,  b  ,c,\"d,d\",e\r\n1,2,3,4\r\n5,6,7";
        csv(str);
//        System.out.println(ret);
    }



    public void csv(String text) {
        this.text = text;
        index = 0;
        csvData = new CsvData();
        file();
        System.out.println(csvData);
//        csvData.row(1);
//        csvData.cell(1,1);
    }

    // file = [header CRLF] record *(CRLF record) [CRLF]
    private void file() {
        header();
        String crlfStr = CRLF();
        if (crlfStr == null) return;
        record();
        while (true) {
            crlfStr = CRLF();
            if (crlfStr == null) break;
            record();
        }
    }

    private void preRecord() {
        tmpRecord = new ArrayList<>();
    }

    private void postRecord() {
//        csvData.getRecords().add(fields);
//        System.out.println(fields);
        csvData.getRecords().add(tmpRecord);
    }

    // record = field *(COMMA field)
    private void record() {
        preRecord();
        List<String> fields = new ArrayList<>();
        fields.add(field());
        String commaStr = null;
        while (true) {
            commaStr = COMMA();
            if (commaStr == null) break;
            String fieldStr = field();
            if (fieldStr == null) break;

            fields.add(fieldStr);
        }
        csvData.getRecords().add(fields);
        System.out.println(fields);
    }

    // header = name *(COMMA name)
    private void header() {
        csvData.getHeader().add(name());
//        List<String> names = new ArrayList<>();
//        names.add(name());
        String str = null;
        while (true) {
            str = COMMA();
            if (str != null) {
                csvData.getHeader().add(name());
            } else {
                break;
            }
        }
        System.out.println(csvData.getHeader());
    }

//    private void seq(List<Function<Boolean, String>> slist) {
//        for (Function<Boolean, String> func : slist) {
//            String ret = func. apply();
//        }
//    }

    private String name() {
        return field();
    }

    private String field() {
        String str = escaped();
        if (str == null) { str = nonEscaped(); }
        return str;
    }

    private String escaped() {
        int saveIndex = index;

        String str = DQUOTE();
        if (str == null) { return null;}

        StringBuilder sb = new StringBuilder(str);
        while (true) {
            str = TEXTDATA();
            if (str == null) { str = COMMA(); }

            if (str != null) { sb.append(str); }
            else { break; }
        }
        str = DQUOTE();
        if (str == null) {
            index = saveIndex;
            return null;
        }
        sb.append(str);
        return sb.toString();
    }

    private String nonEscaped() {
        String str = null;
        StringBuilder sb = new StringBuilder();
        while (true) {
            str = TEXTDATA();

            if (str != null) { sb.append(str); }
            else { break; }
        }
        return sb.toString();
    }

    private String CRLF() {
        String ret = null;
        if (0x0d ==ch() && ch(1) == 0x0a) {
            ret = new String(new char[] { ch(), ch(1) });
            index += 2;
        }
        return ret;
    }

    private String COMMA() {
        String ret = null;
        if (ch() == 0x2C) {
            ret = new String(new char[] { ch() });
            index++;
        }
        return ret;
    }

    private String DQUOTE() {
        String ret = null;
        if (ch() == 0x22) {
            ret = new String(new char[] { ch() });
            index++;
        }
        return ret;
    }

    private String TEXTDATA() {
        String ret = null;
        if ((0x20 <=ch() && ch() <= 0x21) ||
                (0x23 <=ch() && ch() <= 0x2B) ||
                (0x2D <=ch() && ch() <= 0x7E)) {
            ret = new String(new char[] { ch() });
            index++;
        }
        return ret;
    }

    private char ch() {
        return ch(0);
    }

    private char ch(int offset) {
        if (text.length() <= index+offset) return '\0';
        return text.charAt(index+offset);
    }
}
