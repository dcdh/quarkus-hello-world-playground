package com.damdamdeo.hello.world.playground.test;

import com.damdamdeo.hello.world.playground.runtime.HelloWorld;
import com.damdamdeo.hello.world.playground.runtime.HelloWorldDependency;
import jakarta.inject.Singleton;

@Singleton
public class DefaultHelloWorld extends HelloWorld<String> {
    public DefaultHelloWorld(final HelloWorldDependency helloWorldDependency) {
        super(helloWorldDependency);
    }

    @Override
    public String sayHello() {
        return "DefaultHelloWorld";
    }
}
