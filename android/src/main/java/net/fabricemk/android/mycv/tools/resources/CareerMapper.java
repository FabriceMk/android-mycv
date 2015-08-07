package net.fabricemk.android.mycv.tools.resources;

import net.fabricemk.android.mycv.R;

public class CareerMapper {

    public static int mappingIconIdFromName(String name) {
        int iconId = R.drawable.ic_web; // Temp

        switch (name) {
            case "appturbo": iconId = R.drawable.company_appturbo; break;
        }

        return iconId;

    }

    public static int mappingHeaderIdFromName(String name) {
        int iconId = 0;

        switch (name) {
            case "appturbo_header": iconId = R.drawable.appturbo_header; break;
        }

        return iconId;
    }
}
