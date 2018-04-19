import java.security.Policy;

public class App
{
    public static void main(String[] args)
    {
        Policy.setPolicy(new SandboxForPlugin());
        System.setSecurityManager(new SecurityManager());
        PluginLoader pluginLoader = new PluginLoader();
        pluginLoader.loadPlugin();

    }//end of main method

}//end of App class
