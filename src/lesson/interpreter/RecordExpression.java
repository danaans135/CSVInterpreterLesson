
package lesson.interpreter;

import java.util.ArrayList;

public class RecordExpression implements AbstractExpression {

    // <Record> ::= { <Field> (<Delimiter> <Field>)* } <EOL>
    @Override
    public void interpret(Context context) {
        context.getResult().add(new ArrayList<String>());
        AbstractExpression r = new FieldExpression();
        r.interpret(context);
        while (true) {
             if (context.ch() == '\n' || context.ch() == '\0') {
                 context.next();
                 break;
             }
            // context.isDelimiter();
            context.next();
            r.interpret(context);
        }

    }

}
