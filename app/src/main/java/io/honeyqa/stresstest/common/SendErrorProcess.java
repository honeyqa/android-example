package io.honeyqa.stresstest.common;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.util.concurrent.TimeUnit;

import io.honeyqa.client.collector.ErrorReport;

public class SendErrorProcess extends Thread {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private ErrorReport report;
    private String url;

    public SendErrorProcess(ErrorReport report, String url) {
        // TODO Auto-generated constructor stub
        this.report = report;
        this.url = url;
    }

    @Override
    public void run() {
        try {
            OkHttpClient client = new OkHttpClient();
            setTimeout(client);
            RequestBody body = RequestBody.create(JSON, report.ErrorData.toString());
            Request.Builder r = new Request.Builder()
                    .header("Content-Type", "application/json; charset=utf-8")
                    .addHeader("version", "1.0.0")
                    .url(url)
                    .post(body);
            Response response = client.newCall(r.build()).execute();
            String jsondata = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setTimeout(OkHttpClient client) {
        client.setConnectTimeout(5, TimeUnit.SECONDS);
        client.setReadTimeout(5, TimeUnit.SECONDS);
    }
}