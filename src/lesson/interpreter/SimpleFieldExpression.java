package lesson.interpreter;

import java.lang.Character.UnicodeBlock;

public class SimpleFieldExpression implements AbstractExpression {
    //<SimpleField> :== AlphaNum*  ; Any sequence of alpha-numeric characters
    @Override
    public void interpret(Context context) {
        StringBuilder sb = new StringBuilder();
        while (isAlphaNum(context.ch())) {
            sb.append(context.ch());
            context.next();
        }
        context.getResult().get(context.getResult().size() - 1).add(sb.toString());
    }

    private boolean isAlphaNum(char ch) {
        UnicodeBlock ub = Character.UnicodeBlock.of(ch);
        return (0x20 <= ch && ch <= 0x21) ||
                (0x23 <= ch && ch <= 0x2B) ||
                (0x2D <= ch && ch <= 0x7E) ||
                Character.UnicodeBlock.HIRAGANA.equals(ub) ||
                Character.UnicodeBlock.KATAKANA.equals(ub) ||
                Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS.equals(ub) ||
                Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(ub) ||
                Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION.equals(ub)
                ;
//        return ch != '\n' && ch != '\0';
    }

}
