package com.damdamdeo.hello.world.playground.test;

import com.damdamdeo.hello.world.playground.runtime.HelloWorld;
import jakarta.inject.Singleton;

@Singleton
public class DefaultHelloWorld extends HelloWorld<String> {
    @Override
    public String sayHello() {
        return "DefaultHelloWorld";
    }
}
