package com.damdamdeo.hello.world.playground.test;

import com.damdamdeo.hello.world.playground.runtime.HelloWorld;
import io.quarkus.test.QuarkusUnitTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class HelloWorldGeneratedPlaygroundTest {
    @RegisterExtension
    static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
                    .addAsResource(
                            new StringAsset("quarkus.should-generate-bean=true"),
                            "application.properties"));

    @Inject
    HelloWorld helloWorld;

    @Test
    public void shouldSayHello() {
        Assertions.assertThat(helloWorld.sayHello()).isEqualTo("HelloWorldGenerated");
    }
}
