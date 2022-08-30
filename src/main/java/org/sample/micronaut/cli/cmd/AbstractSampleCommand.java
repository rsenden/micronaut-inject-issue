package org.sample.micronaut.cli.cmd;

import org.sample.micronaut.cli.SampleSingleton;

import jakarta.inject.Inject;
import lombok.Getter;

public abstract class AbstractSampleCommand {
    @Getter @Inject private SampleSingleton singleton;
}
