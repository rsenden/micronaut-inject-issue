/*******************************************************************************
 * (c) Copyright 2021 Micro Focus or one of its affiliates
 *
 * Permission is hereby granted, free of charge, to any person obtaining a 
 * copy of this software and associated documentation files (the 
 * "Software"), to deal in the Software without restriction, including without 
 * limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to 
 * whom the Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY 
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 * IN THE SOFTWARE.
 ******************************************************************************/
package org.sample.micronaut.cli;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.apache.commons.logging.impl.SimpleLog;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeReflection;
import org.jasypt.normalization.Normalizer;
import org.sample.micronaut.cli.cmd.SampleCommands;

import com.oracle.svm.core.annotate.AutomaticFeature;

import io.micronaut.configuration.picocli.MicronautFactory;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;
import picocli.CommandLine;

public class SampleCLI {
    public static void main(String[] args) {
        System.exit(execute(args));
    }

    private static int execute(String[] args) {
        try (ApplicationContext applicationContext = ApplicationContext.builder(SampleCLI.class, Environment.CLI).start()) {
            try ( MicronautFactory micronautFactory = new MicronautFactory(applicationContext) ) {
                CommandLine commandLine = new CommandLine(SampleCommands.class, micronautFactory);
                return commandLine.execute(args);
            }
        }
    }
    
    /**
     * Register classes for runtime reflection in GraalVM native images
     */
    @AutomaticFeature
    public static final class RuntimeReflectionRegistrationFeature implements Feature {
        public void beforeAnalysis(BeforeAnalysisAccess access) {
            // This jasypt class uses reflection, so we perform a dummy operation to have GraalVM native image generation detect this
            Normalizer.normalizeToNfc("dummy");
            
            // TODO Review whether these are all necessary
            RuntimeReflection.register(String.class);
            RuntimeReflection.register(LogFactoryImpl.class);
            RuntimeReflection.register(LogFactoryImpl.class.getDeclaredConstructors());
            RuntimeReflection.register(LogFactory.class);
            RuntimeReflection.register(LogFactory.class.getDeclaredConstructors());
            RuntimeReflection.register(SimpleLog.class);
            RuntimeReflection.register(SimpleLog.class.getDeclaredConstructors());
        }
    }
}
