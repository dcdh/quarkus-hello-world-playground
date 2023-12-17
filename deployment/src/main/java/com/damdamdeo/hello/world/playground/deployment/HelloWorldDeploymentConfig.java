package com.damdamdeo.hello.world.playground.deployment;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = ConfigItem.PARENT, phase = ConfigPhase.BUILD_TIME)
public class HelloWorldDeploymentConfig {
    /**
     * should-generate-bean
     */
    @ConfigItem(name = "should-generate-bean", defaultValue = "false")
    boolean shouldGenerateBean;
}
