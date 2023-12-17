package com.damdamdeo.hello.world.playground.deployment;

import com.damdamdeo.hello.world.playground.runtime.HelloWorld;
import com.damdamdeo.hello.world.playground.runtime.HelloWorldDependency;
import io.quarkus.arc.Arc;
import io.quarkus.arc.ArcContainer;
import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.InstanceHandle;
import io.quarkus.arc.deployment.GeneratedBeanBuildItem;
import io.quarkus.arc.deployment.GeneratedBeanGizmoAdaptor;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.gizmo.*;
import jakarta.inject.Singleton;
import org.objectweb.asm.Opcodes;

import java.lang.annotation.Annotation;
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
        final MethodCreator constructor = beanClassCreator.getMethodCreator(MethodDescriptor.INIT, void.class, HelloWorldDependency.class);
        constructor.setModifiers(Modifier.PUBLIC);
        ResultHandle supportHandle = getFromCDI(constructor, HelloWorldDependency.class.getName());
        constructor.invokeSpecialMethod(MethodDescriptor.ofConstructor(HelloWorld.class, HelloWorldDependency.class), constructor.getThis(), supportHandle);
        constructor.returnValue(null);

        // sayHello
        final MethodCreator clazzMethod = beanClassCreator.getMethodCreator("sayHello", Object.class);
        clazzMethod.setModifiers(Opcodes.ACC_PUBLIC);
        clazzMethod.returnValue(clazzMethod.load("HelloWorldGenerated"));
        beanClassCreator.close();
    }

    // https://github.com/quarkiverse/quarkus-langchain4j/blob/main/core/deployment/src/main/java/io/quarkiverse/langchain4j/deployment/AiServicesProcessor.java#L627 :)
    private ResultHandle getFromCDI(final MethodCreator mc, final String className) {
        // Arc.container().instance(className).get();
        final ResultHandle containerHandle = mc.invokeStaticMethod(MethodDescriptor.ofMethod(Arc.class, "container", ArcContainer.class));
        final ResultHandle instanceHandle = mc.invokeInterfaceMethod(
                MethodDescriptor.ofMethod(ArcContainer.class, "instance", InstanceHandle.class, Class.class,
                        Annotation[].class),
                containerHandle, mc.loadClassFromTCCL(className),
                mc.newArray(Annotation.class, 0));
        return mc.invokeInterfaceMethod(MethodDescriptor.ofMethod(InstanceHandle.class, "get", Object.class), instanceHandle);
    }

    private static class ShouldGenerateBean implements BooleanSupplier {
        HelloWorldDeploymentConfig helloWorldDeploymentConfig;

        @Override
        public boolean getAsBoolean() {
            return helloWorldDeploymentConfig.shouldGenerateBean;
        }
    }
}
