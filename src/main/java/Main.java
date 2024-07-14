import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("Main begin");
        Fractionable fr = new Fraction(1,2);
        fr = Utils.cache(fr);
        System.out.println("1 call: doubleValue=" + fr.doubleValue());
        System.out.println("2 call: doubleValue=" + fr.doubleValue());
        fr.setDenum(4);
        System.out.println("After setDenum call: doubleValue=" + fr.doubleValue());
        System.out.println("Main end");
    }


}
