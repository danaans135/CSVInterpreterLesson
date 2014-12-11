package lesson.interpreter2;

public class HeaderExpr implements AbstractExpression2 {

    // name *(COMMA name)
    @Override
    public void interpret(Context2 context) {
        NameExpr nameExpr = new NameExpr();
        nameExpr.interpret(context);
        while (true) {
//            COMMAExpr commaExpr = new COMMAExpr();
//            commaExpr.interpret(context);
            if (context.ch() == ',') {
                context.next();
            } else {
                break;
            }
            nameExpr = new NameExpr();
            nameExpr.interpret(context);
        }
    }

}
