package Bot;

import Ivi.Film;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.URL;

public class Bot extends TelegramLongPollingBot {

    public String msg;
    private long chat_id;

    public void onUpdateReceived(Update update){
        update.getUpdateId();

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        chat_id = update.getMessage().getChatId();
        msg = update.getMessage().getText();
        sendMessage.setText(input(update.getMessage().getText())+" 111111 ");

        try{
            execute(sendMessage);
        } catch ( TelegramApiException e ){
            e.printStackTrace();
        }

    }

    public String input(String msg) {
        if(msg.contains("hi")){
            return  "hi";
        }
        if(msg.contains("film") || msg.contains("Film")){
            return getInfoFilm();
        }
        return getInfoFilm(msg);

    }

    public String getBotUsername(){
        return "@vpasportnikov_bot";
    }

    public  String getBotToken(){
        return "638217280:AAH_se077hJ1P7DWmhubJbO6R8_c1GkQ3Fo";
    }

    private String getInfoFilm(String msg) {
        System.out.println(2);
        //Film film = new Film();
        Film film = new Film(msg);
        SendPhoto sendPhotoRequest = new SendPhoto();

        try{
            URL url = new URL(film.getImg());
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream("C:\\Users\\paspo\\Desktop\\bot\\img\\img.jpg");
            byte[] buffer = new byte[1024];
            int count;

            while((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }

            sendPhotoRequest.setChatId(chat_id);
            sendPhotoRequest.setPhoto(new File("C:\\Users\\paspo\\Desktop\\bot\\img\\img.jpg"));
            execute(sendPhotoRequest);
            File file = new File( "C:\\Users\\paspo\\Desktop\\bot\\img\\img.jpg" );
            file.delete();

            fis.close();
            bis.close();
        } catch (IOException ex){
            System.out.println("file not found");
        } catch (TelegramApiException e){
            e.printStackTrace();
        }

        String info = film.getTitle() + "\n\n"
                + "Теги: " + film.getTags() + "\n\n"
                + "Рейтинг: " + film.getRating() + "\n\n"
                + "Актеры: " + film.getActors() + "\n\n"
                + film.getDescription();

        return info;
    }

    public String getInfoFilm(){
        Film film = new Film();
        SendPhoto sendPhotoRequest = new SendPhoto();

//        try{
//            URL url = new URL(film.getImg());
//            BufferedInputStream bis = new BufferedInputStream(url.openStream());
//            FileOutputStream fis = new FileOutputStream("C:\\Users\\paspo\\Desktop\\bot\\img\\img.jpg");
//            byte[] buffer = new byte[1024];
//            int count;
//
//            while((count = bis.read(buffer, 0, 1024)) != -1) {
//                fis.write(buffer, 0, count);
//            }
//
//            sendPhotoRequest.setChatId(chat_id);
//            sendPhotoRequest.setPhoto(new File("C:\\Users\\paspo\\Desktop\\bot\\img\\img.jpg"));
//            execute(sendPhotoRequest);
//            File file = new File( "C:\\Users\\paspo\\Desktop\\bot\\img\\img.jpg" );
//            file.delete();
//
//            fis.close();
//            bis.close();
//        } catch (IOException ex){
//            System.out.println("file not found");
//        } catch (TelegramApiException e){
//            e.printStackTrace();
//        }

        String info = film.getTitle() + "\n\n"
                + "Теги: " + film.getTags() + "\n\n"
                + "Рейтинг: " + film.getRating() + "\n\n"
                + "Актеры: " + film.getActors() + "\n\n"
                + film.getDescription();

        return info;
    }

}
