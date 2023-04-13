// @ts-ignore
/* eslint-disable */

declare namespace API {

  /**
   * 用户信息（字段）
   */
  type CurrentUser = {
    id?: string;
    username: string;
    gender?: number;
    phone: string;
    nikeName: string;
    avatarUrl?: string;
    email: string;
    userStatus: number;
    userRole: number;
    department: string;
    classes: string;
    dormitoryNo: string;
    createTime: Date;
  };

  //健康上报
  type HealthSmResult = number;
  type HealthSmParams = {
    id?:number,
    uid?:string,
    username?:string,
    physicalCondition?: number;
    atSchool?: number;
    location?: string;
    isHighRisk?: number;
    healthCodeStatus?: number;
  };

  //物资上报
  type MaterialInfoResult = number;
  type MaterialInfoParams = {
    id?:number,
    typeName?:string,
    name?:string,
    img?:string,
    specification?:string,
    unit?:string,
    creator?:string,
    description?:string,
    total?:number,
    status?:number,
  };
  type MaterialOutParams = {
    id?:number,
    name?:string,
    reason?:string,
    total?:number,
    creator?:string
  };



  //请假申请
  type LeaveApplyResult = string;
  type LeaveApplyParams = {
    id?:string,
    uid?:string,
    username?:string,
    department?:string,
    classes?:string,
    dormitoryNo?:string,
    reason?:string,
    leaveType?:number,
    status?:number,
    time?:string,
    day?:string,
    location?:string,
    address?:string,
    img?:string,
    traffic?:string,
    emergencyTelephoneNumber?:string,
    emergencyContact?:string,
    opinion?:string,
  };

  //公告信息
  type NoticeInfoResult = number;
  type NoticeInfoParams = {
    id?:number,
    content?:string,
    publisher?:string,
    type?:number,
    createTime?: Date;
  };

  type MaterialOutInfoParams = {
    id?:number,
    name?:string,
    quantity?:number,
    superintendent?:string,
    reason?:string,
    createTime?: Date;
  };



  /**
   * 对接后端通用返回类
   */
  type BaseResponse<T> = {
    code: number;
    data: T;
    message: string;
    description: string;
  }

  type LoginResult = {
    status?: string;
    type?: string;
    currentAuthority?: string;
  };

  type PageParams = {
    current?: number;
    pageSize?: number;
  };

  type RuleListItem = {
    id?: string;
    username?: string;
    gender?: number;
    phone?: string;
    department?: string;
    classes?: string;
    dormitory_no?: string;
    user_status?: number;
    create_time?: Date;
  };



  type RuleList = {
    data?: RuleListItem[];
    /** 列表的内容总数 */
    total?: number;
    success?: boolean;
  };

  type FakeCaptcha = {
    code?: number;
    status?: string;
  };

  type LoginParams = {
    id?: string;
    userPassword?: string;
    autoLogin?: boolean;
    type?: string;
  };

  type RegisterResult = string;
  type RegisterParams = {
    id?: string;
    username?: string;
    userPassword?: string;
    checkPassword?: string;
    autoLogin?: boolean;
    type?: string;
  };









  type ErrorResponse = {
    /** 业务约定的错误码 */
    errorCode: string;
    /** 业务上的错误信息 */
    errorMessage?: string;
    /** 业务上的请求是否成功 */
    success?: boolean;
  };

  type NoticeIconList = {
    data?: NoticeIconItem[];
    /** 列表的内容总数 */
    total?: number;
    success?: boolean;
  };

  type NoticeIconItemType = 'notification' | 'message' | 'event';

  type NoticeIconItem = {
    id?: string;
    extra?: string;
    key?: string;
    read?: boolean;
    avatar?: string;
    title?: string;
    status?: string;
    datetime?: string;
    description?: string;
    type?: NoticeIconItemType;
  };
}
