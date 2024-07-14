import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class Utils {
    @SuppressWarnings("unchecked")
    public static <T> T cache(T objectIncome)
    {
        return (T) Proxy.newProxyInstance(objectIncome.getClass().getClassLoader(),
                objectIncome.getClass().getInterfaces(),
                new CachingHandler(objectIncome));
    }
}
