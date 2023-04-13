import type {ActionType, ProColumns} from '@ant-design/pro-components';
import {ProTable} from '@ant-design/pro-components';
import {useRef} from 'react';
import {deleteLeaveApplyInfo, searchLeaveApplyInfos, updateLeaveApplyInfos} from "@/services/ant-design-pro/api";
import {Image, message} from "antd";

// @ts-ignore
// @ts-ignore
const columns: ProColumns<API.LeaveApplyParams>[] = [
  {
    title: '序号',
    dataIndex: 'index',
    valueType: 'indexBorder',
    width: 48,
  },
  {
    title: '学生学号',
    dataIndex: 'uid',
    valueType: 'textarea',
  },
  {
    title: '学生姓名',
    dataIndex: 'username',
    valueType: 'textarea',
  },
  {
    title: '院系',
    dataIndex: 'department',
    valueType: 'textarea',
  },
  {
    title: '班级',
    dataIndex: 'classes',
    valueType: 'textarea',
  },
  {
    title: '宿舍号',
    dataIndex: 'dormitoryNo',
    valueType: 'textarea',
  },

  {
    title: '请假原因',
    dataIndex: 'reason',
    valueType: 'textarea',
  },
  {
    title: '请假类型',
    dataIndex: 'leaveType',
    valueType: 'select',
    valueEnum: {
      1: {
        text: '事假',
      },
      2: {
        text: '病假',
      },
      3: {
        text: '公假',
      },
      4: {
        text: '申请校外实习',
      },
    },
  },
  {
    title: '审批状态',
    dataIndex: 'status',
    valueType: 'select',
    valueEnum: {
      0: {
        text: '撤销',
      },
      1: {
        text: '待审核',
        status: 'Warning',
      },
      2: {
        text: '审核通过',
        status: 'Success',
      },
      3: {
        text: '审核不通过',
        status: 'Error',
      },

    },
  },
  {
    title: '请假时间区间',
    dataIndex: 'time',
    valueType: 'textarea',
  },
  {
    title: '目的地',
    dataIndex: 'location',
    valueType: 'textarea',
  },
  {
    title: '目前所在地',
    dataIndex: 'address',
    valueType: 'textarea',
  },
  {
    title: '附件',
    dataIndex: 'img',
    render: (_, record) => (
      <div>
        <Image src={record.img} width={50}/>
      </div>
    ),
    search:false,
  },
  {
    title: '交通工具',
    dataIndex: 'traffic',
    valueType: 'textarea',
  },
  {
    title: '紧急联系人',
    dataIndex: 'emergencyContact',
    valueType: 'textarea',
  },
  {
    title: '紧急联系人电话',
    dataIndex: 'emergencyTelephoneNumber',
    valueType: 'textarea',
  },
  {
    title: '审批意见',
    dataIndex: 'opinion',
    valueType: 'textarea',
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
    <ProTable<API.LeaveApplyParams>
      columns={columns}
      actionRef={actionRef}
      cardBordered
      request={searchLeaveApplyInfos}
      editable={{
        type: 'multiple',
        //修改后保存
        onSave: async (rowKey, data, row) => {
          try {
            const result = await updateLeaveApplyInfos(data);
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
            const result = await deleteLeaveApplyInfo(rowKey)
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
