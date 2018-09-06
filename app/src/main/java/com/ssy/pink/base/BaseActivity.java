package com.ssy.pink.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ssy.pink.MyApplication;
import com.ssy.pink.R;
import com.ssy.pink.activity.MyIdolActivity;
import com.ssy.pink.activity.SplashActivity;
import com.ssy.pink.bean.exception.ClientException;
import com.ssy.pink.bean.exception.ExceptionResponse;
import com.ssy.pink.common.ConstantErrorCode;
import com.ssy.pink.mvp.iview.IView;
import com.ssy.pink.receiver.NetChangeReceiver;
import com.ssy.pink.utils.LogUtil;
import com.ssy.pink.utils.StatusBarUtil;
import com.ssy.pink.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.SocketTimeoutException;

/**
 * 添加异常处理的Activity超类
 */
public class BaseActivity extends PermissionActivity implements IView {

    private static final String TAG = "BaseActivity";
    private static final String NO_SUCH_SOURCE = "NoSuchSource_The source does not exist.";
    private static final String NO_SUCH_LINK = "NoSuchLink_This Link does not exist.";
    private static final String SAME_FOLDER_CONFLICT = "SameNodeConflict_The dest folder is same as the src folder.";
    private static final String SUB_FOLDER_CONFLICT = "SubFolderConflict_The dest folder is sub folder for the src folder.";
    private NetChangeReceiver netChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this instanceof SplashActivity) {
        } else if (this instanceof MyIdolActivity) {

        } else {
            StatusBarUtil.setColor(this);
        }
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerNetChangeReceiver();
    }

    protected void registerNetChangeReceiver() {
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        netChangeReceiver = new NetChangeReceiver();
        registerReceiver(netChangeReceiver, iFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterNetChangeReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    protected void unregisterNetChangeReceiver() {
        unregisterReceiver(netChangeReceiver);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(ExceptionResponse event) {
        String errorCode = event.getErrorCode();
        String message = event.getMessage();
        int statusCode = event.getStatusCode();
        if (ConstantErrorCode.CertPathValidatorException.equals(message)) {
            onException_CertError();
            return;
        } else if (ConstantErrorCode.ConnectException.equals(message)) {
            onException_Connect();
            return;
        }
        switch (statusCode) {
            case ConstantErrorCode.ERROR_CODE_400_PARAMS: {
                switch (errorCode) {
                    case ConstantErrorCode.InvalidParameter:
                        onException_400_InvalidParameter(message, errorCode);
                        break;
                    case ConstantErrorCode.InvalidPart:
                        onException_400_InvalidPart(message, errorCode);
                        break;
                    case ConstantErrorCode.InvalidRange:
                        onException_400_InvalidRange(message, errorCode);
                        break;
                    case ConstantErrorCode.InvalidTeamRole:
                        onException_400_InvalidTeamRole(message, errorCode);
                        break;
                    case ConstantErrorCode.InvalidRegion:
                        onException_400_InvalidRegion(message, errorCode);
                        break;
                    case ConstantErrorCode.InvalidPermissionRole:
                        onException_400_InvalidPermissionRole(message, errorCode);
                        break;
                    case ConstantErrorCode.InvalidFileType:
                        onException_400_InvalidFileType(message, errorCode);
                        break;
                    case ConstantErrorCode.UnmatchedDownloadUrl:
                        onException_400_UnmatchedDownloadUrl(message, errorCode);
                        break;
                    case ConstantErrorCode.BAD_REQUEST:
                        onExceptionBadRequest(message, errorCode);
                        break;
                    default:
                        onException_undefined(message, errorCode);
                        break;
                }
            }
            break;
            case ConstantErrorCode.ERROR_CODE_401_UNAUTHORIZED: {
                switch (errorCode) {
                    case ConstantErrorCode.Unauthorized:
                        onException_401_Unauthorized(message, errorCode);
                        break;
                    case ConstantErrorCode.ClientUnauthorized:
                        onException_401_ClientUnauthorized(message, errorCode);
                        break;
                    default:
                        onException_undefined(message, errorCode);
                        break;
                }
            }
            break;
            case ConstantErrorCode.ERROR_CODE_403_NO_RIGHT: {
                switch (errorCode) {
                    case ConstantErrorCode.Forbidden:
                        onException_403_Forbidden(message, errorCode);
                        break;
                    case ConstantErrorCode.UserLocked:
                        onException_403_UserLocked(message, errorCode);
                        break;
                    case ConstantErrorCode.InvalidSpaceStatus:
                        onException_403_InvalidSpaceStatus(message, errorCode);
                        break;
                    case ConstantErrorCode.SourceForbidden:
                        onException_403_SourceForbidden(message, errorCode);
                        break;
                    case ConstantErrorCode.DestForbidden:
                        onException_403_DestForbidden(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchApplication:
                        onException_403_NoSuchApplication(message, errorCode);
                        break;
                    case ConstantErrorCode.ScannedForbidden:
                        onException_403_ScannedForbidden(message, errorCode);
                        break;
                    case ConstantErrorCode.DynamicMailForbidden:
                        onException_403_DynamicMailForbidden(message, errorCode);
                        break;
                    case ConstantErrorCode.DynamicPhoneForbidden:
                        onException_403_DynamicPhoneForbidden(message, errorCode);
                        break;
                    case ConstantErrorCode.SecurityMatrixForbidden:
                        onException_403_SecurityMatrixForbidden(message, errorCode);
                        break;
                    default:
                        onException_undefined(message, errorCode);
                        break;
                }
            }
            break;
            case ConstantErrorCode.ERROR_CODE_404_NO_RESOURCE: {
                switch (errorCode) {
                    case ConstantErrorCode.NoSuchUser:
                        onException_404_NoSuchUser(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchItem:
                        onException_404_NoSuchItem(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchFolder:
                        onException_404_NoSuchFolder(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchFile:
                        onException_404_NoSuchFile(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchApplication:
                        onException_404_NoSuchApplication(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchVersion:
                        onException_404_NoSuchVersion(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchToken:
                        onException_404_NoSuchToken(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchLink:
                        onException_404_NoSuchLink(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchShare:
                        onException_404_NoSuchShare(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchRegion:
                        onException_404_NoSuchRegion(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchParent:
                        onException_404_NoSuchParent(message, errorCode);
                        break;
                    case ConstantErrorCode.LinkNotEffective:
                        onException_404_LinkNotEffective(message, errorCode);
                        break;
                    case ConstantErrorCode.LinkExpired:
                        onException_404_LinkExpired(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchSource:
                        onException_404_NoSuchSource(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchDest:
                        onException_404_NoSuchDest(message, errorCode);
                        break;
                    case ConstantErrorCode.NoThumbnail:
                        onException_404_NoThumbnail(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchOption:
                        onException_404_NoSuchOption(message, errorCode);
                        break;
                    case ConstantErrorCode.AbnormalTeamStatus:
                        onException_404_AbnormalTeamStatus(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchGroup:
                        onException_404_NoSuchGroup(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchMember:
                        onException_404_NoSuchMember(message, errorCode);
                        break;
                    case ConstantErrorCode.AbnormalGroupStatus:
                        onException_404_AbnormalGroupStatus(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchTeamspace:
                        onException_404_NoSuchTeamspace(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchClient:
                        onException_404_NoSuchClient(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchACL:
                        onException_404_NoSuchACL(message, errorCode);
                        break;
                    case ConstantErrorCode.CallbackFailed:
                        onException_404_CallbackFailed(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchAccount:
                        onException_404_NoSuchAccount(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchRole:
                        onException_404_NoSuchRole(message, errorCode);
                        break;
                    case ConstantErrorCode.NoSuchEnterprise:
                        onException_404_NoSuchEnterprise(message, errorCode);
                        break;
                    case ConstantErrorCode.ObjectNotFound:
                        onException_404_ObjectNotFound(message, errorCode);
                        break;
                    default:
                        onException_undefined(message, errorCode);
                        break;
                }

            }
            break;
            case ConstantErrorCode.ERROR_CODE_405_NOT_ALLOWED: {
                switch (errorCode) {
                    case ConstantErrorCode.MethodNotAllowed:
                        onException_405_MethodNotAllowed(message, errorCode);
                        break;
                    case ConstantErrorCode.InvalidProtocol:
                        onException_405_InvalidProtocol(message, errorCode);
                        break;
                    case ConstantErrorCode.InvalidLicense:
                        onException_405_InvalidLicense(message, errorCode);
                        break;
                    default:
                        onException_undefined(message, errorCode);
                }
            }
            break;
            case ConstantErrorCode.ERROR_CODE_409_SOURCE_CONFLICT: {
                switch (errorCode) {
                    case ConstantErrorCode.Conflict:
                        onException_409_Conflict(message, errorCode);
                        break;
                    case ConstantErrorCode.ConflictUser:
                        onException_409_ConflictUser(message, errorCode);
                        break;
                    case ConstantErrorCode.RepeatNameConflict:
                        onException_409_RepeatNameConflict(message, errorCode);
                        break;
                    case ConstantErrorCode.SubFolderConflict:
                        onException_409_SubFolderConflict(message, errorCode);
                        break;
                    case ConstantErrorCode.SameParentConflict:
                        onException_409_SameParentConflict(message, errorCode);
                        break;
                    case ConstantErrorCode.SameNodeConflict:
                        onException_409_SameNodeConflict(message, errorCode);
                        break;
                    case ConstantErrorCode.LinkExistedConflict:
                        onException_409_LinkExistedConflict(message, errorCode);
                        break;
                    case ConstantErrorCode.ExistMemberConflict:
                        onException_409_ExistMemberConflict(message, errorCode);
                        break;
                    case ConstantErrorCode.ExistTeamspaceConflict:
                        onException_409_ExistTeamspaceConflict(message, errorCode);
                        break;
                    case ConstantErrorCode.AsyncNodesConflict:
                        onException_409_AsyncNodesConflict(message, errorCode);
                        break;
                    case ConstantErrorCode.ExceedQuota:
                        onException_409_ExceedQuota(message, errorCode);
                        break;
                    case ConstantErrorCode.ConflictDomain:
                        onException_409_ConflictDomain(message, errorCode);
                        break;
                    case ConstantErrorCode.ConflictEmail:
                        onException_409_ConflictEmail(message, errorCode);
                        break;
                    case ConstantErrorCode.ExistFavoriteConflict:
                        ToastUtils.showToast(this, R.string.has_add_favorite);
                        break;
                    default:
                        onException_undefined(message, errorCode);
                        break;
                }
            }
            break;
            case ConstantErrorCode.ERROR_CODE_412_REQUEST: {
                switch (errorCode) {
                    case ConstantErrorCode.TooManyRequests:
                        onException_412_TooManyRequests(message, errorCode);
                        break;
                    case ConstantErrorCode.PreconditionFailed:
                        onException_412_PreconditionFailed(message, errorCode);
                        break;
                    case ConstantErrorCode.ExceedMaxLinkNum:
                        onException_412_ExceedMaxLinkNum(message, errorCode);
                        break;
                    case ConstantErrorCode.FileScanning:
                        onException_412_FileScanning(message, errorCode);
                        break;
                    case ConstantErrorCode.EmailChangeConflict:
                        onException_412_EmailChangeConflict(message, errorCode);
                        break;
                    case ConstantErrorCode.ExceedUserMaxNodeNum:
                        onException_412_ExceedUserMaxNodeNum(message, errorCode);
                        break;
                    case ConstantErrorCode.ExceedMaxMembers:
                        onException_412_ExceedMaxMembers(message, errorCode);
                        break;
                    case ConstantErrorCode.FileConverting:
                        onException_412_FileConverting(message, errorCode);
                        break;
                    case ConstantErrorCode.FileConvertNotSupport:
                        onException_412_FileConvertNotSupport(message, errorCode);
                        break;
                    case ConstantErrorCode.FileConvertFailed:
                        onException_412_FileConvertFailed(message, errorCode);
                        break;
                    case ConstantErrorCode.CloudTeamSpaceExceedMax:
                        onException_412_CloudTeamSpaceExceedMax(message, errorCode);
                        break;
                    case ConstantErrorCode.ExceedTeamSpaceMaxMemberNum:
                        onException_412_CloudTeamSpaceMemberExceedMax(message, errorCode);
                        break;
                    default:
                        onException_undefined(message, errorCode);
                        break;
                }
            }
            break;
            case ConstantErrorCode.ERROR_CODE_500_INNER_ERROR: {
                switch (errorCode) {
                    case ConstantErrorCode.InternalServerError:
                        onException_500_InternalServerError(message, errorCode);
                        break;
                    case ConstantErrorCode.FSException:
                        onException_500_FSException(message, errorCode);
                        break;
                    default:
                        onException_undefined(message, errorCode);
                        break;
                }
            }
            break;
            case ConstantErrorCode.ERROR_CODE_507_SERVER_SAVE: {
                switch (errorCode) {
                    case ConstantErrorCode.InsufficientStorage:
                        onException_507_InsufficientStorage(message, errorCode);
                        break;
                    case ConstantErrorCode.TEAM_SPECE_OVER_SIZE:
                        onException_507_TeamSpaceOverSize(message, errorCode);
                        break;
                    default:
                        onException_undefined(message, errorCode);
                        break;
                }
            }
            break;
            default:
                onException_undefined(message, errorCode);
                break;
        }


    }

    private void onExceptionBadRequest(String message, String errorCode) {
        if (message.startsWith("Required parameter 'Range' is incorrect.")) {
            Toast.makeText(MyApplication.getInstance(), R.string.file_change_download_again, Toast.LENGTH_SHORT).show();
        } else {
            onException_undefined(message, errorCode);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(SocketTimeoutException e) {
        handleSocketTimeout(e);
    }

    protected void handleSocketTimeout(SocketTimeoutException e) {
        dissmissExceptionDialog();
        Toast.makeText(this, getString(R.string.net_time_out), Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(ClientException event) {
        int code = event.getErrorCode();
        String msg = event.getMessage();
        switch (code) {
            case ClientException.CODE_DISABLE_NETWORK_900://当前网络不可用
                onException_900_UnusableNetWork(msg, code);
                break;
            case ClientException.CODE_CERT_EXCEPTION:
                onException_CertError();
                break;
            case 0:
                if (msg.equalsIgnoreCase(ConstantErrorCode.SAME_NAME_EXSIT_EXCEPTION)) {
                    ToastUtils.showToast(MyApplication.getInstance(), getString(R.string.upload_failed) + ", "
                            + getString(R.string.repeat_name));
                } else {
                    toastShow(event.getMessage(), String.valueOf(event.getErrorCode()));
                }
                break;
            default:
                toastShow(event.getMessage(), String.valueOf(event.getErrorCode()));
                break;
        }
    }


    protected void onException_900_UnusableNetWork(String msg, int code) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void onException_400_InvalidParameter(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_400_InvalidPart(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_400_InvalidRange(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_400_InvalidTeamRole(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_400_InvalidRegion(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_400_InvalidPermissionRole(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_400_InvalidFileType(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_400_UnmatchedDownloadUrl(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_401_Unauthorized(String message, String code) {
        dissmissExceptionDialog();
        toastShow(message, code);
    }

    protected void onException_401_ClientUnauthorized(String message, String code) {
        showToast(R.string.client_unauthorized, message, code);
    }

    protected void onException_403_Forbidden(String message, String code) {
        showToast(R.string.forbidden_operation, message, code);        //返回的message一致，暂时给出统一提示
    }

    protected void onException_403_UserLocked(String message, String code) {
        dissmissExceptionDialog();
        showToast(R.string.user_locked, message, code);
    }

    protected void onException_403_InvalidSpaceStatus(String message, String code) {
        showToast(R.string.invalid_space_status, message, code);
    }

    protected void onException_403_SourceForbidden(String message, String code) {
        showToast(R.string.source_forbidden, message, code);
    }

    protected void onException_403_DestForbidden(String message, String code) {
        showToast(R.string.dest_forbidden, message, code);
    }

    protected void onException_403_NoSuchApplication(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_403_ScannedForbidden(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_403_DynamicMailForbidden(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_403_DynamicPhoneForbidden(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_403_SecurityMatrixForbidden(String message, String code) {
        dissmissExceptionDialog();
        showToast(R.string.security_matrix_forbidden, message, code);
    }

    protected void onException_404_NoSuchUser(String message, String code) {
        showToast(R.string.no_such_user, message, code);
    }

    protected void onException_404_NoSuchItem(String message, String code) {
        showToast(R.string.no_such_item, message, code);
    }

    protected void onException_404_NoSuchFolder(String message, String code) {
        showToast(R.string.no_such_folder, message, code);
    }

    protected void onException_404_NoSuchFile(String message, String code) {
        showToast(R.string.no_such_file, message, code);
    }

    protected void onException_404_NoSuchApplication(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchVersion(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchToken(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchLink(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchShare(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchRegion(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchParent(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_LinkNotEffective(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_LinkExpired(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchSource(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchDest(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoThumbnail(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchOption(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_AbnormalTeamStatus(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchGroup(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchMember(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_AbnormalGroupStatus(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchTeamspace(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchClient(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchACL(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_CallbackFailed(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchAccount(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchRole(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_NoSuchEnterprise(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_404_ObjectNotFound(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_405_MethodNotAllowed(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_405_InvalidProtocol(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_405_InvalidLicense(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_409_Conflict(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_409_ConflictUser(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_409_RepeatNameConflict(String message, String code) {
        showToast(R.string.repeat_name, message, code);
    }

    protected void onException_409_SubFolderConflict(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_409_SameParentConflict(String message, String code) {
//        toastShow(message, code);              //已经在MoveFileTargetActivity中给出提示，避免重复提示
    }

    protected void onException_409_SameNodeConflict(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_409_LinkExistedConflict(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_409_ExistMemberConflict(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_409_ExistTeamspaceConflict(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_409_AsyncNodesConflict(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_409_ExceedQuota(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_409_ConflictDomain(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_409_ConflictEmail(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_412_TooManyRequests(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_412_PreconditionFailed(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_412_ExceedMaxLinkNum(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_412_FileScanning(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_412_EmailChangeConflict(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_412_ExceedUserMaxNodeNum(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_412_ExceedMaxMembers(String message, String code) {
//        toastShow(message, code);
        showToast(R.string.exceed_team_space_max_member_num, message, code);
    }

    protected void onException_412_FileConverting(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_412_FileConvertNotSupport(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_412_FileConvertFailed(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_412_CloudTeamSpaceExceedMax(String message, String code) {
        Toast.makeText(this, R.string.team_space_exceed_max, Toast.LENGTH_SHORT).show();
    }

    protected void onException_412_CloudTeamSpaceMemberExceedMax(String message, String code) {
        Toast.makeText(this, R.string.team_space_member_exceed_max, Toast.LENGTH_SHORT).show();
    }

    protected void onException_500_InternalServerError(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_500_FSException(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_507_InsufficientStorage(String message, String code) {
        toastShow(message, code);
    }

    protected void onException_507_TeamSpaceOverSize(String message, String code) {
        Toast.makeText(this, R.string.team_space_over_size, Toast.LENGTH_SHORT).show();
    }

    protected void onException_undefined(String message, String code) {
        dissmissExceptionDialog();
        toastShow(message, code);
    }

    /**
     * 安全校验证书异常时，dismiss进度条，只有登录界面需要重写此方法
     */
    protected void onException_CertError() {
        dissmissExceptionDialog();
        Toast.makeText(this, getString(R.string.cert_error), Toast.LENGTH_SHORT).show();
        LogUtil.e(TAG, ConstantErrorCode.CertPathValidatorException);
    }

    /**
     * 安全校验证书异常时，dismiss进度条，只有登录界面需要重写此方法
     */
    protected void onException_Connect() {
        dissmissExceptionDialog();
        Toast.makeText(this, getString(R.string.connect_exception), Toast.LENGTH_SHORT).show();
        LogUtil.e(TAG, ConstantErrorCode.ConnectException);
    }

    protected void toastShow(String message, String code) {
        Log.e(TAG, code + " _ " + message);
        String errorMessage = code + "_" + message;
        if (errorMessage.contains(ConstantErrorCode.Unauthorized)) {
            if (message.toLowerCase().contains(ConstantErrorCode.UsernameOrPasswordIncorrect)) {
                ToastUtils.showToast(this, R.string.a_or_p_not_right);
            } else {
                ToastUtils.showToast(this, R.string.unauthorized);
            }
        } else {
            switch (errorMessage) {
                default:
                    dissmissExceptionDialog();
                    if (message.toLowerCase().contains(ConstantErrorCode.ServerException)) {
                        ToastUtils.showToast(this, code + "_" + message);
                    } else if (message.toLowerCase().contains(ConstantErrorCode.UsernameOrPasswordIncorrect)) {
                        ToastUtils.showToast(this, R.string.a_or_p_not_right);
                    } else {
                        ToastUtils.showToast(MyApplication.getInstance(), R.string.error_occurred);
                    }
                    break;
            }
        }
    }

    private void showToast(int sourceId, String message, String code) {
        ToastUtils.showToast(this, sourceId);
        LogUtil.e(TAG, code + "_" + message);
    }

    /**
     * 只有LoginActivity需要重写此方法
     */
    protected void dissmissExceptionDialog() {

    }

    @Override
    public void showToast(int strId) {
        ToastUtils.showToast(this, strId);
    }

    @Override
    public void showToast(String str) {
        ToastUtils.showToast(this, str);
    }
}


