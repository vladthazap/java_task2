import java.lang.reflect.*;
import java.util.*;

public class CachingHandler<T> implements InvocationHandler {
    private T currentObject;
    private Map<Method,Object> results = new HashMap<>();

    public CachingHandler(T currentObject){
        this.currentObject = currentObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object objectResult;
        Method currentMethod;

        System.out.println(this.getClass().toString() + "::" + "invoke begin" );
        currentMethod = currentObject.getClass().getMethod(method.getName(), method.getParameterTypes());

        if (currentMethod.isAnnotationPresent(Cache.class)) {
            if (results.containsKey(currentMethod)) {
                System.out.println("cache hit" );
                return results.get(currentMethod);
            }

            objectResult = method.invoke(currentObject,args);
            results.put(currentMethod, objectResult);
            System.out.println("cache miss" );
            return objectResult ;
        }

        if (currentMethod.isAnnotationPresent(Mutator.class)) {
            System.out.println("cache clear" );
            results.clear();
        }

        return method.invoke(currentObject, args);


    }


}
