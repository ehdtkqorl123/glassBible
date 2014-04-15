package ca.paulshin.glass.bible.api;

import retrofit.RestAdapter;
import ca.paulshin.glass.bible.App;
import ca.paulshin.glass.bible.utils.DebugLog;

/**
 * Api for Bible.org
 */
public class Api {

    public static final String BUILD_DATE = "20140413";

    public static final String URL = "http://labs.bible.org";

    public static RestAdapter get() {
        return new RestAdapter.Builder().setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.BASIC).setLog(new RestAdapter.Log() {
            @Override
            public void log(String s) {
                if (App.DEBUG) {
                    DebugLog.i(s);
                }
            }
        }).build();
    }
}
