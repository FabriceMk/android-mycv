package net.fabricemk.android.mycv.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.Skill;
import net.fabricemk.android.mycv.tools.resources.SkillMapper;

/**
 * The activity shows the details of a specific skill
 *
 * For now the Lollipop transitions are deactivated
 */
public class SkillDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_TITLE = "net.fabricemk.android.mycv.title";
    private static final String EXTRA_DESCRIPTION = "net.fabricemk.android.mycv.description";
    private static final String EXTRA_ICON = "net.fabricemk.android.mycv.icon";

    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    public static void navigate(AppCompatActivity activity, View transitionImage, Skill skill) {
        Intent intent = new Intent(activity, SkillDetailsActivity.class);
        intent.putExtra(EXTRA_TITLE, skill.getName());
        intent.putExtra(EXTRA_DESCRIPTION, skill.getDescription());
        intent.putExtra(EXTRA_ICON, skill.getIcon());

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, transitionImage, EXTRA_ICON);
//        ActivityCompat.startActivity(activity, intent, options.toBundle());
        ActivityCompat.startActivity(activity, intent, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        initActivityTransitions();

        setContentView(R.layout.skill_details);

//        ViewCompat.setTransitionName(findViewById(R.id.icon), EXTRA_ICON);
//        supportPostponeEnterTransition();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle(itemTitle);
        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        ImageView iconView = (ImageView) findViewById(R.id.icon);
        TextView titleView = (TextView) findViewById(R.id.title);
        TextView descriptionView = (TextView) findViewById(R.id.description);

        int resourceId = SkillMapper.mappingIconIdFromName(getIntent().getStringExtra(EXTRA_ICON));

        Glide.with(this)
                .load(resourceId)
                .centerCrop()
                .crossFade()
                .into(iconView);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        titleView.setText(title);

        String description = getIntent().getStringExtra(EXTRA_DESCRIPTION);
        descriptionView.setText(description);
    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        mCollapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        mCollapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        //supportStartPostponedEnterTransition();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
