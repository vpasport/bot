import Bot.Bot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {
        System.getProperties().put( "proxySet", "true" );
        System.getProperties().put( "socksProxyHost", "178.62.59.71" );
        System.getProperties().put( "socksProxyPort", "4076");

        System.out.println("Proxy init");

        ApiContextInitializer.init();
        TelegramBotsApi telegram = new TelegramBotsApi();

        Bot bot = new Bot();

        try {
            telegram.registerBot( bot );
            System.out.println("Bot init");
        } catch ( TelegramApiException e ){
            e.printStackTrace();
        }
    }
}
