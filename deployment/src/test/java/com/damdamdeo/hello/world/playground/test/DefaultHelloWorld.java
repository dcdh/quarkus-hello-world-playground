package com.damdamdeo.hello.world.playground.test;

import com.damdamdeo.hello.world.playground.runtime.HelloWorld;
import jakarta.inject.Singleton;

@Singleton
public class DefaultHelloWorld implements HelloWorld<String> {
    @Override
    public String sayHello() {
        return "DefaultHelloWorld";
    }
}
