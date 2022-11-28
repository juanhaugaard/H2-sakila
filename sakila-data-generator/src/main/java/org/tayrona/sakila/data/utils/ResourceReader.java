package org.tayrona.sakila.data.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.zip.GZIPInputStream;

public class ResourceReader {
    final String resourceName;

    public ResourceReader(String resourceName) {
        this.resourceName = resourceName;
    }

    public void processLines(Consumer<String> consumer) throws IOException {
        ClassLoader cL = ResourceReader.class.getClassLoader();
        if (isGzip()) {
            processGzLines(consumer, cL);
        } else {
            processLines(consumer, cL);
        }
    }
    private void processGzLines(Consumer<String> consumer, ClassLoader cL) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new GZIPInputStream(
                                Objects.requireNonNull(
                                        cL.getResourceAsStream(resourceName)
                                )
                        )
                )
        )) {
            String line = bufferedReader.readLine();
            while (null != line) {
                consumer.accept(line);
                line = bufferedReader.readLine();
            }
        }
    }

    private void processLines(Consumer<String> consumer, ClassLoader cL) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                cL.getResourceAsStream(resourceName)
                        )
                )
        )) {
            String line = bufferedReader.readLine();
            while (null != line) {
                consumer.accept(line);
                line = bufferedReader.readLine();
            }
        }
    }
    private boolean isGzip() {
        return resourceName.toLowerCase().endsWith(".gz");
    }
}
