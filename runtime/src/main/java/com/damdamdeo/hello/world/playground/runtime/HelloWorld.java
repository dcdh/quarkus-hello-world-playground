package com.damdamdeo.hello.world.playground.runtime;

import java.util.Objects;

public abstract class HelloWorld<T> {
    private final HelloWorldDependency helloWorldDependency;

    protected HelloWorld(final HelloWorldDependency helloWorldDependency) {
        this.helloWorldDependency = Objects.requireNonNull(helloWorldDependency);
    }

    public abstract T sayHello();

    public String sayHelloDependency() {
        return helloWorldDependency.sayHello();
    }
}
