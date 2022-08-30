package org.sample.micronaut.cli.cmd;

import org.sample.micronaut.cli.FixInjection;

import picocli.CommandLine.Command;

@Command(name="annotated-subclass") @FixInjection
public class SampleCommandSubclassAnnotation extends AbstractSampleCommand implements Runnable {

    @Override
    public void run() {
        getSingleton().printMessage();
    }

}
