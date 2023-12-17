package com.damdamdeo.hello.world.playground.test;

import com.damdamdeo.hello.world.playground.runtime.HelloWorld;
import com.damdamdeo.hello.world.playground.runtime.HelloWorldDependency;
import io.quarkus.arc.Arc;
import io.quarkus.arc.impl.InjectionPointProvider;
import io.quarkus.test.QuarkusUnitTest;
import jakarta.inject.Inject;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class HelloWorldPlaygroundTest {

    @RegisterExtension
    static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
                    .addClass(DefaultHelloWorldDependency.class)
                    .addClass(DefaultHelloWorld.class));

    @Inject
    HelloWorld<String> helloWorld;

    @Test
    public void shouldSayHello() {
//        Arc.container().instance(HelloWorldDependency.class).get();// null comment faire ???
//        Arc.container().instance("com.damdamdeo.hello.world.playground.runtime.HelloWorldDependency<java.lang.String>").get();// also null Generic is no more available at runtime
        assertAll(
                () -> assertThat(helloWorld.sayHello()).isEqualTo("DefaultHelloWorld"),
                () -> assertThat(helloWorld.sayHelloDependency()).isEqualTo("DefaultHelloWorldDependency")
        );
    }
}
