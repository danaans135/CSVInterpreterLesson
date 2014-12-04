package lesson.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Context {

    private String mText;
    private String[] mSplit;
    private int curIdx;
    private List<List<String>> mResult = new ArrayList<List<String>>();

    public Context(String text) {
        mText = text;
        curIdx = 0;

//        mText.getChars(0, srcEnd, dst, dstBegin)
        Pattern p = Pattern.compile("(.*)\r\n");
        mSplit = mText.split("\r\n");

    }

    public List<List<String>> getResult() {
        return mResult ;
    }

    public String getCurrentExpression() {
        if (mSplit.length <= curIdx) return null;
        return mSplit[curIdx];
    }

    public void next() {
        curIdx++;
    }

    public char ch() {
//        if (curIdx >= mText.length()) return '\0';
//        return mText.charAt(curIdx);
        return ch(0);
    }

    public char ch(int offset) {
        if (curIdx+offset >= mText.length()) return '\0';
        return mText.charAt(curIdx+offset);
    }

}
