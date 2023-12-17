package com.damdamdeo.hello.world.playground.runtime;

import java.util.Objects;

public abstract class HelloWorld<T> {
    private final HelloWorldDependency<T> helloWorldDependency;

    protected HelloWorld(final HelloWorldDependency<T> helloWorldDependency) {
        this.helloWorldDependency = Objects.requireNonNull(helloWorldDependency);
    }

    public abstract T sayHello();

    public T sayHelloDependency() {
        return helloWorldDependency.sayHello();
    }
}
