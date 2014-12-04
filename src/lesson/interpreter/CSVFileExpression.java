package lesson.interpreter;

public class CSVFileExpression implements AbstractExpression {

    // <CSVFile> ::= <Record>*
    @Override
    public void interpret(Context context) {
        while (true) {
//            String record = context.getCurrentExpression();
//            if (record == null) break;
            if (context.ch() == '\0') break;
            RecordExpression r = new RecordExpression();
            r.interpret(context);
//            context.next();
        }
    }

}
