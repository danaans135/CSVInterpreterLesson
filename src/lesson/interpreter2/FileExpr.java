package lesson.interpreter2;

public class FileExpr implements AbstractExpression2 {

    private static final boolean HEADER_FLAG = true;

    // file = [header CRLF] record *(CRLF record) [CRLF]
    @Override
    public void interpret(Context2 context) {
        if (HEADER_FLAG) {
            HeaderExpr headerExpr = new HeaderExpr();
            headerExpr.interpret(context);
            CRExpr crExpr = new CRExpr();
            crExpr.interpret(context);
            if (context.isError()) return;
            LFExpr lfExpr = new LFExpr();
            lfExpr.interpret(context);
        }
    }

}
