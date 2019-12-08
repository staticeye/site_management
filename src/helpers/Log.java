package helpers;

public class Log {
    String key;
    Exception message;

    public Log(String key, Exception message) {
        this.key = key;
        this.message = message;
    }

    public void error() {
        System.out.println("[error]" + key + " " + "|" + " " + message);
    }

    public void info() {
        System.out.println("[info]" + key + " " + "|" + " " + message);
    }
}
