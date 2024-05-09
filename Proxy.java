import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyPattern {
    public static void main(String[] args) {
        Man man = new Man("Yogesh","Chennai", 24);
        ClassLoader classLoader = man.getClass().getClassLoader();
        Class[] interfaces = man.getClass().getInterfaces();
//         Person personProxy = man;
        Person personProxy = (Person) Proxy.newProxyInstance(classLoader, interfaces, new PersonInvocationHandler(man));
        personProxy.introduce();
//        personProxy.sayAge();
//        personProxy.fromWhere();
    }
}

class PersonInvocationHandler implements InvocationHandler{
    private final Person person;
    PersonInvocationHandler(Person person){
        this.person = person;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        String name = method.getName();
        System.out.println(name);
        return method.invoke(person, args);
    }
}

interface Person{
    void introduce();
    void sayAge();
    void fromWhere();
}

class Man implements Person{
    private final String name;
    private final String city;
    private final Integer age;

    public Man(String name, String city, Integer age){
        this.name = name;
        this.city = city;
        this.age = age;
    }
    @Override
    public void introduce() {
        System.out.println("Hi i am "+name);
    }

    @Override
    public void sayAge() {
        System.out.println("My age is "+age);
    }

    @Override
    public void fromWhere() {
        System.out.println("I am from "+city);
    }
}
