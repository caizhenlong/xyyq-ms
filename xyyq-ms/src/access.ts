/**
 * @see https://umijs.org/zh-CN/plugins/plugin-access
 * */
export default function access(initialState: { currentUser?: API.CurrentUser } | undefined) {
  const {currentUser} = initialState ?? {};
  return {
    //userRole 0-学生 1-教师 2-管理员
    canStudent: currentUser && currentUser.userRole === 0,
    canTeacher: currentUser && currentUser.userRole === 1,
    canAdmin: currentUser && currentUser.userRole === 2,
    canAdminAndTeacher: currentUser && (currentUser.userRole===1 || currentUser.userRole===2),

  };
}
