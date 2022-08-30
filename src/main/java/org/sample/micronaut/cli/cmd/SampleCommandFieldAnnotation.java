package org.sample.micronaut.cli.cmd;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name="field-annotation")
public class SampleCommandFieldAnnotation extends AbstractSampleCommand implements Runnable {
    
    @Option(names={"t"}) @FieldAnnotation
    private String t;

    @Override
    public void run() {
        getSingleton().printMessage();
    }

    @Retention(RUNTIME)
    @Target(FIELD)
    @Inherited
    public static @interface FieldAnnotation {}
}
