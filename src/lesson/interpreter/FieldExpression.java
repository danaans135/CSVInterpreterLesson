package lesson.interpreter;

public class FieldExpression implements AbstractExpression {

    // <Field> ::= <SimpleField> <QuotedField>
    @Override
    public void interpret(Context context) {
        if (context.ch() == '"' || context.ch() == '\'') {
            AbstractExpression r = new QuotedFieldExpression();
            r.interpret(context);
        } else {
            AbstractExpression r = new SimpleFieldExpression();
            r.interpret(context);
        }

    }

}
