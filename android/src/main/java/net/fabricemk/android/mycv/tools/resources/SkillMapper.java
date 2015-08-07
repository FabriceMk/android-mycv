package net.fabricemk.android.mycv.tools.resources;

import net.fabricemk.android.mycv.R;

public class SkillMapper {

    public static int mappingIconIdFromName(String name) {
        int iconId = R.drawable.ic_web; // Temp

        switch (name) {
            case "android": iconId = R.drawable.skill_android; break;
            case "java": iconId = R.drawable.skill_java; break;
            case "php": iconId = R.drawable.skill_php; break;
            case "html": iconId = R.drawable.skill_html; break;
            case "javascript": iconId = R.drawable.skill_javascript; break;
            case "ruby": iconId = R.drawable.skill_ruby; break;
            case "python": iconId = R.drawable.skill_python; break;
            case "ror": iconId = R.drawable.skill_ror; break;
            case "codeigniter": iconId = R.drawable.skill_codeigniter; break;
            case "sinatra": iconId = R.drawable.skill_sinatra; break;
            case "sass": iconId = R.drawable.skill_sass; break;
            case "aws": iconId = R.drawable.skill_aws; break;
            case "gae": iconId = R.drawable.skill_gae; break;
            case "git": iconId = R.drawable.skill_git; break;
            case "gulp": iconId = R.drawable.skill_gulp; break;
            case "intellij": iconId = R.drawable.skill_intellij; break;
            case "photoshop": iconId = R.drawable.skill_photoshop; break;
            case "sysadmin": iconId = R.drawable.skill_sysadmin; break;
            case "ai": iconId = R.drawable.skill_ia; break;
            case "fr": iconId = R.drawable.flag_france; break;
            case "en": iconId = R.drawable.flag_uk; break;
            case "jp": iconId = R.drawable.flag_japan; break;
        }

        return iconId;

    }
}
