/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.input;

import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import jmevr.util.OpenVRUtil;
import openvr_api.HmdMatrix34_t;
import openvr_api.HmdMatrix44_t;
import openvr_api.IOpenvr_api;
import openvr_api.IVRCompositor;
import openvr_api.IVRSystem;
import openvr_api.Openvr_apiLibrary;
import openvr_api.TrackedDevicePose_t;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.Pointer.StringType;

/**
 *
 * @author phr00t
 */
public class OpenVR implements VRHMD {

    private static final Openvr_apiLibrary openvr = new Openvr_apiLibrary();
    private static IVRSystem vrsystem; //p_HMD
    private static IVRCompositor vrCompositor;
    private static boolean forceInitialize = false;
    
    private static Pointer hmdDeviceIndex;
    private static Pointer hmdDisplayFrequency;
    private static Pointer hmdTrackedDevicePose;
    
    private static Pointer hmdErrorStore;
    
    private static final Quaternion rotStore = new Quaternion();
    private static final Vector3f posStore = new Vector3f();
    
    private final HMDInfo hmdinfo = new HMDInfo();
    private Matrix4f[] poseMatrices;
    private char[] devClassChar;
    private final Matrix4f hmdPose = Matrix4f.IDENTITY.clone();
    private Matrix4f hmdProjectionLeftEye;
    private Matrix4f hmdProjectionRightEye;
    private Matrix4f hmdPoseLeftEye;
    private Matrix4f hmdPoseRightEye;
    
    @Override
    public String getName() {
        return "OpenVR";
    }

    @Override
    public boolean initialize() {
        hmdErrorStore = Pointer.allocateLong();
        Pointer pvr = openvr.vRInit(hmdErrorStore);
        if( pvr != null ) vrsystem = (IVRSystem)pvr.get();
        if( hmdErrorStore.getLong() != 0 ) {
            String errstr = openvr.vRGetStringForHmdError(hmdErrorStore);
            System.out.println("OpenVR Initialize Result: " + errstr);
            return false;
        } else {
            System.out.println("OpenVR initialized & VR connected.");
            hmdDeviceIndex = Pointer.allocateInt();
            hmdDeviceIndex.setInt(Openvr_apiLibrary.k_unTrackedDeviceIndex_Hmd);
            
            // this was taken straight from C
            // static const char * const IVRCompositor_Version = "IVRCompositor_005";
            String ivr_string = "IVRCompositor_005";
            Pointer ivr_comp_version = Pointer.allocateChars(ivr_string.length() + 1); // add one for null termination
            ivr_comp_version.setString(ivr_string, StringType.C);
            
            // clear error store
            hmdErrorStore.setLong(0);
            
            Pointer compositor = openvr.vRGetGenericInterface(ivr_comp_version, hmdErrorStore);
            if(compositor != null && hmdErrorStore.getLong() != 0){                
                vrCompositor = (IVRCompositor) compositor.get();
                hmdDisplayFrequency = Pointer.allocateInt();
                hmdDisplayFrequency.setInt( (int) Openvr_apiLibrary.TrackedDeviceProperty.Prop_DisplayFrequency_Float.value);
                hmdDisplayFrequency = Pointer.allocateInt();
                hmdDisplayFrequency.setInt( (int) Openvr_apiLibrary.TrackedDeviceProperty.Prop_SecondsFromVsyncToPhotons_Float.value);
                hmdTrackedDevicePose = Pointer.allocateInts(Openvr_apiLibrary.k_unMaxTrackedDeviceCount);
                return true;
            } else {
                System.out.println("OpenVR Compositor error: " + openvr.vRGetStringForHmdError(hmdErrorStore));
                return false;
            }
            
        }
    }

    @Override
    public void forceInitializeSuccess() {
        forceInitialize = true;
    }

    @Override
    public void initRendering(int width, int height, int samples) {
        // TODO: this
    }

    @Override
    public HMDInfo updateHMDInfo() {
         // i think this is the only value used...
        hmdinfo.InterpupillaryDistance = getInterpupillaryDistance();
        return hmdinfo;
    }

    @Override
    public HMDInfo getHMDInfo() {
        return hmdinfo;
    }

    @Override
    public void destroy() {
        openvr.vRShutdown();
    }

    @Override
    public boolean isInitialized() {
        return forceInitialize || vrsystem != null && openvr.vRIsHmdPresent();
    }

    @Override
    public void reset() {
        if( vrsystem == null ) return;
        vrsystem.resetSeatedZeroPose();
    }

    public void getRenderSize(Vector2f store) {
        if( vrsystem == null ) {
            store.x = 1280f;
            store.y = 800f;
        } else {
            Pointer x = Pointer.allocateInt();
            Pointer y = Pointer.allocateInt();
            vrsystem.getRecommendedRenderTargetSize(x, y);
            store.x = x.getInt();
            store.y = y.getInt();
        }
    }
    
    @Override
    public int getHResolution() {        
        return 1280; // i don't think this is used/needed... (shader perhaps)
    }

    @Override
    public float getFOV() {
        if( vrsystem == null ) return 130f;
        return vrsystem.getFloatTrackedDeviceProperty(hmdDeviceIndex, Openvr_apiLibrary.TrackedDeviceProperty.Prop_FieldOfViewBottomDegrees_Float, hmdErrorStore);
    }

    @Override
    public int getVResolution() {
        return 800; // i don't think this is used/needed... (shader perhaps)
    }

    @Override
    public float getInterpupillaryDistance() {
        if( vrsystem == null ) return 0.064f;
        return vrsystem.getFloatTrackedDeviceProperty(hmdDeviceIndex, Openvr_apiLibrary.TrackedDeviceProperty.Prop_UserIpdMeters_Float, hmdErrorStore);
    }

    @Override
    public float getEyeHeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Quaternion getOrientation() {
        hmdPose.toRotationQuat(rotStore);
        return rotStore;
    }

    @Override
    public Vector3f getPosition() {
        hmdPose.toTranslationVector(posStore);
        return posStore;
    }

    @Override
    public Vector3f getAngularAcceleration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector3f getPositionalAcceleration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector3f getAccelerometer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void updatePose(){
        if(vrsystem == null){
            return;
        }
        int unMaxTrackedDeviceCount = Openvr_apiLibrary.k_unMaxTrackedDeviceCount;
        if(vrCompositor != null){
            vrCompositor.waitGetPoses(hmdTrackedDevicePose, unMaxTrackedDeviceCount);
        } else {
            // We just got done with the glFinish - the seconds since last vsync should be 0.
            float fSecondsSinceLastVsync = 0.0f;
                
            float fFrameDuration = 1.0f / vrsystem.getFloatTrackedDeviceProperty( hmdDeviceIndex, (IntValuedEnum<IOpenvr_api.TrackedDeviceProperty>) hmdDisplayFrequency, hmdErrorStore);
                
            float fSecondsUntilPhotons = fFrameDuration - fSecondsSinceLastVsync + vrsystem.getFloatTrackedDeviceProperty(hmdDeviceIndex, (IntValuedEnum<IOpenvr_api.TrackedDeviceProperty>) hmdDisplayFrequency, hmdErrorStore);
            vrsystem.getDeviceToAbsoluteTrackingPose(Openvr_apiLibrary.TrackingUniverseOrigin.TrackingUniverseSeated, fSecondsUntilPhotons, hmdTrackedDevicePose, unMaxTrackedDeviceCount);
        }
        //int validPoseCount = 0; //not currently used... commenting for now
        //String poseClasses = "";
        for (int nDevice = 0; nDevice < unMaxTrackedDeviceCount; ++nDevice ){
            if(((TrackedDevicePose_t)hmdTrackedDevicePose.get(nDevice)).bPoseIsValid()){
                //validPoseCount++;
                OpenVRUtil.convertSteamVRMatrix3ToMatrix4f(((TrackedDevicePose_t)hmdTrackedDevicePose.get(nDevice)).mDeviceToAbsoluteTracking(), poseMatrices[nDevice]);
                if(devClassChar[nDevice] == 0){
                    IntValuedEnum<IOpenvr_api.TrackedDeviceClass > trackedDeviceClass = vrsystem.getTrackedDeviceClass((Pointer<IOpenvr_api.TrackedDeviceIndex_t>) hmdDeviceIndex.get(nDevice));
                    if          ((int)trackedDeviceClass.value() == Openvr_apiLibrary.TrackedDeviceClass.TrackedDeviceClass_Controller.value){
                        devClassChar[nDevice] = 'C';
                    } else if   ((int)trackedDeviceClass.value() == Openvr_apiLibrary.TrackedDeviceClass.TrackedDeviceClass_HMD.value){
                        devClassChar[nDevice] = 'H';
                    } else if   ((int)trackedDeviceClass.value() == Openvr_apiLibrary.TrackedDeviceClass.TrackedDeviceClass_Invalid.value){
                        devClassChar[nDevice] = 'I';
                    } else if   ((int)trackedDeviceClass.value() == Openvr_apiLibrary.TrackedDeviceClass.TrackedDeviceClass_Other.value){
                        devClassChar[nDevice] = 'O';
                    } else if   ((int)trackedDeviceClass.value() == Openvr_apiLibrary.TrackedDeviceClass.TrackedDeviceClass_TrackingReference.value){
                        devClassChar[nDevice] = 'T';
                    } else {
                        devClassChar[nDevice] = '?';
                    }
                }
                //poseClasses += devClassChar[nDevice];
            }
        }
        if (((TrackedDevicePose_t)hmdTrackedDevicePose.get(hmdDeviceIndex.getInt())).bPoseIsValid()){
            poseMatrices[hmdDeviceIndex.getInt()].invert(hmdPose);
        }
    }

    @Override
    public Matrix4f getPositionAndOrientation() {
        return hmdPose;
    }

    @Override
    public Matrix4f getEyeTransform(int eye) {
        return null;
    }
    
    public Matrix4f getHMDMatrixProjectionEye(int eye, Camera cam){
        if(vrsystem == null){
            return new Matrix4f();
        }
        HmdMatrix44_t mat = vrsystem.getProjectionMatrix(eye == 0 ? IOpenvr_api.Hmd_Eye.Eye_Left : IOpenvr_api.Hmd_Eye.Eye_Left, cam.getFrustumNear(), cam.getFrustumFar(), IOpenvr_api.GraphicsAPIConvention.API_OpenGL);
        return OpenVRUtil.convertSteamVRMatrix4ToMatrix4f(mat, eye == 0 ? hmdProjectionLeftEye : hmdProjectionRightEye);
    }
        
    public Matrix4f getHMDMatrixPoseEye(int eye){
        if(vrsystem == null){
            return new Matrix4f();
        }
        HmdMatrix34_t mat = vrsystem.getEyeToHeadTransform(eye == 0 ? IOpenvr_api.Hmd_Eye.Eye_Left : IOpenvr_api.Hmd_Eye.Eye_Left);
        return OpenVRUtil.convertSteamVRMatrix3ToMatrix4f(mat, eye == 0 ? hmdPoseLeftEye : hmdPoseRightEye);
    }

    
////-----------------------------------------------------------------------------
//// Purpose:
////-----------------------------------------------------------------------------
//Matrix4 CMainApplication::GetCurrentViewProjectionMatrix( vr::Hmd_Eye nEye )
//{
//	Matrix4 matMVP;
//	if( nEye == vr::Eye_Left )
//	{
//		matMVP = m_mat4ProjectionLeft * m_mat4eyePosLeft * m_mat4HMDPose;
//	}
//	else if( nEye == vr::Eye_Right )
//	{
//		matMVP = m_mat4ProjectionRight * m_mat4eyePosRight *  m_mat4HMDPose;
//	}
//
//	return matMVP;
//}
}
