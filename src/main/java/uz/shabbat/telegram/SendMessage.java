package uz.shabbat.telegram;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SendMessage {
    private static SendMessage instance = null;

    private SendMessage() {
    }

    public static SendMessage getInstance() {
        if (instance == null) {
            instance = new SendMessage();
        }
        return instance;
    }

    public void send(String tgToken, int chatId, String txt) throws IOException {
        String urlToken = "https://api.telegram.org/bot" + tgToken + "/sendMessage";
        HttpURLConnection con = null;
        String urlParameters = "chat_id=" + chatId + "&text=" + txt;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        try {
            URL url = new URL(urlToken);
            con = (HttpURLConnection) url.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java upread.ru client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }
            StringBuilder content;
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
        } finally {
            assert con != null;
            con.disconnect();
        }

    }


}
