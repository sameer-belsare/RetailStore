package com.retailstore;

import io.realm.Realm;

/**
 * Created by sameer.belsare on 13/2/17.
 */

public class ApplicationController {
    private static ApplicationController mInstance;
    private Realm realm;

    private ApplicationController(){

    }

    public static ApplicationController getInstance() {
        if (mInstance == null) {
            mInstance = new ApplicationController();
        }
        return mInstance;
    }

    public Realm getRealmInstance() {
        if (realm == null || realm.isClosed()) {
            realm = Realm.getDefaultInstance();
        }
        return realm;
    }

    public void closeRealmInstance() {
        if (realm != null && !realm.isClosed()) {
            realm.close();
            realm = null;
        }
    }
}
