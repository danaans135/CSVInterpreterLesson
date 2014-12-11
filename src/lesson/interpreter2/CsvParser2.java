package lesson.interpreter2;

public class CsvParser2 {

    public CsvData parse(String str) {
        Context2 context = new Context2(str);
        FileExpr fileExpr = new FileExpr();
        fileExpr.interpret(context);
        return context.getResult();
    }

}
