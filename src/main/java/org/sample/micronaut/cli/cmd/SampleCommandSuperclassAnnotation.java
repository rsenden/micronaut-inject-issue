package org.sample.micronaut.cli.cmd;

import picocli.CommandLine.Command;

@Command(name="annotated-superclass")
public class SampleCommandSuperclassAnnotation extends AbstractAnnotatedSampleCommand implements Runnable {

    @Override
    public void run() {
        getSingleton().printMessage();
    }

}
