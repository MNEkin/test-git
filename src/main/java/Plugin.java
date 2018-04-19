public interface Plugin
{
    boolean load();

    boolean hasError();

    void setParameters(String param);

    String getResult();


}
