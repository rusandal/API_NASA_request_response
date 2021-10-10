import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static final String NASA_URL = "https://api.nasa.gov/planetary/apod?api_key=xAGzYhjvJYcRjZwWWGVUDy8m9mVMQLVmhAXmkgvm";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
        // создание объекта запроса с произвольными заголовками
        HttpGet request = new HttpGet(NASA_URL);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        // отправка запроса
        CloseableHttpResponse response = httpClient.execute(request);
        // вывод полученных заголовков
        Arrays.stream(response.getAllHeaders()).forEach(System.out::println);

        NasaRequest posts = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
        });
        System.out.println(posts);
        String url = posts.getUrl();
        String[] mySplit = url.split("/");
        request = new HttpGet(url);
        String jpegBody="";
        String fileName=null;
        //
        if (posts.getMedia_type().equals("video")){
            fileName = posts.getTitle()+"_video.html";
        } else if (posts.getMedia_type().equals("image")){
            fileName = mySplit[mySplit.length-1];
            request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
            response = httpClient.execute(request);
            jpegBody = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        }
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            // перевод строки в массив байтов
            byte[] bytes = jpegBody.getBytes();
            // запись байтов в файл
            fos.write(bytes, 0, bytes.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
