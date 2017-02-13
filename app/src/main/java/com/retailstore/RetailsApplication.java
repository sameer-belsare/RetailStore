package com.retailstore;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by sameer.belsare on 13/2/17.
 */

public class RetailsApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //Realm initialisation
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static Context getAppContext(){
        return context;
    }
}
