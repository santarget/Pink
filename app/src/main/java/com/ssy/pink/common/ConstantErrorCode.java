package com.ssy.pink.common;

/**
 * Created by Administrator on 2016/12/12.
 */

public class ConstantErrorCode {

    // ==================400==========================
    public final static int ERROR_CODE_400_PARAMS = 400;
    public final static String InvalidParameter = "InvalidParameter";
    public final static String InvalidPart = "InvalidPart";
    public final static String InvalidRange = "InvalidRange";
    public final static String InvalidTeamRole = "InvalidTeamRole";
    public final static String InvalidRegion = "InvalidRegion";
    public final static String InvalidPermissionRole = "InvalidPermissionRole";
    public final static String InvalidFileType = "InvalidFileType";
    public final static String UnmatchedDownloadUrl = "UnmatchedDownloadUrl";
    public final static String BAD_REQUEST = "BadRequest";

    // ==================401==========================
    public final static int ERROR_CODE_401_UNAUTHORIZED = 401;
    public final static String Unauthorized = "Unauthorized";
    public final static String ClientUnauthorized = "ClientUnauthorized";
    public final static String UsernameOrPasswordIncorrect = "user name or password is incorrect";

    // ==================403==========================
    public final static int ERROR_CODE_403_NO_RIGHT = 403;
    public final static String Forbidden = "Forbidden";
    public final static String UserLocked = "UserLocked";
    public final static String InvalidSpaceStatus = "InvalidSpaceStatus";
    public final static String SourceForbidden = "SourceForbidden";
    public final static String DestForbidden = "DestForbidden";
    public final static String SecurityMatrixForbidden = "SecurityMatrixForbidden";
    //403 和 404 都有
    public final static String NoSuchApplication = "NoSuchApplication";
    public final static String ScannedForbidden = "ScannedForbidden";
    public final static String DynamicMailForbidden = "DynamicMailForbidden";
    public final static String DynamicPhoneForbidden = "DynamicPhoneForbidden";


    // ==================404==========================
    public final static int ERROR_CODE_404_NO_RESOURCE = 404;
    public final static String NoSuchUser = "NoSuchUser";
    public final static String NoSuchItem = "NoSuchItem";
    public final static String NoSuchFolder = "NoSuchFolder";
    public final static String NoSuchFile = "NoSuchFile";
    public final static String NoSuchVersion = "NoSuchVersion";
    public final static String NoSuchToken = "NoSuchToken";
    public final static String NoSuchLink = "NoSuchLink";
    public final static String NoSuchShare = "NoSuchShare";
    public final static String NoSuchRegion = "NoSuchRegion";
    public final static String NoSuchParent = "NoSuchParent";
    public final static String LinkNotEffective = "LinkNotEffective";
    public final static String LinkExpired = "LinkExpired";
    public final static String NoSuchSource = "NoSuchSource";
    public final static String NoSuchDest = "NoSuchDest";
    public final static String NoThumbnail = "NoThumbnail";
    public final static String NoSuchOption = "NoSuchOption";
    public final static String AbnormalTeamStatus = "AbnormalTeamStatus";
    public final static String NoSuchGroup = "NoSuchGroup";
    public final static String NoSuchMember = "NoSuchMember";
    public final static String AbnormalGroupStatus = "AbnormalGroupStatus";
    public final static String NoSuchTeamspace = "NoSuchTeamspace";
    public final static String NoSuchClient = "NoSuchClient";
    public final static String NoSuchACL = "NoSuchACL";
    public final static String CallbackFailed = "CallbackFailed";
    public final static String NoSuchAccount = "NoSuchAccount";
    public final static String NoSuchRole = "NoSuchRole";
    public final static String NoSuchEnterprise = "NoSuchEnterprise";
    public final static String ObjectNotFound = "ObjectNotFound";


    // ==================405==========================
    public final static int ERROR_CODE_405_NOT_ALLOWED = 405;
    public final static String MethodNotAllowed = "MethodNotAllowed";
    public final static String InvalidProtocol = "InvalidProtocol";
    public final static String InvalidLicense = "InvalidLicense";


    // ==================409==========================
    public final static int ERROR_CODE_409_SOURCE_CONFLICT = 409;
    public final static String Conflict = "Conflict";
    public final static String ConflictUser = "ConflictUser";
    public final static String RepeatNameConflict = "RepeatNameConflict";
    public final static String SubFolderConflict = "SubFolderConflict";
    public final static String SameParentConflict = "SameParentConflict";
    public final static String SameNodeConflict = "SameNodeConflict";
    public final static String LinkExistedConflict = "LinkExistedConflict";
    public final static String ExistMemberConflict = "ExistMemberConflict";
    public final static String ExistTeamspaceConflict = "ExistTeamspaceConflict";
    public final static String AsyncNodesConflict = "AsyncNodesConflict";
    public final static String ExceedQuota = "ExceedQuota";
    public final static String ConflictDomain = "ConflictDomain";
    public final static String ConflictEmail = "ConflictEmail";
    public final static String ExistFavoriteConflict = "ExistFavoriteConflict";

    // ==================412==========================
    public final static int ERROR_CODE_412_REQUEST = 412;
    public final static String TooManyRequests = "TooManyRequests";
    public final static String PreconditionFailed = "PreconditionFailed";
    public final static String ExceedMaxLinkNum = "ExceedMaxLinkNum";
    public final static String FileScanning = "FileScanning";
    public final static String EmailChangeConflict = "EmailChangeConflict";
    public final static String ExceedUserMaxNodeNum = "ExceedUserMaxNodeNum";
    public final static String ExceedMaxMembers = "ExceedMaxMembers";
    public final static String FileConverting = "FileConverting";
    public final static String FileConvertNotSupport = "FileConvertNotSupport";
    public final static String FileConvertFailed = "FileConvertFailed";
    public final static String CloudTeamSpaceExceedMax = "CloudTeamSpaceExceedMax";
    public final static String ExceedTeamSpaceMaxMemberNum = "ExceedTeamSpaceMaxMemberNum";

    // ==================500==========================
    public final static int ERROR_CODE_500_INNER_ERROR = 500;
    public final static String InternalServerError = "InternalServerError";
    public final static String FSException = "FSException";
    public final static String ServerException = "server exception";

    // ==================507==========================
    public final static int ERROR_CODE_507_SERVER_SAVE = 507;
    public final static String InsufficientStorage = "InsufficientStorage";
    public final static String TEAM_SPECE_OVER_SIZE = "Insufficient Storage";


    public static final String CertPathValidatorException = "CertPathValidatorException";//安全校验证书异常
    public static final String ConnectException = "ConnectException";//服务器连接异常
    public static final String SocketTimeoutException = "SocketTimeoutException";

    //上传文件存在同名文件夹冲突
    public static final String SAME_NAME_EXSIT_EXCEPTION = "A same name file or folder is already exsit.";

}
