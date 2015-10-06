package io.honeyqa.stresstest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Timer;
import java.util.TimerTask;

import io.honeyqa.client.collector.ErrorReport;
import io.honeyqa.client.collector.ErrorReportFactory;
import io.honeyqa.client.rank.ErrorRank;
import io.honeyqa.stresstest.app.App;
import io.honeyqa.stresstest.app.AppPreferences;
import io.honeyqa.stresstest.common.ExceptionCommand;
import io.honeyqa.stresstest.common.ExceptionFactory;
import io.honeyqa.stresstest.common.FileHelper;
import io.honeyqa.stresstest.common.Information;
import io.honeyqa.stresstest.common.SendErrorProcess;

public class ExecuteService extends Service {

    private static final String URL = "http://ur-qa.com";
    private static final String URL_PATH = "/urqa/client/send/exception";

    private Timer mTimer;

    public ExecuteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mTimer = new Timer(ExecuteService.class.getSimpleName(), true);
        FileHelper.getInstance().init();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Preconditions.checkNotNull(App.getPreferences());


        run();

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }

    public void run() {
        final long TIME = App.getPreferences().getLong(AppPreferences.KEY_TIME, AppPreferences.DEFAULT_TIME);
        final long COUNT = App.getPreferences().getLong(AppPreferences.KEY_COUNT, AppPreferences.DEFAULT_COUNT);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < COUNT; i++) {
                    worker();
                }
            }
        };
        mTimer.schedule(task, 0, TIME);
    }

    public void worker() {
        DateTime now = DateTime.now(DateTimeZone.forID("Asia/Seoul"));
        Information information = new Information();
        try {
            ExceptionCommand command = ExceptionFactory.getRandom();

            information.setMillis(now.getMillis());
            information.setExceptionMessage(command.name());

            command.execute();
        } catch (Exception e) {
            ErrorReport report = ErrorReportFactory.createErrorReport(e, "", ErrorRank.Critical, App.getContext());
            SendErrorProcess process = new SendErrorProcess(report, url());
            process.start();
        } finally {
            Log.i("SERVICE", information.toString());
        }
    }


    public void workerFile(Information information) throws Exception {
        Gson gson = new Gson();
        FileHelper fileHelper = FileHelper.getInstance();
        fileHelper.append(gson.toJson(information));
    }


    public String url() {
        long port = App.getPreferences().getLong(AppPreferences.KEY_PORT, AppPreferences.DEFAULT_PORT);
        return URL + ":" + port + URL_PATH;
    }

}
