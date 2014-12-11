package lesson.interpreter2;

import java.util.ArrayList;
import java.util.List;

public class Context2 {

    private String text;
    private boolean isError;
    private int curIdx;

    public Context2(String text) {
        this.text = text;
        this.isError = false;
    }

    public CsvData getResult() {
        return null;
    }

    public boolean isError() {
        return isError;
    }

    public void next() {
        curIdx++;
    }

    public char ch() {
        return ch(0);
    }

    public char ch(int offset) {
        if (curIdx+offset >= text.length()) return '\0';
        return text.charAt(curIdx+offset);
    }

}
