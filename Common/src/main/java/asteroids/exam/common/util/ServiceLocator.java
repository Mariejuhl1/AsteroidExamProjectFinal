package asteroids.exam.common.util;

import java.util.*;
import java.lang.module.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public enum ServiceLocator {

    INSTANCE;

    private static final Map<Class<?>, ServiceLoader<?>> loaderMap = new HashMap<>();
    private final ModuleLayer layer;

    ServiceLocator() {
        try {
            Path pluginsDir = Paths.get("plugins");      // plugin path
            ModuleFinder finder = ModuleFinder.of(pluginsDir);
            List<String> names = finder.findAll().stream()
                    .map(ModuleReference::descriptor)
                    .map(ModuleDescriptor::name)
                    .collect(Collectors.toList());
            Configuration config = ModuleLayer.boot()
                    .configuration()
                    .resolve(finder, ModuleFinder.of(), names);
            layer = ModuleLayer.boot()
                    .defineModulesWithOneLoader(config, ClassLoader.getSystemClassLoader());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> locateAll(Class<T> service) {
        ServiceLoader<T> loader = (ServiceLoader<T>) loaderMap.get(service);
        if (loader == null) {
            loader = ServiceLoader.load(layer, service);
            loaderMap.put(service, loader);
        }
        List<T> list = new ArrayList<>();
        for (T inst : loader) {
            list.add(inst);                             // collect implementations
        }
        return list;
    }
}
