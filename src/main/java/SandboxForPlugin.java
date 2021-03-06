import java.security.*;

public class SandboxForPlugin extends Policy
{
    @Override
    public PermissionCollection getPermissions(ProtectionDomain domain)
    {
        if (isPlugin(domain))
        {
            return pluginPermissions();
        }
        else
        {
            return applicationPermissions();
        }
    }

    private boolean isPlugin(ProtectionDomain domain)
    {
        return domain.getClassLoader() instanceof PluginClassLoader;
    }

    private PermissionCollection pluginPermissions()
    {
        Permissions permissions = new Permissions();
//        permissions.add(new AllPermission());

        return permissions;
    }

    private PermissionCollection applicationPermissions()
    {
        Permissions permissions = new Permissions();
        permissions.add(new AllPermission());
        return permissions;
    }
}
