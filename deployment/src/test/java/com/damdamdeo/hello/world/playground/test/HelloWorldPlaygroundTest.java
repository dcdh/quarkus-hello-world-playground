package com.damdamdeo.hello.world.playground.test;

import com.damdamdeo.hello.world.playground.runtime.HelloWorld;
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
        assertAll(
                () -> assertThat(helloWorld.sayHello()).isEqualTo("DefaultHelloWorld"),
                () -> assertThat(helloWorld.sayHelloDependency()).isEqualTo("DefaultHelloWorldDependency")
        );
    }
}
