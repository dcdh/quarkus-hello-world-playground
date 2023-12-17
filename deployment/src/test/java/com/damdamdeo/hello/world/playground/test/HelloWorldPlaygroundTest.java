package com.damdamdeo.hello.world.playground.test;

import com.damdamdeo.hello.world.playground.runtime.HelloWorld;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;

public class HelloWorldPlaygroundTest {

    @RegisterExtension
    static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
        .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
                .addClass(DefaultHelloWorld.class));

    @Inject
    HelloWorld<String> helloWorld;

    @Test
    public void shouldSayHello() {
        Assertions.assertThat(helloWorld.sayHello()).isEqualTo("DefaultHelloWorld");
    }
}
