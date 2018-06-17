package org.kpdev.eventq_pens.eventq;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String SP_USER_APP = "spUserApp";

    private static final String SP_NAME = "spName";
    private static final String SP_EMAIL = "spEmail";
    private static final String SP_ID = "spID";
    public static final String SP_KEY = "spKey";
    private static final String SP_NRP = "spNRP";
    private static final String SP_USERNAME = "spUsername";
    private static final String SP_PHONENUMBER = "spPhoneNumber";
    private static final String SP_SEX = "spSex";
    private static final String SP_DEPARTMENT = "spDepartment";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_USER_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
        spEditor.apply();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSpName() {
        return sp.getString(SP_NAME, "");
    }

    public String getSpEmail() {
        return sp.getString(SP_EMAIL, "");
    }

    public int getSpId() {
        return sp.getInt(SP_ID, 0);
    }

    public String getSpNrp() {
        return sp.getString(SP_NRP, "");
    }

    public String getSpUsername() {
        return sp.getString(SP_USERNAME, "");
    }

    public String getSpPhonenumber() {
        return sp.getString(SP_PHONENUMBER, "");
    }

    public String getSpSex() {
        return sp.getString(SP_SEX, "");
    }

    public String getSpDepartment() {
        return sp.getString(SP_DEPARTMENT, "");
    }

    public String getSpKey() {
        return sp.getString(SP_KEY, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
