// Generated by view binder compiler. Do not edit!
package com.policyboss.policybosscaller.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.policyboss.policybosscaller.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityOverlayPopupPermissionBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final LinearLayout lyBackground;

  @NonNull
  public final LinearLayout lyOverlay;

  @NonNull
  public final RelativeLayout lyParent;

  private ActivityOverlayPopupPermissionBinding(@NonNull RelativeLayout rootView,
      @NonNull LinearLayout lyBackground, @NonNull LinearLayout lyOverlay,
      @NonNull RelativeLayout lyParent) {
    this.rootView = rootView;
    this.lyBackground = lyBackground;
    this.lyOverlay = lyOverlay;
    this.lyParent = lyParent;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityOverlayPopupPermissionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityOverlayPopupPermissionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_overlay_popup_permission, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityOverlayPopupPermissionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.lyBackground;
      LinearLayout lyBackground = ViewBindings.findChildViewById(rootView, id);
      if (lyBackground == null) {
        break missingId;
      }

      id = R.id.lyOverlay;
      LinearLayout lyOverlay = ViewBindings.findChildViewById(rootView, id);
      if (lyOverlay == null) {
        break missingId;
      }

      RelativeLayout lyParent = (RelativeLayout) rootView;

      return new ActivityOverlayPopupPermissionBinding((RelativeLayout) rootView, lyBackground,
          lyOverlay, lyParent);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}