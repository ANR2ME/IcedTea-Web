package net.adoptopenjdk.icedteaweb.integration.classloader;

import net.adoptopenjdk.icedteaweb.Assert;
import net.adoptopenjdk.icedteaweb.classloader.JnlpApplicationClassLoader;
import net.adoptopenjdk.icedteaweb.classloader.Part;
import net.adoptopenjdk.icedteaweb.classloader.PartsHandler;
import net.adoptopenjdk.icedteaweb.jnlp.element.resource.JARDesc;
import net.sourceforge.jnlp.JNLPFile;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DummyPartsHandler extends PartsHandler {

    public DummyPartsHandler(final List<Part> parts, final JNLPFile file) {
        super(parts, file);
    }

    private final List<JARDesc> downloaded = new CopyOnWriteArrayList<>();

    @Override
    protected void validateJars(List<JnlpApplicationClassLoader.LoadableJar> jars) {
        // do nothing
    }

    @Override
    protected URL getLocalUrlForJar(final JARDesc jarDesc) {
        Assert.requireNonNull(jarDesc, "jarDesc");
        if(downloaded.contains(jarDesc)) {
            throw new IllegalStateException("Already downloaded " + jarDesc.getLocation());
        }
        System.out.println("Should load " + jarDesc.getLocation());
        downloaded.add(jarDesc);
        return jarDesc.getLocation();
    }

    public boolean hasTriedToDownload(final String name) {
        return downloaded.stream()
                .anyMatch(jar -> jar.getLocation().toString().endsWith(name));
    }

    public List<JARDesc> getDownloaded() {
        return Collections.unmodifiableList(downloaded);
    }
}
