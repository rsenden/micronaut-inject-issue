package org.sample.micronaut.cli.cmd;

import com.fasterxml.jackson.annotation.JsonProperty;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name="standard-annotation")
public class SampleCommandStandardAnnotations extends AbstractSampleCommand implements Runnable {
    
    @Option(names={"t"}) @Deprecated @JsonProperty 
    private String t;

    @Override
    public void run() {
        getSingleton().printMessage();
    }

}
