import type {ActionType, ProColumns} from '@ant-design/pro-components';
import {ProTable} from '@ant-design/pro-components';
import {useRef} from 'react';
import {deleteNoticeInfo, searchNoticeInfos, updateNoticeInfos} from "@/services/ant-design-pro/api";
import {message} from "antd";


const columns: ProColumns<API.NoticeInfoParams>[] = [
  {
    title: '序号',
    dataIndex: 'index',
    valueType: 'indexBorder',
    width: 48,
  },
  {
    title: '编号',
    dataIndex: 'id',
    valueType: 'text',
  },
  {
    title: '公告内容',
    dataIndex: 'content',
    valueType: 'textarea',
  },
  {
    title: '发布者',
    dataIndex: 'publisher',
    valueType: 'text',
  },
  {
    title: '公告类型',
    dataIndex: 'type',
    valueType: 'select',
    valueEnum: {
      0: {
        text: '普通通知',
      },
      1: {
        text: '讲座通知',
      },
      2: {
        text: '重要通知',
      },

    },
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
    <ProTable<API.HealthSmParams>
      columns={columns}
      actionRef={actionRef}
      cardBordered
      request={searchNoticeInfos}
      editable={{
        type: 'multiple',
        //修改后保存
        onSave: async (rowKey, data, row) => {
          try {
            const result = await updateNoticeInfos(data);
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
            const result = await deleteNoticeInfo(rowKey)
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
