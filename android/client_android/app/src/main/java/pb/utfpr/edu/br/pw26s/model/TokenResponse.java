package pb.utfpr.edu.br.pw26s.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TokenResponse {
    private String access_token;

    public String getToken() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public static String getTokenSharedPreferences(
            Context context){
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getString("access_token", "");
    }

    public void setTokenSharedPreferences(String token, Context context){
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("access_token",  token);
        editor.apply();
    }
}
