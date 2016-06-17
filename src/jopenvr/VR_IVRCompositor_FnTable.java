package jopenvr;
import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.util.Arrays;
import java.util.List;
/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class VR_IVRCompositor_FnTable extends Structure {
	/** C type : SetTrackingSpace_callback* */
	public VR_IVRCompositor_FnTable.SetTrackingSpace_callback SetTrackingSpace;
	/** C type : GetTrackingSpace_callback* */
	public VR_IVRCompositor_FnTable.GetTrackingSpace_callback GetTrackingSpace;
	/** C type : WaitGetPoses_callback* */
	public VR_IVRCompositor_FnTable.WaitGetPoses_callback WaitGetPoses;
	/** C type : GetLastPoses_callback* */
	public VR_IVRCompositor_FnTable.GetLastPoses_callback GetLastPoses;
	/** C type : GetLastPoseForTrackedDeviceIndex_callback* */
	public VR_IVRCompositor_FnTable.GetLastPoseForTrackedDeviceIndex_callback GetLastPoseForTrackedDeviceIndex;
	/** C type : Submit_callback* */
	public VR_IVRCompositor_FnTable.Submit_callback Submit;
	/** C type : ClearLastSubmittedFrame_callback* */
	public VR_IVRCompositor_FnTable.ClearLastSubmittedFrame_callback ClearLastSubmittedFrame;
	/** C type : PostPresentHandoff_callback* */
	public VR_IVRCompositor_FnTable.PostPresentHandoff_callback PostPresentHandoff;
	/** C type : GetFrameTiming_callback* */
	public VR_IVRCompositor_FnTable.GetFrameTiming_callback GetFrameTiming;
	/** C type : GetFrameTimeRemaining_callback* */
	public VR_IVRCompositor_FnTable.GetFrameTimeRemaining_callback GetFrameTimeRemaining;
	/** C type : FadeToColor_callback* */
	public VR_IVRCompositor_FnTable.GetCumulativeStats_callback GetCumulativeStats;
	public VR_IVRCompositor_FnTable.FadeToColor_callback FadeToColor;
	/** C type : FadeGrid_callback* */
	public VR_IVRCompositor_FnTable.FadeGrid_callback FadeGrid;
	/** C type : SetSkyboxOverride_callback* */
	public VR_IVRCompositor_FnTable.SetSkyboxOverride_callback SetSkyboxOverride;
	/** C type : ClearSkyboxOverride_callback* */
	public VR_IVRCompositor_FnTable.ClearSkyboxOverride_callback ClearSkyboxOverride;
	/** C type : CompositorBringToFront_callback* */
	public VR_IVRCompositor_FnTable.CompositorBringToFront_callback CompositorBringToFront;
	/** C type : CompositorGoToBack_callback* */
	public VR_IVRCompositor_FnTable.CompositorGoToBack_callback CompositorGoToBack;
	/** C type : CompositorQuit_callback* */
	public VR_IVRCompositor_FnTable.CompositorQuit_callback CompositorQuit;
	/** C type : IsFullscreen_callback* */
	public VR_IVRCompositor_FnTable.IsFullscreen_callback IsFullscreen;
	/** C type : GetCurrentSceneFocusProcess_callback* */
	public VR_IVRCompositor_FnTable.GetCurrentSceneFocusProcess_callback GetCurrentSceneFocusProcess;
	/** C type : GetLastFrameRenderer_callback* */
	public VR_IVRCompositor_FnTable.GetLastFrameRenderer_callback GetLastFrameRenderer;
	/** C type : CanRenderScene_callback* */
	public VR_IVRCompositor_FnTable.CanRenderScene_callback CanRenderScene;
	/** C type : ShowMirrorWindow_callback* */
	public VR_IVRCompositor_FnTable.ShowMirrorWindow_callback ShowMirrorWindow;
	/** C type : HideMirrorWindow_callback* */
	public VR_IVRCompositor_FnTable.HideMirrorWindow_callback HideMirrorWindow;
	/** C type : IsMirrorWindowVisible_callback* */
	public VR_IVRCompositor_FnTable.IsMirrorWindowVisible_callback IsMirrorWindowVisible;
	/** C type : CompositorDumpImages_callback* */
	public VR_IVRCompositor_FnTable.CompositorDumpImages_callback CompositorDumpImages;
	/** C type : ShouldAppRenderWithLowResources_callback* */
	public VR_IVRCompositor_FnTable.ShouldAppRenderWithLowResources_callback ShouldAppRenderWithLowResources;
	/** C type : ForceInterleavedReprojectionOn_callback* */
	public VR_IVRCompositor_FnTable.ForceInterleavedReprojectionOn_callback ForceInterleavedReprojectionOn;
	public VR_IVRCompositor_FnTable.ForceReconnectProcess_callback ForceReconnectProcess;
	/** C type : SuspendRendering_callback* */
	public VR_IVRCompositor_FnTable.SuspendRendering_callback SuspendRendering;
	/** C type : RequestScreenshot_callback* */
	public jopenvr.VR_IVRScreenshots_FnTable.RequestScreenshot_callback RequestScreenshot;
	/** C type : GetCurrentScreenshotType_callback* */
	public VR_IVRCompositor_FnTable.GetCurrentScreenshotType_callback GetCurrentScreenshotType;
	/** C type : GetMirrorTextureD3D11_callback* */
	public VR_IVRCompositor_FnTable.GetMirrorTextureD3D11_callback GetMirrorTextureD3D11;
	/** C type : GetMirrorTextureGL_callback* */
	public VR_IVRCompositor_FnTable.GetMirrorTextureGL_callback GetMirrorTextureGL;
	/** C type : ReleaseSharedGLTexture_callback* */
	public VR_IVRCompositor_FnTable.ReleaseSharedGLTexture_callback ReleaseSharedGLTexture;
	/** C type : LockGLSharedTextureForAccess_callback* */
	public VR_IVRCompositor_FnTable.LockGLSharedTextureForAccess_callback LockGLSharedTextureForAccess;
	/** C type : UnlockGLSharedTextureForAccess_callback* */
	public VR_IVRCompositor_FnTable.UnlockGLSharedTextureForAccess_callback UnlockGLSharedTextureForAccess;
	public interface SetTrackingSpace_callback extends Callback {
		void apply(int eOrigin);
	};
	public interface GetTrackingSpace_callback extends Callback {
		int apply();
	};
	public interface WaitGetPoses_callback extends Callback {
		int apply(TrackedDevicePose_t pRenderPoseArray, int unRenderPoseArrayCount, TrackedDevicePose_t pGamePoseArray, int unGamePoseArrayCount);
	};
	public interface GetLastPoses_callback extends Callback {
		int apply(TrackedDevicePose_t pRenderPoseArray, int unRenderPoseArrayCount, TrackedDevicePose_t pGamePoseArray, int unGamePoseArrayCount);
	};
	public interface GetLastPoseForTrackedDeviceIndex_callback extends Callback {
		int apply(int unDeviceIndex, TrackedDevicePose_t pOutputPose, TrackedDevicePose_t pOutputGamePose);
	};
	public interface Submit_callback extends Callback {
		int apply(int eEye, Texture_t pTexture, VRTextureBounds_t pBounds, int nSubmitFlags);
	};
	public interface ClearLastSubmittedFrame_callback extends Callback {
		void apply();
	};
	public interface PostPresentHandoff_callback extends Callback {
		void apply();
	};
	public interface GetFrameTiming_callback extends Callback {
		byte apply(Compositor_FrameTiming pTiming, int unFramesAgo);
	};
	public interface GetFrameTimeRemaining_callback extends Callback {
		float apply();
	};
	public interface GetCumulativeStats_callback extends Callback {
		void apply(Compositor_CumulativeStats pStats, int nStatsSizeInBytes);
	};
	public interface FadeToColor_callback extends Callback {
		void apply(float fSeconds, float fRed, float fGreen, float fBlue, float fAlpha, byte bBackground);
	};
	public interface FadeGrid_callback extends Callback {
		void apply(float fSeconds, byte bFadeIn);
	};
	public interface SetSkyboxOverride_callback extends Callback {
		int apply(Texture_t pTextures, int unTextureCount);
	};
	public interface ClearSkyboxOverride_callback extends Callback {
		void apply();
	};
	public interface CompositorBringToFront_callback extends Callback {
		void apply();
	};
	public interface CompositorGoToBack_callback extends Callback {
		void apply();
	};
	public interface CompositorQuit_callback extends Callback {
		void apply();
	};
	public interface IsFullscreen_callback extends Callback {
		byte apply();
	};
	public interface GetCurrentSceneFocusProcess_callback extends Callback {
		int apply();
	};
	public interface GetLastFrameRenderer_callback extends Callback {
		int apply();
	};
	public interface CanRenderScene_callback extends Callback {
		byte apply();
	};
	public interface ShowMirrorWindow_callback extends Callback {
		void apply();
	};
	public interface HideMirrorWindow_callback extends Callback {
		void apply();
	};
	public interface IsMirrorWindowVisible_callback extends Callback {
		byte apply();
	};
	public interface CompositorDumpImages_callback extends Callback {
		void apply();
	};
	public interface ShouldAppRenderWithLowResources_callback extends Callback {
		byte apply();
	};
	public interface ForceInterleavedReprojectionOn_callback extends Callback {
		void apply(byte bOverride);
	};
	public interface ForceReconnectProcess_callback extends Callback {
		void apply();
	};
	public interface SuspendRendering_callback extends Callback {
		void apply(byte bSuspend);
	};
	public interface RequestScreenshot_callback extends Callback {
		int apply(int type, Pointer pchDestinationFileName, Pointer pchVRDestinationFileName);
	};
	public interface GetCurrentScreenshotType_callback extends Callback {
		int apply();
	};
	public interface GetMirrorTextureD3D11_callback extends Callback {
		int apply(int eEye, Pointer pD3D11DeviceOrResource, PointerByReference ppD3D11ShaderResourceView);
	};
	public interface GetMirrorTextureGL_callback extends Callback {
		int apply(int eEye, IntByReference pglTextureId, PointerByReference pglSharedTextureHandle);
	};
	public interface ReleaseSharedGLTexture_callback extends Callback {
		byte apply(int glTextureId, Pointer glSharedTextureHandle);
	};
	public interface LockGLSharedTextureForAccess_callback extends Callback {
		void apply(Pointer glSharedTextureHandle);
	};
	public interface UnlockGLSharedTextureForAccess_callback extends Callback {
		void apply(Pointer glSharedTextureHandle);
	};
	public VR_IVRCompositor_FnTable() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("SetTrackingSpace", "GetTrackingSpace", "WaitGetPoses", "GetLastPoses", "GetLastPoseForTrackedDeviceIndex", "Submit", "ClearLastSubmittedFrame", "PostPresentHandoff", "GetFrameTiming", "GetFrameTimeRemaining", "GetCumulativeStats", "FadeToColor", "FadeGrid", "SetSkyboxOverride", "ClearSkyboxOverride", "CompositorBringToFront", "CompositorGoToBack", "CompositorQuit", "IsFullscreen", "GetCurrentSceneFocusProcess", "GetLastFrameRenderer", "CanRenderScene", "ShowMirrorWindow", "HideMirrorWindow", "IsMirrorWindowVisible", "CompositorDumpImages", "ShouldAppRenderWithLowResources", "ForceInterleavedReprojectionOn", "ForceReconnectProcess", "SuspendRendering", "RequestScreenshot", "GetCurrentScreenshotType", "GetMirrorTextureD3D11", "GetMirrorTextureGL", "ReleaseSharedGLTexture", "LockGLSharedTextureForAccess", "UnlockGLSharedTextureForAccess");
	}
	public VR_IVRCompositor_FnTable(Pointer peer) {
		super(peer);
	}
	public static class ByReference extends VR_IVRCompositor_FnTable implements Structure.ByReference {
		
	};
	public static class ByValue extends VR_IVRCompositor_FnTable implements Structure.ByValue {
		
	};
}
