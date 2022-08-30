package org.sample.micronaut.cli;

import jakarta.inject.Singleton;

@Singleton
public class SampleSingleton {
    public void printMessage() {
        System.out.println("Singleton called");
    }
}
