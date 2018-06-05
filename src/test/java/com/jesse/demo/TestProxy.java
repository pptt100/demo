package com.jesse.demo;

/**
 * Created by Jesse on 2018/5/30.
 */
public class TestProxy {

    public static void main(String[] args) {

        Real real = new Real();

        Artificial artificial = new Artificial();

        Proxy proxy = new Proxy();

        proxy.setPerson(real);

        proxy.sayHello();

        proxy.setPerson(artificial);

        proxy.sayHello();

    }

}

interface Person {
    void sayHello();
}

class Proxy implements Person{

    private Person person;

    public Proxy() {
    }

    public Proxy(final Person person) {
        this.person = person;
    }

    @Override
    public void sayHello() {
        person.sayHello();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

class Real implements Person{

    @Override
    public void sayHello() {
        System.out.println("I Love You!!!");
    }
}

class Artificial implements Person{

    @Override
    public void sayHello() {
        System.out.println("I hate you!!!");
    }
}