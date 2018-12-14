package com.lingqiapp.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilderSupplier;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lingqiapp.R;
import com.lingqiapp.Utils.UrlUtils;

/**
 * com.lingqiapp.View
 *
 * @author 赵磊
 * @date 2018/12/14
 * 功能描述：
 */
public class MYSimpleDraweeView extends SimpleDraweeView {

    private static Supplier<? extends SimpleDraweeControllerBuilder> sDraweeControllerBuilderSupplier;

    /**
     * Initializes {@link MYSimpleDraweeView} with supplier of Drawee controller builders.
     */
    public static void initialize(
            Supplier<? extends SimpleDraweeControllerBuilder> draweeControllerBuilderSupplier) {
        sDraweeControllerBuilderSupplier = draweeControllerBuilderSupplier;
    }

    /**
     * Shuts {@link MYSimpleDraweeView} down.
     */
    public static void shutDown() {
        sDraweeControllerBuilderSupplier = null;
    }

    private SimpleDraweeControllerBuilder mSimpleDraweeControllerBuilder;

    public MYSimpleDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        init(context, null);
    }

    public MYSimpleDraweeView(Context context) {
        super(context);
        init(context, null);
    }

    public MYSimpleDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MYSimpleDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MYSimpleDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }

        sDraweeControllerBuilderSupplier =
                new PipelineDraweeControllerBuilderSupplier(context, null);
        MYSimpleDraweeView.initialize(sDraweeControllerBuilderSupplier);

        Preconditions.checkNotNull(
                sDraweeControllerBuilderSupplier,
                "SimpleDraweeView was not initialized!");
        mSimpleDraweeControllerBuilder = sDraweeControllerBuilderSupplier.get();

        if (attrs != null) {
            TypedArray gdhAttrs = context.obtainStyledAttributes(
                    attrs,
                    R.styleable.SimpleDraweeView);
            try {
                if (gdhAttrs.hasValue(R.styleable.SimpleDraweeView_actualImageUri)) {
                    setImageURI(
                            Uri.parse(gdhAttrs.getString(R.styleable.SimpleDraweeView_actualImageUri)),
                            null);
                } else if (gdhAttrs.hasValue((R.styleable.SimpleDraweeView_actualImageResource))) {
                    int resId = gdhAttrs.getResourceId(
                            R.styleable.SimpleDraweeView_actualImageResource,
                            NO_ID);
                    if (resId != NO_ID) {
                        setActualImageResource(resId);
                    }
                }
            } finally {
                gdhAttrs.recycle();
            }
        }
    }

    @Override
    protected SimpleDraweeControllerBuilder getControllerBuilder() {
        return mSimpleDraweeControllerBuilder;
    }

    /**
     * Displays an image given by the uri.
     *
     * @param uri uri of the image
     * @undeprecate
     */
    @Override
    public void setImageURI(Uri uri) {
        setImageURI(uri, null);
    }

    /**
     * Displays an image given by the uri string.
     *
     * @param uriString uri string of the image
     */
    @Override
    public void setImageURI(@Nullable String uriString) {

        if (uriString.contains("/Public/uploads/")) {
            setImageURI(UrlUtils.URL + uriString, null);
        }

        if (uriString.contains(".com")) {
            setImageURI("https:" + uriString, null);
        } else if (uriString.contains(".cn")) {
            setImageURI("https:" + uriString, null);
        } else if (uriString.contains("https://")) {
            setImageURI("" + uriString, null);
        }

        Log.e("setImageURI", uriString);

    }

    /**
     * Displays an image given by the uri.
     *
     * @param uri           uri of the image
     * @param callerContext caller context
     */
    @Override
    public void setImageURI(Uri uri, @Nullable Object callerContext) {
        DraweeController controller = mSimpleDraweeControllerBuilder
                .setCallerContext(callerContext)
                .setUri(uri)
                .setOldController(getController())
                .build();
        setController(controller);
    }

    /**
     * Displays an image given by the uri string.
     *
     * @param uriString     uri string of the image
     * @param callerContext caller context
     */
    @Override
    public void setImageURI(@Nullable String uriString, @Nullable Object callerContext) {
        Uri uri = (uriString != null) ? Uri.parse(uriString) : null;
        setImageURI(uri, callerContext);
    }

    /**
     * Sets the actual image resource to the given resource ID.
     * <p>
     * Similar to {@link #setImageResource(int)}, this sets the displayed image to the given resource.
     * However, {@link #setImageResource(int)} bypasses all Drawee functionality and makes the view
     * act as a normal {@link android.widget.ImageView}, whereas this method keeps all of the
     * Drawee functionality, including the {@link com.facebook.drawee.interfaces.DraweeHierarchy}.
     *
     * @param resourceId the resource ID to use.
     */
    @Override
    public void setActualImageResource(@DrawableRes int resourceId) {
        setActualImageResource(resourceId, null);
    }

    /**
     * Sets the actual image resource to the given resource ID.
     * <p>
     * Similar to {@link #setImageResource(int)}, this sets the displayed image to the given resource.
     * However, {@link #setImageResource(int)} bypasses all Drawee functionality and makes the view
     * act as a normal {@link android.widget.ImageView}, whereas this method keeps all of the
     * Drawee functionality, including the {@link com.facebook.drawee.interfaces.DraweeHierarchy}.
     *
     * @param resourceId    the resource ID to use.
     * @param callerContext caller context
     */
    @Override
    public void setActualImageResource(@DrawableRes int resourceId, @Nullable Object callerContext) {
        setImageURI(UriUtil.getUriForResourceId(resourceId), callerContext);
    }

    /**
     * This method will bypass all Drawee-related functionality.
     * If you want to keep this functionality, take a look at {@link #setActualImageResource(int)}
     * and {@link #setActualImageResource(int, Object)}}.
     *
     * @param resId the resource ID
     */
    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
    }

}
