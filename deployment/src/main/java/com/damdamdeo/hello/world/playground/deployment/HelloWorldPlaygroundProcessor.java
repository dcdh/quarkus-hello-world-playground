package com.damdamdeo.hello.world.playground.deployment;

import com.damdamdeo.hello.world.playground.runtime.HelloWorld;
import com.damdamdeo.hello.world.playground.runtime.HelloWorldDependency;
import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.deployment.GeneratedBeanBuildItem;
import io.quarkus.arc.deployment.GeneratedBeanGizmoAdaptor;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.gizmo.*;
import jakarta.inject.Singleton;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Modifier;
import java.util.function.BooleanSupplier;

class HelloWorldPlaygroundProcessor {

    private static final String FEATURE = "hello-world-playground";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep(onlyIf = ShouldGenerateBean.class)
    void helloWorldGenerator(final BuildProducer<GeneratedBeanBuildItem> generatedBeanBuildItemBuildProducer) {
        final ClassOutput beansClassOutput = new GeneratedBeanGizmoAdaptor(generatedBeanBuildItemBuildProducer);
        final ClassCreator beanClassCreator = ClassCreator.builder().classOutput(beansClassOutput)
                .className(HelloWorld.class.getName() + "Generated")
                .signature(
                        SignatureBuilder.forClass()
                                .setSuperClass(
                                        Type.parameterizedType(
                                                Type.classType(HelloWorld.class),
                                                Type.classType(String.class))))
                .build();
        beanClassCreator.addAnnotation(Singleton.class);
        beanClassCreator.addAnnotation(DefaultBean.class);
        // constructor
        MethodCreator constructor = beanClassCreator.getMethodCreator(MethodDescriptor.INIT, void.class, HelloWorldDependency.class);
        constructor.setSignature(
                SignatureBuilder.forMethod()
                        .addParameterType(Type.parameterizedType(Type.classType(HelloWorldDependency.class), Type.classType(String.class)))
                        .setReturnType(Type.voidType())
                        .build());
        constructor.setModifiers(Modifier.PUBLIC);
        constructor.invokeSpecialMethod(MethodDescriptor.ofConstructor(HelloWorld.class, HelloWorldDependency.class), constructor.getThis(), constructor.getMethodParam(0));
        constructor.returnVoid();

        // sayHello
        final MethodCreator clazzMethod = beanClassCreator.getMethodCreator("sayHello", Object.class);
        clazzMethod.setModifiers(Opcodes.ACC_PUBLIC);
        clazzMethod.returnValue(clazzMethod.load("HelloWorldGenerated"));
        beanClassCreator.close();
    }

    private static class ShouldGenerateBean implements BooleanSupplier {
        HelloWorldDeploymentConfig helloWorldDeploymentConfig;

        @Override
        public boolean getAsBoolean() {
            return helloWorldDeploymentConfig.shouldGenerateBean;
        }
    }
}
