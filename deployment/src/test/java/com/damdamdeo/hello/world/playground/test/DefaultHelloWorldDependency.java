package com.damdamdeo.hello.world.playground.test;

import com.damdamdeo.hello.world.playground.runtime.HelloWorldDependency;
import jakarta.inject.Singleton;

@Singleton
public class DefaultHelloWorldDependency implements HelloWorldDependency {
    @Override
    public String sayHello() {
        return "DefaultHelloWorldDependency";
    }
}
