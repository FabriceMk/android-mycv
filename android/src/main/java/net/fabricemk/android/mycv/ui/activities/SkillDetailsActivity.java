package net.fabricemk.android.mycv.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.Skill;
import net.fabricemk.android.mycv.tools.resources.SkillMapper;

/**
 * The activity shows the details of a specific skill
 */
public class SkillDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_TITLE = "net.fabricemk.android.mycv.title";
    private static final String EXTRA_DESCRIPTION = "net.fabricemk.android.mycv.description";
    private static final String EXTRA_ICON = "net.fabricemk.android.mycv.icon";

    // View name of the header image. Used for activity scene transitions
    public static final String VIEW_NAME_HEADER_IMAGE = "detail:header:image";

    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    public static void navigate(AppCompatActivity activity, View transitionImage, Skill skill) {
        Intent intent = new Intent(activity, SkillDetailsActivity.class);
        intent.putExtra(EXTRA_TITLE, skill.getName());
        intent.putExtra(EXTRA_DESCRIPTION, skill.getDescription());
        intent.putExtra(EXTRA_ICON, skill.getIcon());

        // Prepare the views for activity transitions
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, new Pair<View, String>(transitionImage, VIEW_NAME_HEADER_IMAGE));
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.skill_details);

        // Setup Toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle(itemTitle);
        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        ImageView iconView = (ImageView) findViewById(R.id.icon);
        TextView titleView = (TextView) findViewById(R.id.title);
        TextView descriptionView = (TextView) findViewById(R.id.description);

        // Init all activity transitions processing
        initActivityTransitions(iconView);

        int resourceId = SkillMapper.mappingIconIdFromName(getIntent().getStringExtra(EXTRA_ICON));

        Glide.with(this)
                .load(resourceId)
                .asBitmap()
                .listener(new RequestListener<Object, Bitmap>() {

                    @Override
                    public boolean onException(Exception e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        supportStartPostponedEnterTransition();
                        return false;
                    }
                })
                .dontTransform()
                .into(iconView);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        titleView.setText(title);

        String description = getIntent().getStringExtra(EXTRA_DESCRIPTION);
        descriptionView.setText(description);
    }

    /**
     * Apply activity transitions for API > Lollipop
     * @param iconView
     */
    private void initActivityTransitions(View iconView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition fade = new Fade();
            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(android.R.id.navigationBarBackground, true);
            getWindow().setExitTransition(fade);
            getWindow().setEnterTransition(fade);
            // Delay the transition
            supportPostponeEnterTransition();
            ViewCompat.setTransitionName(iconView, VIEW_NAME_HEADER_IMAGE);
        }
    }

    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        mCollapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        mCollapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
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
