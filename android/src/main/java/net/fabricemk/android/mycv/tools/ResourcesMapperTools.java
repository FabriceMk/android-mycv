package net.fabricemk.android.mycv.tools;

import android.content.Context;
import android.content.res.Resources;

import net.fabricemk.android.mycv.R;

public class ResourcesMapperTools {

    public static int getHeaderId(Context ctxt, String name) {
        return drawableMapper(ctxt, name, "header_", R.drawable.header_2);
    }

    public static int getCompanyIconId(Context ctxt, String name) {
        return drawableMapper(ctxt, name, "company_", 0);
    }

    public static int getSkillIconId(Context ctxt, String name) {
        return drawableMapper(ctxt, name, "skill_", R.drawable.ic_web);
    }

    private static int drawableMapper(Context ctxt, String name, String prefix, int defaultResource) {
        Resources resources = ctxt.getResources();
        int resourceId = resources.getIdentifier(prefix + name, "drawable",
                ctxt.getPackageName());

        if (resourceId == 0) {
            resourceId = defaultResource;
        }

        return resourceId;
    }
}
