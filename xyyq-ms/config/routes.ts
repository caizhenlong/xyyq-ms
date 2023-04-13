export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {
        name: '登录',
        path: '/user/login',
        component: './user/Login',
      },
      {
        name: '注册',
        path: '/user/register',
        component: './user/Register',
      },
      {
        component: './404',
      },
    ],
  },

/*  {
    path: '/welcome',
    name: '欢迎',
    icon: 'smile',
    component: './Welcome',
  },*/

  {
    path: '/notice',
    name: '公告',
    icon: 'crown',
    routes: [
      {
        path: '/notice/noticeInfo',
        name: '公告',
        icon: 'smile',
        component: './Notice/NoticeInfo',
      },
      {
        path: '/notice/notice-announce',
        name: '发布公告',
        icon: 'smile',
        component: './Notice/Announce',
        access: 'canAdminAndTeacher',
      },
      {
        path: '/notice/notice-manage',
        name: '公告管理',
        icon: 'smile',
        component: './Notice/NoticeManage',
        access: 'canAdminAndTeacher',
      },
      {
        component: './404',
      },
    ],
  },

  {
    path: '/userInfo/settings',
    name: '个人设置',
    icon: 'crown',
    component: './UserInfo',
    hideInMenu:true
  },

  {
    path: '/admin',
    name: '用户管理',
    icon: 'crown',
    access: 'canAdmin',
    component: './Admin/UserManage',
  },
  {
    path: '/health',
    name: '健康上报管理',
    icon: 'crown',
    routes: [
      {
        path: '/health/health-report',
        name: '健康上报',
        icon: 'smile',
        component: './Health/HealthReport',
      },
      {
        path: '/health/health-manage',
        name: '健康上报信息管理',
        icon: 'smile',
        component: './Health/HealthManage',
        access: 'canAdminAndTeacher',
      },
      {
        component: './404',
      },
    ],
  },

  {
    path: '/material',
    name: '物资管理',
    icon: 'crown',
    access: 'canAdminAndTeacher',
    routes: [
      {
        path: '/material/material-add',
        name: '物资入库',
        icon: 'smile',
        component: './Material/MaterialReport',
        access: 'canAdminAndTeacher',
      },
      {
        path: '/material/material-out',
        name: '物资出库',
        icon: 'smile',
        component: './Material/MaterialOut',
        access: 'canAdminAndTeacher',
      },
      {
        path: '/material/material-outInfo',
        name: '物资出库信息',
        icon: 'smile',
        component: './Material/MaterialOutInfo',
        access: 'canAdmin',
      },
      {
        path: '/material/material-manage',
        name: '物资信息管理',
        icon: 'smile',
        component: './Material/MaterialManage',
        access: 'canAdmin',
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/leave',
    name: '请假管理',
    icon: 'crown',
    routes: [
      {
        path: '/leave/leave-apply',
        name: '请假申请',
        icon: 'smile',
        component: './Leave/LeaveApply',
      },
      {
        path: '/leave/leave-approval',
        name: '请假审批',
        icon: 'smile',
        component: './Leave/LeaveApproval',
        access: 'canAdminAndTeacher',
      },
      {
        component: './404',
      },
    ],
  },

  {
    path: '/',
    redirect: '/notice/noticeInfo',
  },
  {
    component: './404',
  },
];
