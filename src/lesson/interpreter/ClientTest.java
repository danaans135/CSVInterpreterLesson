package lesson.interpreter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ClientTest {

    @Test
    public void test() {
        AbstractExpression exp = new CSVFileExpression();
//        Context context = new Context("abc,def,ghi");
        Context context = new Context("abc,\"日本語\nです\",ghi\njk,lm,no");
        exp.interpret(context);
        List<List<String>> result = context.getResult();
//        assertArrayEquals(new String[] {"abc", "def", "ghi"}, result.get(0).toArray(new String[0]));
        assertArrayEquals(new String[] {"abc", "日本語\nです", "ghi"}, result.get(0).toArray(new String[0]));
        assertArrayEquals(new String[] {"jk", "lm", "no"}, result.get(1).toArray(new String[0]));
    }

}
