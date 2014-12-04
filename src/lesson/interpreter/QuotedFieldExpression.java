package lesson.interpreter;

public class QuotedFieldExpression implements AbstractExpression {

    // <QuotedField> :== <QuoteChar> <Anychar>* <QuoteChar>  ; See below for quite how flexible <anychar> is
    @Override
    public void interpret(Context context) {
        context.next();
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (context.ch() == '"' && context.ch(1) == '"') {
                sb.append(context.ch());
                context.next();
                context.next();
                continue;
            } else if (context.ch() == '"') {
                context.next();
                break;
            }
            sb.append(context.ch());
            context.next();
        }
        context.getResult().get(context.getResult().size() - 1).add(sb.toString());

    }

    private boolean isAnyChar() {
        return false;
    }

}
