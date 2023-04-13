// @ts-ignore
/* eslint-disable */
import {request} from 'umi';
import {RecordKey} from "@ant-design/pro-utils/lib/useEditableArray";

/** 获取当前的用户 GET /api/user/currentUser */
export async function currentUser(options?: { [key: string]: any }) {
  return request<{
    data: API.CurrentUser;
  }>('/api/user/currentUser', {
    method: 'GET',
    ...(options || {}),
  });
}

/** 退出登录接口 POST /api/user/logout */
export async function outLogin(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/user/logout', {
    method: 'POST',
    ...(options || {}),
  });
}

/** 登录接口 POST /api/user/login */
export async function login(body: API.LoginParams, options?: { [key: string]: any }) {
  //return request<API.BaseResponse<API.LoginResult>>('/api/user/login', {
  return request<API.BaseResponse<API.LoginResult>>('/api/user/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 注册接口 POST /api/user/register */
export async function register(body: API.RegisterParams, options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.RegisterResult>>('/api/user/register', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 搜索用户 GET /api/user/search */
export async function searchUsers(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.CurrentUser[]>>('/api/user/search', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 更新用户信息接口 POST /api/user/update */
export async function updateUser(body: API.CurrentUser, options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.CurrentUser>>('/api/user/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

// @ts-ignore
/** 删除用户信息接口 POST /api/user/delete */
export async function deleteUser(body: RecordKey, options?: { [p: string]: any }) {
  return request<API.BaseResponse<API.CurrentUser>>('/api/user/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}



/** 每日健康上报接口 POST /api/health/addHealthInfo */
export async function healthReport(body: API.HealthSmParams, options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.HealthSmResult>>('/api/health/addHealthInfo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 每日健康上报列表 GET /api/health/search */
export async function searchHealths(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.HealthSmParams[]>>('/api/health/search', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 更新每日健康上报信息接口 POST /api/health/update */
export async function updateHealthInfo(body: API.HealthSmParams & { index?: number }, options?: { [p: string]: any }) {
  return request<API.BaseResponse<API.HealthSmParams>>('/api/health/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

// @ts-ignore
/** 删除每日健康上报信息接口 POST /api/health/delete */
export async function deleteHealthInfo(body: RecordKey, options?: { [p: string]: any }) {
  return request<API.BaseResponse<API.HealthSmParams>>('/api/health/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 物资添加接口 POST /api/material/addMaterialInfo */
export async function MaterialInfoReport(body: API.MaterialInfoParams, options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.MaterialInfoResult>>('/api/material/addMaterialInfo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 物资信息列表 GET /api/material/search */
export async function searchMaterialInfos(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.MaterialInfoParams[]>>('/api/material/search', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 物资信息更新接口 POST /api/material/update */
export async function updateMaterialInfo(body: API.MaterialInfoParams, options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.MaterialInfoParams>>('/api/material/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

// @ts-ignore
/** 删除物资信息接口 POST /api/material/delete */
export async function deleteMaterialInfo(body: RecordKey, options?: { [p: string]: any }) {
  return request<API.BaseResponse<API.MaterialInfoParams>>('/api/material/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 物资出库更新接口 POST /api/material/materialOut */
export async function materialOut(body: API.MaterialInfoParams, options?: { [key: string]: any }) {
  return request<API.BaseResponse<boolean>>('/api/material/materialOut', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}


/** 图片上传接口 POST /api/oss/file/upload */
export async function uploadFile(body: FormData, options?: { [key: string]: any }) {
  return request<API.BaseResponse<string>>('/api/oss/file/upload', {
    method: 'POST',
    headers: {
    },
    data:body,
    ...(options || {}),
  });
}


/** 请假申请接口 POST /api/leave/addLeaveApply */
export async function addLeaveApply(body: API.LeaveApplyParams, options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.LeaveApplyResult>>('/api/leave/addLeaveApply', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 请假申请信息列表 GET /api/leave/search */
export async function searchLeaveApplyInfos(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.LeaveApplyParams[]>>('/api/leave/search', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 请假申请信息更新接口 POST /api/leave/update */
export async function updateLeaveApplyInfos(body: API.LeaveApplyParams, options?: { [key: string]: any }) {
  return request<API.BaseResponse<number>>('/api/leave/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

// @ts-ignore
/** 删除请假申请信息接口 POST /api/leave/delete */
export async function deleteLeaveApplyInfo(body: RecordKey, options?: { [p: string]: any }) {
  return request<API.BaseResponse<boolean>>('/api/leave/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}


/** 发布公告接口 POST /api/notice/addNoticeInfo */
export async function announce(body: API.NoticeInfoParams, options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.NoticeInfoResult>>('/api/notice/addNoticeInfo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 公告信息列表 GET /api/notice/search */
export async function searchNoticeInfos(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.NoticeInfoParams[]>>('/api/notice/search', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 公告信息更新接口 POST /api/notice/update */
export async function updateNoticeInfos(body: API.NoticeInfoParams, options?: { [key: string]: any }) {
  return request<API.BaseResponse<number>>('/api/notice/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

// @ts-ignore
/** 删除公告信息接口 POST /api/notice/delete */
export async function deleteNoticeInfo(body: RecordKey, options?: { [p: string]: any }) {
  return request<API.BaseResponse<boolean>>('/api/notice/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 公告信息列表 GET /api/notice/list */
export async function NoticeListInfo(options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.NoticeInfoParams[]>
    >('/api/notice/list', {
    method: 'GET',
    ...(options || {}),
  });
}

/** 物资出库信息列表 GET /api/material/list */
export async function MaterialOutListInfo(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any }) {
  return request<API.BaseResponse<API.MaterialOutInfoParams[]>>('/api/material/list', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}





/** 此处后端没有提供注释 GET /api/notices */
export async function getNotices(options?: { [key: string]: any }) {
  return request<API.NoticeIconList>('/api/notices', {
    method: 'GET',
    ...(options || {}),
  });
}

/** 获取用户列表 GET /api/user/search */
export async function rule(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  options?: { [key: string]: any },
) {
  return request<API.RuleList>('/api/user/search', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 新建规则 PUT /api/rule */
export async function updateRule(options?: { [key: string]: any }) {
  return request<API.RuleListItem>('/api/rule', {
    method: 'PUT',
    ...(options || {}),
  });
}



/** 删除规则 DELETE /api/rule */
export async function removeRule(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/rule', {
    method: 'DELETE',
    ...(options || {}),
  });
}
