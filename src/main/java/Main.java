import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<InterceptedMessage> messages = new ArrayList<InterceptedMessage>();

//optional, default port is 8080
        int mitmproxyPort = 8080;

//optional, you can pass null if no extra params
        List<String> extraMitmproxyParams = Arrays.asList("param1", "value1", "param2", "value2");

// remember to set local OS proxy settings in the Network Preferences
       // MitmproxyJava proxy = new MitmproxyJava("/usr/local/bin/mitmdump", (InterceptedMessage m) -> {
        MitmproxyJava proxy = new MitmproxyJava("C:/Program Files (x86)/mitmproxy/bin/mitmdump", (InterceptedMessage m) -> {
            System.out.println("intercepted request for " + m.getRequest().getUrl());
            messages.add(m);
            return m;
        }, mitmproxyPort, extraMitmproxyParams);

        try {
            proxy.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
// do stuff
        try {
          //  proxy.stop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
