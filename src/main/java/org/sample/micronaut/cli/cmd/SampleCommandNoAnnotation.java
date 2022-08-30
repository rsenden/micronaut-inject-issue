package org.sample.micronaut.cli.cmd;

import picocli.CommandLine.Command;

@Command(name="no-annotation")
public class SampleCommandNoAnnotation extends AbstractSampleCommand implements Runnable {

    @Override
    public void run() {
        getSingleton().printMessage();
    }

}
