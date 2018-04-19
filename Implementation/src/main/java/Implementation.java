import java.io.File;

public class Implementation implements Plugin
{
    private boolean checkErrors = false;
    private String param;

    @Override
    public boolean load()
    {
        System.out.println(System.getProperty("user.dir"));
        return true;
    }

    @Override
    public boolean hasError()
    {
        return checkErrors;
    }

    @Override
    public void setParameters(String param)
    {
        this.param = param;
        System.out.println("Plugin loaded");
        File testForSandbox = new File("createdForTestingSandbox");
        testForSandbox.mkdirs();
        System.out.println(modifyString());
    }

    @Override
    public String getResult()
    {
        return this.param;
    }

    public void setError(boolean error)
    {
        checkErrors = error;
    }

    private String modifyString()
    {
        this.param = this.param + " modified by Muhammed";
        return this.param;
    }

}
