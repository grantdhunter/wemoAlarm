package ca.granthunterdev.wemoalarm;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Sprinkles;

/**
 * Created by Grant on 2/13/2015.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        configureDataBase();
    }

    private void configureDataBase() {
        Sprinkles sprinkles = Sprinkles.init(getApplicationContext());

        sprinkles.addMigration(new Migration() {
            @Override
            protected void onPreMigrate() {
                // do nothing
            }

            @Override
            protected void doMigration(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL("CREATE TABLE ALARMS(" +
                                "id INT PRIMARY KEY AUTOINCREMENT," +
                                "deviceUdn TEXT," +
                                "name TEXT," +
                                "daysOfWeek INT," +
                                "hour INT," +
                                "minutes INT," +
                                "alarmSound TEXT," +
                                "enabled BOOLEAN" +
                                ");"
                );
            }

            @Override
            protected void onPostMigrate() {
                // do nothing
            }
        });
    }
}
