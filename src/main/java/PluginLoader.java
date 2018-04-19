import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarFile;

public class PluginLoader
{
    private final String PLUGIN_FOLDER = "plugins";
    private ArrayList<URL> urls = new ArrayList <>();
    private ArrayList<String> classes = new ArrayList <>();

    private File[] getJarFiles()
    {
        File pluginFolder = new File(PLUGIN_FOLDER);
        File[] jarFiles = pluginFolder.listFiles((dir, name) -> name.endsWith(".jar"));
        return jarFiles;
    }

    private void getClassesInJars()
    {
        File[] jarFiles = getJarFiles();
        if (jarFiles != null)
        {
            for (File file : jarFiles)
            {
                try
                {
                    JarFile jarFile = new JarFile(file);
                    urls.add(new URL("jar:file:" + PLUGIN_FOLDER + "/" + file.getName() + "!/"));
                    System.out.println("jar:file:" + PLUGIN_FOLDER + "/" + file.getName() + "!/");
                    jarFile.stream().forEach(jarEntry ->
                    {
                        if (jarEntry.getName().endsWith(".class"))
                        {
                            classes.add(jarEntry.getName());
                        }
                    });
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadPlugin()
    {
        getClassesInJars();
        URLClassLoader pluginLoader = new PluginClassLoader(urls.toArray(new URL[urls.size()]));
        classes.forEach(s ->
        {
            try
            {
                //This won't work if not replaced
                Class classs = pluginLoader.loadClass(s.replaceAll("/", ".").replace(".class", ""));
                if (checkInterface(classs.getInterfaces()))
                {
                    Plugin plugin = (Plugin) classs.newInstance();
                    /*if (plugin.load())
                    {
                        System.out.println("Loaded plugin " + classs.getCanonicalName() + " successfully");
                    }*/
                    plugin.setParameters("String parameter");
                }

            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
            {
                e.printStackTrace();
            }
        });
    }

    public boolean checkInterface(Class[] interfaces)
    {
        for (Class anInterface : interfaces)
        {
            if (anInterface == Plugin.class)
            {
                return true;
            }
        }
        return false;
    }
}
