package org.sample.micronaut.cli.cmd;

import org.sample.micronaut.cli.FixInjection;
import org.sample.micronaut.cli.SampleSingleton;

import jakarta.inject.Inject;
import lombok.Getter;

@FixInjection
public abstract class AbstractAnnotatedSampleCommand {
    @Getter @Inject private SampleSingleton singleton;
}
