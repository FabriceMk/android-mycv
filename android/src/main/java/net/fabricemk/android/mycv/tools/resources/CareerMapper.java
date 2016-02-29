package net.fabricemk.android.mycv.tools.resources;

import net.fabricemk.android.mycv.R;

public class CareerMapper {

    public static int mappingIconIdFromName(String name) {
        int iconId = 0;

        switch (name) {
            case "appturbo": iconId = R.drawable.company_appturbo; break;
            case "rakuten": iconId = R.drawable.company_rakuten; break;
        }

        return iconId;

    }

    public static int mappingHeaderIdFromName(String name) {
        int iconId = 0;

        switch (name) {
            case "appturbo_header": iconId = R.drawable.appturbo_header; break;
            case "rakuten_header": iconId = R.drawable.rakuten_header; break;
        }

        return iconId;
    }
}
