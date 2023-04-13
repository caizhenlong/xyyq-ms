import type {ActionType, ProColumns} from '@ant-design/pro-components';
import {ProTable} from '@ant-design/pro-components';
import {useRef} from 'react';
import {deleteUser, searchUsers, updateUser} from "@/services/ant-design-pro/api";
import {message} from "antd";

const columns: ProColumns<API.CurrentUser>[] = [
  {
    title: '序号',
    dataIndex: 'index',
    valueType: 'indexBorder',
    width: 48,
  },
  {
    title: '学号',
    dataIndex: 'id',
    valueType: 'textarea',
    readonly:true,
    search:false,
  },
  {
    title: '姓名',
    dataIndex: 'username',
    valueType: 'textarea',
  },
  {
    title: '性别',
    dataIndex: 'gender',
    valueType: 'select',
    search:false,
    valueEnum: {
      1: {
        text: '男',
      },
      2: {
        text: '女',
      },
    },
  },
  {
    title: '联系电话',
    dataIndex: 'phone',
    valueType: 'textarea',
    search:false,
  },
  {
    title: '院系',
    dataIndex: 'department',
    valueType: 'textarea',
    search:false,
  },
  {
    title: '班级',
    dataIndex: 'classes',
    valueType: 'textarea',
    search:false,
  },
  {
    title: '宿舍号',
    dataIndex: 'dormitoryNo',
    valueType: 'textarea',
    search:false
  },
  {
    title: '状态',
    dataIndex: 'userStatus',
    valueType: 'select',
    search:false,
    valueEnum: {
      0: {
        text: '禁用',
        status: 'Error',
      },
      1: {
        text: '正常',
        status: 'Success',
      },
    },
  },
  {
    title: '角色',
    dataIndex: 'userRole',
    valueType: 'select',
    search:false,
    valueEnum: {
      0: {
        text: '学生',
      },
      1: {
        text: '教师',
      },
      2: {
        text: '管理员',
      },
    },
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    valueType: 'dateTime',
    search:false
  },

  {
    title: '操作',
    valueType: 'option',
    key: 'option',
    render: (text, record, _, action) => [
      <a
        key="editable"
        onClick={() => {
          // @ts-ignore
          action?.startEditable?.(record.id);
        }}
      >
        编辑
      </a>,
    ],
  },
];

export default () => {
  const actionRef = useRef<ActionType>();

  // @ts-ignore
  // @ts-ignore
  // @ts-ignore
  return (
    <ProTable<API.CurrentUser>
      columns={columns}
      actionRef={actionRef}
      cardBordered
      request={searchUsers}
      editable={{
        type: 'multiple',
        //修改后保存
        onSave: async (rowKey, data, row) => {
          try {
            const result = await updateUser(data);
            if (result.code==0) {
              const defaultLoginSuccessMessage = '修改成功！';
              message.success(defaultLoginSuccessMessage);
            }else {
              const defaultLoginFailureMessage = result.description;
              message.error(defaultLoginFailureMessage);
            }
          }catch (error: any) {
            const defaultLoginFailureMessage = '修改失败，请重试！';
            message.error(defaultLoginFailureMessage);
          }
        },
        //删除某一行
        onDelete: async (rowKey)=>{
/*          console.log(rowKey)*/
          try {
            const result = await deleteUser(rowKey)
            if (result.code==0) {
              const defaultLoginSuccessMessage = '删除成功！';
              message.success(defaultLoginSuccessMessage);
            }else {
              const defaultLoginFailureMessage = result.description;
              message.error(defaultLoginFailureMessage);
            }
          }catch (error: any) {
            const defaultLoginFailureMessage = '删除失败，请重试！';
            message.error(defaultLoginFailureMessage);
          }
        }
      }}
      columnsState={{
        persistenceKey: 'pro-table-singe-demos',
        persistenceType: 'localStorage',
        onChange(value) {
          console.log('value: ', value);
        },
      }}
      rowKey="id"
      //条件搜索
      search={{
        /*labelWidth: 'auto',*/
        defaultCollapsed: false,
        optionRender: (searchConfig, formProps, dom) => [
          ...dom.reverse(),
        ],
      }}
      /*options={{
        setting: {
          listsHeight: 400,
        },
      }}*/
      form={{
        // 由于配置了 transform，提交的参与与定义的不同这里需要转化一下
        syncToUrl: (values, type) => {
          if (type === 'get') {
            return {
              ...values,
              created_at: [values.startTime, values.endTime],
            };
          }
          return values;
        },
      }}
      pagination={{
        pageSize: 5,
        onChange: (page) => console.log(page),
      }}
      dateFormatter="string"
      headerTitle="用户信息"
      toolBarRender={() => [
      ]}
    />
  );
};
